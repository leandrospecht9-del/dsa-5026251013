package lw04.unguided;

import java.util.Scanner;

public class Main {

    static void arrangeCard(int[] a, int passes) {
        for (int i = 0; i < passes; i++) {
            int minIdx = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = a[i];
            a[i] = a[minIdx];
            a[minIdx] = temp;
        }
    }

    static int seeCall(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //Scanner sc = new Scanner(Main.class.getResourceAsStream("cards.txt"));
        Scanner sc = new Scanner(Main.class.getResourceAsStream("cards2.txt"));

        int n = sc.nextInt();
        int[] cards = new int[n];
        for (int i = 0; i < n; i++) {
            cards[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] call = new int[m];
        for (int i = 0; i < m; i++) {
            call[i] = sc.nextInt();
        }
        sc.close();

        arrangeCard(cards, 2);
        for (int k = 0; k < n; k++) {
            System.out.print(cards[k] + " ");
        }
        System.out.println();

        for (int i = 0; i < m; i++) {
            int idx = seeCall(cards, call[i]);
            if (idx >= 0) {
                System.out.println(call[i] + ": FOUND at index " + idx);
            } else {
                System.out.println(call[i] + ": NOT FOUND");
            }
        }
    }
}