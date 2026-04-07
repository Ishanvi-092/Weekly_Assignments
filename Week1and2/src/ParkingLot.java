import java.util.*;

public class ParkingLot {

    static String[] spots = new String[10];

    public static int hash(String plate) {
        return Math.abs(plate.hashCode()) % spots.length;
    }

    public static void park(String plate) {
        int index = hash(plate);
        int probes = 0;

        while (spots[index] != null) {
            index = (index + 1) % spots.length;
            probes++;
        }

        spots[index] = plate;
        System.out.println("Parked at " + index + ", probes: " + probes);
    }

    public static void exit(String plate) {
        for (int i = 0; i < spots.length; i++) {
            if (plate.equals(spots[i])) {
                spots[i] = null;
                System.out.println("Exited from " + i);
                return;
            }
        }
    }

    public static void main(String[] args) {
        park("ABC123");
        park("ABC124");
        exit("ABC123");
    }
}
