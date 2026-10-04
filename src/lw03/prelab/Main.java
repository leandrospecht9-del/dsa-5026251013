package lw03.prelab;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.LinkedHashMap;
public class Main {

    // Problem 1: Playlist (List)
    static void playlist() {
        List<String> songs = new ArrayList<>();
        Scanner sc;
        try {
            sc = new Scanner(new File("src/lw03/prelab/playlist.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File playlist.txt tidak ditemukan");
            return;
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            if (line.startsWith("ADD ")) {
                // ADD <SONG>
                String song = line.substring(4);
                songs.add(song);

            } else if (line.startsWith("INSERT ")) {
                // INSERT <INDEX> <SONG>
                String[] parts = line.split(" ", 3);
                int index = Integer.parseInt(parts[1]);
                String song = parts[2];
                songs.add(index, song);

            } else if (line.startsWith("REMOVE ")) {
                // REMOVE <SONG>, remove(Object) hanya menghapus kemunculan pertama
                String song = line.substring(7);
                songs.remove(song);
            }
        }
        sc.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + songs.size());
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ": " + songs.get(i));
        }
    }

    // Problem 2: Peserta workshop (Set)
    static void participants() {
        Set<String> unique = new LinkedHashSet<>();
        int duplicates = 0;
        Scanner sc;
        try {
            sc = new Scanner(new File("src/lw03/prelab/participant.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File participants.txt tidak ditemukan");
            return;
        }

        while (sc.hasNextLine()) {
            String name = sc.nextLine().trim();
            if (name.isEmpty()) {
                continue;
            }

            // add() mengembalikan false kalau nama sudah ada di Set
            if (!unique.add(name)) {
                duplicates++;
            }
        }
        sc.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + unique.size());
        int no = 1;
        for (String name : unique) {
            System.out.println(no + ". " + name);
            no++;
        }
        System.out.println("Duplicate registrations: " + duplicates);
    }

    // Problem 3: Stok produk (Map)
    static void inventory() {
        Map<String, Integer> stock = new LinkedHashMap<>();
        int failedSales = 0;
        Scanner sc;
        try {
            sc = new Scanner(new File("src/lw03/prelab/inventory.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File inventory.txt tidak ditemukan");
            return;
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(" ");
            String type = parts[0];
            String product = parts[1];
            int qty = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (stock.containsKey(product)) {
                    stock.put(product, stock.get(product) + qty);
                } else {
                    stock.put(product, qty);
                }

            } else if (type.equals("SELL")) {
                if (stock.containsKey(product) && stock.get(product) >= qty) {
                    stock.put(product, stock.get(product) - qty);
                } else {
                    failedSales++;
                }
            }
        }
        sc.close();

        System.out.println("===== Problem 3 =====");
        for (String product : stock.keySet()) {
            System.out.println(product + ": " + stock.get(product));
        }
        System.out.println("Failed sales: " + failedSales);
    }

    public static void main(String[] args) {
        playlist();
        System.out.println();
        participants();
        System.out.println();
        inventory();
    }
}