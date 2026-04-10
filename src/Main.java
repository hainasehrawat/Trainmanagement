import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.*;

// ================= UC14: Custom Exception =================
class InvalidCapacityException extends Exception {
    InvalidCapacityException(String message) {
        super(message);
    }
}

public class TrainConsistManagementApp {

    // ================= Bogie Class =================
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Invalid capacity: " + capacity);
            }
            this.name = name;
            this.capacity = capacity;
        }

        public String toString() {
            return name + " (" + capacity + ")";
        }
    }

    // ================= Goods Bogie =================
    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // ================= UC1 =================
        List<String> trainConsist = new ArrayList<>();

        // ================= UC2 =================
        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");
        trainConsist.remove("AC Chair");

        // ================= UC3 =================
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");
        bogieIds.add("BG101");

        // ================= UC4 =================
        LinkedList<String> orderedTrain = new LinkedList<>();
        orderedTrain.add("Engine");
        orderedTrain.add("Sleeper");
        orderedTrain.add("AC");
        orderedTrain.add("Cargo");
        orderedTrain.add("Guard");
        orderedTrain.add(2, "Pantry");
        orderedTrain.removeFirst();
        orderedTrain.removeLast();

        // ================= UC5 =================
        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");

        // ================= UC6 =================
        HashMap<String, Integer> bogieCapacity = new HashMap<>();
        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 60);
        bogieCapacity.put("First Class", 24);

        // ================= UC7 =================
        List<Bogie> bogies = new ArrayList<>();

        try {
            bogies.add(new Bogie("Sleeper", 72));
            bogies.add(new Bogie("AC Chair", 60));
            bogies.add(new Bogie("First Class", 24));
            bogies.add(new Bogie("Sleeper", 72));
            bogies.add(new Bogie("Invalid", -10)); // UC14 test
        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ================= UC8 =================
        List<Bogie> filteredBogies = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        // ================= UC9 =================
        Map<String, List<Bogie>> groupedBogies = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        System.out.println("\nGrouped Bogies:");
        groupedBogies.forEach((k, v) -> System.out.println(k + " -> " + v));

        // ================= UC10 =================
        int totalSeats = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("\nTotal Seats: " + totalSeats);

        // ================= UC11 =================
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter Train ID: ");
        String trainId = sc.nextLine();

        System.out.print("Enter Cargo Code: ");
        String cargoCode = sc.nextLine();

        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        System.out.println("Train Valid: " + trainPattern.matcher(trainId).matches());
        System.out.println("Cargo Valid: " + cargoPattern.matcher(cargoCode).matches());

        // ================= UC12 =================
        List<GoodsBogie> goods = new ArrayList<>();
        goods.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goods.add(new GoodsBogie("Open", "Coal"));

        boolean safe = goods.stream()
                .allMatch(g ->
                        !g.type.equalsIgnoreCase("Cylindrical")
                                || g.cargo.equalsIgnoreCase("Petroleum")
                );

        System.out.println("\nSafety: " + (safe ? "SAFE" : "UNSAFE"));

        // ================= UC13 =================
        long startLoop = System.nanoTime();
        int sumLoop = 0;
        for (Bogie b : bogies) sumLoop += b.capacity;
        long endLoop = System.nanoTime();

        long startStream = System.nanoTime();
        int sumStream = bogies.stream().map(b -> b.capacity).reduce(0, Integer::sum);
        long endStream = System.nanoTime();

        System.out.println("\nLoop Time: " + (endLoop - startLoop));
        System.out.println("Stream Time: " + (endStream - startStream));

        // ================= UC15 =================
        try {
            String type = "Rectangular";
            String cargo = "Petroleum";

            if (type.equals("Rectangular") && cargo.equals("Petroleum")) {
                throw new Exception("Unsafe cargo!");
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            System.out.println("Cargo assignment done.");
        }

        // ================= UC16 =================
        for (int i = 0; i < bogies.size() - 1; i++) {
            for (int j = 0; j < bogies.size() - i - 1; j++) {
                if (bogies.get(j).capacity > bogies.get(j + 1).capacity) {
                    Bogie temp = bogies.get(j);
                    bogies.set(j, bogies.get(j + 1));
                    bogies.set(j + 1, temp);
                }
            }
        }

        System.out.println("\nBubble Sorted Bogies:");
        bogies.forEach(System.out::println);

        // ================= UC17 =================
        String[] names = {"Sleeper", "AC Chair", "First Class"};
        Arrays.sort(names);

        System.out.println("\nSorted Names:");
        for (String n : names) System.out.println(n);

        // ================= UC18 =================
        String search = "BG102";
        boolean found = false;

        for (String id : bogieIds) {
            if (id.equals(search)) {
                found = true;
                break;
            }
        }
        System.out.println("\nLinear Search: " + (found ? "Found" : "Not Found"));

        // ================= UC19 =================
        List<String> sortedIds = new ArrayList<>(bogieIds);
        Collections.sort(sortedIds);

        int index = Collections.binarySearch(sortedIds, search);
        System.out.println("Binary Search: " + (index >= 0 ? "Found" : "Not Found"));

        // ================= UC20 =================
        try {
            List<String> empty = new ArrayList<>();
            if (empty.isEmpty()) {
                throw new Exception("Train has no bogies!");
            }
        } catch (Exception e) {
            System.out.println("\nSearch Error: " + e.getMessage());
        }

        sc.close();
    }
}
