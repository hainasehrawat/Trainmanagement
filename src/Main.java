import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== UC6: Map Bogie to Capacity ===");

        Map<String, Integer> capacityMap = new HashMap<>();

        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC", 50);
        capacityMap.put("FirstClass", 40);
        capacityMap.put("Cargo", 100);

        System.out.println("Bogie capacity details:");
        for (Map.Entry<String, Integer> entry : capacityMap.entrySet()) {
            System.out.println(entry.getKey() + " -> Capacity: " + entry.getValue());
        }

        System.out.println("Capacity of Sleeper: " + capacityMap.get("Sleeper"));
    }
}