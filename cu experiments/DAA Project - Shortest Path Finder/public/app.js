let map;
let directionsService;
let directionsRenderer;
let startMarker, endMarker;

function initMap() {
    // Initialize the map centered around a default location (e.g., New York)
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 40.7128, lng: -74.0060 },
        zoom: 14,
    });
    directionsService = new google.maps.DirectionsService();
    directionsRenderer = new google.maps.DirectionsRenderer();
    directionsRenderer.setMap(map);
}

function findShortestPath() {
    // Get the start and end locations from user input
    let startLocation = document.getElementById("start").value;
    let endLocation = document.getElementById("end").value;

    let request = {
        origin: startLocation,
        destination: endLocation,
        travelMode: google.maps.TravelMode.DRIVING,
    };

    // Get directions from Google Maps API
    directionsService.route(request, function (result, status) {
        if (status === google.maps.DirectionsStatus.OK) {
            directionsRenderer.setDirections(result);
            plotMarkers(result.routes[0].legs[0].start_location, result.routes[0].legs[0].end_location);
        } else {
            alert("Directions request failed due to " + status);
        }
    });
}

function plotMarkers(startLocation, endLocation) {
    // Remove any previous markers
    if (startMarker) startMarker.setMap(null);
    if (endMarker) endMarker.setMap(null);

    // Plot the start marker
    startMarker = new google.maps.Marker({
        position: startLocation,
        map: map,
        label: "Start",
    });

    // Plot the end marker
    endMarker = new google.maps.Marker({
        position: endLocation,
        map: map,
        label: "End",
    });
}
