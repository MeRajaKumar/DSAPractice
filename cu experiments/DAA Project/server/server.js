const express = require('express');
const app = express();
const port = 3000;

app.use(express.json());
app.use(express.static('public')); // Serve static files (like HTML, CSS, JS)

app.post('/find-path', (req, res) => {
    // Implement your Dijkstra’s Algorithm here to compute the shortest path
    let { start, end } = req.body;

    // Placeholder: Replace with actual Dijkstra logic
    const shortestPath = `Shortest path from ${start} to ${end} calculated here.`;

    res.json({ path: shortestPath });
});

app.listen(port, () => {
    console.log(`Server running on http://localhost:${port}`);
});
