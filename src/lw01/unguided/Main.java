package lw01.unguided;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        try (Scanner scanner = new Scanner(Main.class.getResourceAsStream("/jobs.txt"))) {
            int n = scanner.nextInt();
            Rental[] rentals = new Rental[n];
            for (int i = 0; i < n; i++) {
                String type = scanner.next();
                if (type.equals("Projector")) {
                } else if (type.equals("Laptop")) {
                } else {
                    throw new IllegalArgumentException("Unknown rental type: " + type);
                }

            }
         
      for (Rental rental : rentals) {
         System.out.println(rental.summary());
      }
        }
    }
}

