package lw02.Unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Mainlw2 {
    public static void main(String[] args) {
        Queue<String[]> requestQueue = new LinkedList<>();
        LinkedList<String[]> membersBook = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        // Stok awal buku
        int kalkul = 2;
        int fisika = 1;
        int stat = 2;

        // Penghitung jumlah buku yang dipinjam masing-masing member (maksimal 2)
        int farel = 0;
        int taqiy = 0;
        int michelle = 0;
        int raissa = 0;

        // Membaca file borrowing.txt sekali saja
        try (Scanner scanner = new Scanner(Mainlw2.class.getResourceAsStream("borrowing.txt"))) {
            while (scanner.hasNext()) {
                String name = scanner.next();       // Nama peminjam
                String bookTitle = scanner.next();  // Judul buku
                requestQueue.add(new String[]{name, bookTitle});
            }
        } catch (Exception e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
            return;
        }

        // Memproses antrean peminjaman (Queue FIFO)
        while (!requestQueue.isEmpty()) {
            String[] request = requestQueue.poll();
            String name = request[0];
            String bookTitle = request[1];

            boolean success = false;

            // Cek berdasarkan nama peminjam
            if (name.equals("Farel")) {
                if (farel < 2) {
                    if (bookTitle.equals("Kalkulus") && kalkul > 0) {
                        kalkul--;
                        farel++;
                        success = true;
                    } else if (bookTitle.equals("Fisika") && fisika > 0) {
                        fisika--;
                        farel++;
                        success = true;
                    } else if (bookTitle.equals("Statistika") && stat > 0) {
                        stat--;
                        farel++;
                        success = true;
                    }
                }
            } else if (name.equals("Taqiy")) {
                if (taqiy < 2) {
                    if (bookTitle.equals("Kalkulus") && kalkul > 0) {
                        kalkul--;
                        taqiy++;
                        success = true;
                    } else if (bookTitle.equals("Fisika") && fisika > 0) {
                        fisika--;
                        taqiy++;
                        success = true;
                    } else if (bookTitle.equals("Statistika") && stat > 0) {
                        stat--;
                        taqiy++;
                        success = true;
                    }
                }
            } else if (name.equals("Michelle")) {
                if (michelle < 2) {
                    if (bookTitle.equals("Kalkulus") && kalkul > 0) {
                        kalkul--;
                        michelle++;
                        success = true;
                    } else if (bookTitle.equals("Fisika") && fisika > 0) {
                        fisika--;
                        michelle++;
                        success = true;
                    } else if (bookTitle.equals("Statistika") && stat > 0) {
                        stat--;
                        michelle++;
                        success = true;
                    }
                }
            } else if (name.equals("Raissa")) {
                if (raissa < 2) {
                    if (bookTitle.equals("Kalkulus") && kalkul > 0) {
                        kalkul--;
                        raissa++;
                        success = true;
                    } else if (bookTitle.equals("Fisika") && fisika > 0) {
                        fisika--;
                        raissa++;
                        success = true;
                    } else if (bookTitle.equals("Statistika") && stat > 0) {
                        stat--;
                        raissa++;
                        success = true;
                    }
                }
            }

            // Jika peminjaman berhasil dicatat, masukkan ke membersBook. Jika gagal, masukkan ke Stack failed.
            if (success) {
                membersBook.add(request);
            } else {
                failed.push(request);
            }
        }

        // Output Hasil sesuai format
        System.out.println("=== Successfully Processed Requests ===");
        for (String[] memberData : membersBook) {
            System.out.println(memberData[0] + " " + memberData[1]);
        }

        System.out.println("\n=== Remaining Book Stock ===");
        System.out.println("Kalkulus : " + kalkul);
        System.out.println("Fisika : " + fisika);
        System.out.println("Statistika : " + stat);

        System.out.println("\n=== Failed Requests ===");
        for (String[] failedRequest : failed) {
            System.out.println(failedRequest[0] + " " + failedRequest[1]);
        }
    }
}