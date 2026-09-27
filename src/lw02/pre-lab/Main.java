import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        // baca semua transaksi dari file, simpan ke LinkedList
        LinkedList<String[]> daftarTransaksi = new LinkedList<>();

        Scanner baca = new Scanner(Main.class.getResourceAsStream("/transactions.txt"));

        while (baca.hasNextLine()) {
            String baris = baca.nextLine().trim();
            if (baris.isEmpty()) {
                continue;
            }
            String[] data = baris.split(" ");
            daftarTransaksi.add(data);
        }
        baca.close();

        // buat daftar customer, saldo awal 0
        LinkedList<String[]> daftarCustomer = new LinkedList<>();

        for (String[] transaksi : daftarTransaksi) {
            String nama = transaksi[0];
            boolean sudahAda = false;

            for (String[] customer : daftarCustomer) {
                if (customer[0].equals(nama)) {
                    sudahAda = true;
                }
            }

            if (sudahAda == false) {
                String[] customerBaru = new String[2];
                customerBaru[0] = nama;
                customerBaru[1] = "0";
                daftarCustomer.add(customerBaru);
            }
        }

        // pindahkan semua transaksi ke Queue
        Queue<String[]> antrian = new LinkedList<>();
        for (String[] transaksi : daftarTransaksi) {
            antrian.offer(transaksi);
        }

        // Siapkan Stack untuk menyimpan transaksi yang gagal
        Stack<String[]> gagal = new Stack<>();

        // proses transaksi satu per satu, urut dari depan (FIFO)
        while (antrian.isEmpty() == false) {
            String[] transaksi = antrian.poll();
            String nama = transaksi[0];
            String jenis = transaksi[1];
            int jumlah = Integer.parseInt(transaksi[2]);

            // cari customer yang namanya cocok
            String[] customer = null;
            for (String[] c : daftarCustomer) {
                if (c[0].equals(nama)) {
                    customer = c;
                }
            }

            int saldo = Integer.parseInt(customer[1]);

            if (jenis.equals("DEPOSIT")) {
                saldo = saldo + jumlah;
                customer[1] = String.valueOf(saldo);
            }

            if (jenis.equals("WITHDRAW")) {
                if (jumlah > saldo) {
                    // saldo tidak cukup, transaksi gagal
                    gagal.push(transaksi);
                } else {
                    saldo = saldo - jumlah;
                    customer[1] = String.valueOf(saldo);
                }
            }
        }

        // tampilkan saldo akhir tiap customer
        System.out.println("=== Final Balances ===");
        for (String[] customer : daftarCustomer) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        //                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           tampilkan semua transaksi yang gagal
        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (gagal.isEmpty() == false) {
            String[] t = gagal.pop();
            System.out.println(t[0] + " " + t[1] + " " + t[2]);
        }
    }
}