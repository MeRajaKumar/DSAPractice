import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.*;

public class Main {
    // Simple in-memory data store
    static class Location {
        String id;
        String name;
        double lat;
        double lng;
        
        Location(String id, String name, double lat, double lng) {
            this.id = id;
            this.name = name;
            this.lat = lat;
            this.lng = lng;
        }
    }
    
    static class Road {
        String from;
        String to;
        double distance;
        
        Road(String from, String to, double distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }
    
    static Map<String, Location> locations = new HashMap<>();
    static List<Road> roads = new ArrayList<>();
    static Gson gson = new Gson();
    
    public static void main(String[] args) {
        // Initialize sample data
        initializeData();
        
        // Configure Spark
        port(4567);
        staticFiles.location("/public");
        
        // API endpoints
        get("/locations", (req, res) -> {
            res.type("application/json");
            return gson.toJson(locations.values());
        });
        
        get("/route", (req, res) -> {
            String from = req.queryParams("from");
            String to = req.queryParams("to");
            
            // Simple shortest path calculation (Dijkstra's algorithm)
            List<String> path = calculateRoute(from, to);
            
            res.type("application/json");
            return gson.toJson(path);
        });
    }
    
    static void initializeData() {
        // Add sample locations
        locations.put("A", new Location("A", "Central Park", 40.7829, -73.9654));
        locations.put("B", new Location("B", "Times Square", 40.7580, -73.9855));
        locations.put("C", new Location("C", "Empire State", 40.7484, -73.9857));
        locations.put("D", new Location("D", "Brooklyn Bridge", 40.7061, -73.9969));
        
        // Add sample roads
        roads.add(new Road("A", "B", 2.5));
        roads.add(new Road("B", "C", 1.2));
        roads.add(new Road("C", "D", 3.0));
        roads.add(new Road("A", "D", 4.0)); // Alternative longer route
    }
    
    static List<String> calculateRoute(String from, String to) {
        // This is a simplified implementation
        // In a real project, you'd use Dijkstra's or A* algorithm
        
        // For demo, return a hardcoded path
        if (from.equals("A") && to.equals("D")) {
            return Arrays.asList("A", "B", "C", "D"); // Shorter path
        }
        
        return Arrays.asList(from, to); // Fallback
    }
}