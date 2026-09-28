package lw02.Unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Mainlw2 {
    public static void main(String[] args) {
        LinkedList<String[]> everyRequest = new LinkedList<>();
        LinkedList<String[]> bookStock = new LinkedList<>();
        LinkedList<String[]> membersBook = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        int kalkul = 2;
        int fisika = 1;
        int Stat = 2;

        Scanner scanner = new Scanner(Mainlw2.class.getResourceAsStream("borrowing.txt"));

        while (scanner.hasNextLine()) {
            String[] request = new String[1];
            String[] bookData = new String[1];
            request[0] = scanner.next(); //nama
            bookData[0] = scanner.next(); //judul buku

            everyRequest.add(request);
            bookStock.add(bookData);
        }
        scanner.close();

        while (scanner.hasNextLine()) {
            String[] member = new String[2];
            member[0] = scanner.next(); //nama
            member[1] = scanner.next(); //judul buku

            membersBook.add(member);
        }
        scanner.close();

        queue.addAll(everyRequest);
        queue.addAll(bookStock);
        queue.addAll(membersBook);
        
        for (String[] request : membersBook) {
            String name = request[0];
            String bookTitle = request[1];
            int farel = 0;
            int taqiy = 0;
            int michelle = 0;
            int raissa = 0;

            if (bookTitle.equals("Kalkulus") && kalkul > 0 && name.equals("Farel") && taqiy < 2) {
                kalkul--;
                farel++;
                membersBook.add(new String[]{name, bookTitle});
            } else if (bookTitle.equals("Fisika") && fisika > 0&& name.equals("Farel") && farel < 2) {
                fisika--;
                farel++;
                membersBook.add(new String[]{name, bookTitle});
            } else if (bookTitle.equals("Statistik") && Stat > 0 && name.equals("Farel") && farel < 2) {
                Stat--;
                farel++;
                membersBook.add(new String[]{name, bookTitle});
            } else{
                failed.push(request);
            }

            if (bookTitle.equals("Kalkulus") && kalkul > 0 && name.equals("Taqiy") && taqiy < 2) {
                kalkul--;
                taqiy++;
                membersBook.add(new String[]{name, bookTitle});
            } else if (bookTitle.equals("Fisika") && fisika > 0&& name.equals("Taqiy") && taqiy < 2) {
                fisika--;
                taqiy++;
                membersBook.add(new String[]{name, bookTitle});
            } else if (bookTitle.equals("Statistik") && Stat > 0 && name.equals("Taqiy") && taqiy < 2) {
                Stat--;
                farel++;
                membersBook.add(new String[]{name, bookTitle});
            } else{
                failed.push(request);
            }
        }
        
        System.out.println("\n=== Successfully Processed Request ===");

        for(String[] memberData : membersBook){
            System.out.println(memberData[0] + ": " + memberData[1]);
        }
        System.out.println("\n=== Remaining Book Stock ===");
        System.out.println("Kalkulus: " + kalkul);
        System.out.println("Fisika: " + fisika);
        System.out.println("Statistik: " + Stat);

        System.out.println("\n=== Failed Requests ===");

        for(String[] failedRequest : failed){
            System.out.println(failedRequest[0] + ": " + failedRequest[1]);
        }
        
    }
}
