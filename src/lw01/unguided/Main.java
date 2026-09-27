package lw01.unguided;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] ids = new String[];
        Rental[] rentals = new Rental[];
        String[] days = new String[];


        try (Scanner scanner = new Scanner(Main.class.getResourceAsStream("/jobs.txt"))) {
            int n = scanner.nextInt();
            
            for (int i = 0; i < n; i++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();

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

