package lw01.unguided;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        (Scanner scanner = new Scanner(Main.class.getResourceAsStream("/jobs.txt"))) {
                int n = scanner.nextInt();
                String[] ids = new String[n];
                Rental[] rentals = new Rental[n];
                String[] types = new String[n];
                for (int i = 0; i < n; i++) {
                    String type = scanner.next();
                    String id = scanner.next();
                    int days = scanner.nextInt();

                    Rental job;
                    if (type.equals("Projector")) {
                        job = new ProjectorRental(id, days);
                    } else if (type.equals("Laptop")) {
                        job = new LaptopRental(id, days);
                    } else {
                        throw new IllegalArgumentException("Unknown rental type: " + type);
                    }

                }
                scanner.close();
                for (Rental rental : rentals) {
                System.out.println(rental.summary());
            
        }

    }
}

