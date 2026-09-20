package lw01.prelab;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> printJobs = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("jobs.txt"));
            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int pages = scanner.nextInt();

                if (type.equalsIgnoreCase("MONO")) {
                    printJobs.add(new MonoPrint(id, pages));
                } else if (type.equalsIgnoreCase("COLOUR")) {
                    printJobs.add(new ColourPrint(id, pages));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File jobs.txt tidak ditemukan.");
        }

        // Menampilkan summary menggunakan polimorfisme (tanpa instanceof atau cast)
        for (PrintJob job : printJobs) {
            System.out.println(job.summary());
        }
    }
}
