document.addEventListener('DOMContentLoaded', function() {
    // Initialize map
    const map = L.map('map').setView([40.7128, -74.0060], 13);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);
    
    let markers = {};
    let routeLine;
    
    // Load locations
    fetch('/locations')
        .then(response => response.json())
        .then(locations => {
            const fromSelect = document.getElementById('from');
            const toSelect = document.getElementById('to');
            
            locations.forEach(loc => {
                // Add to dropdowns
                const option = document.createElement('option');
                option.value = loc.id;
                option.textContent = loc.name;
                fromSelect.appendChild(option.cloneNode(true));
                toSelect.appendChild(option);
                
                // Add marker to map
                markers[loc.id] = L.marker([loc.lat, loc.lng])
                    .addTo(map)
                    .bindPopup(loc.name);
            });
        });
    
    // Find route button
    document.getElementById('find-route').addEventListener('click', function() {
        const from = document.getElementById('from').value;
        const to = document.getElementById('to').value;
        
        if (!from || !to) {
            alert('Please select both starting point and destination');
            return;
        }
        
        fetch(`/route?from=${from}&to=${to}`)
            .then(response => response.json())
            .then(route => {
                displayRoute(route);
            });
    });
    
    function displayRoute(routeIds) {
        // Clear previous route
        if (routeLine) {
            map.removeLayer(routeLine);
        }
        
        // Get coordinates for the route
        const routeCoords = routeIds.map(id => {
            const marker = markers[id];
            return [marker.getLatLng().lat, marker.getLatLng().lng];
        });
        
        // Draw the route
        routeLine = L.polyline(routeCoords, {color: 'blue'}).addTo(map);
        
        // Zoom to fit the route
        map.fitBounds(routeLine.getBounds());
        
        // Display route info
        document.getElementById('route-info').textContent = 
            `Route: ${routeIds.join(' → ')}`;
    }
});