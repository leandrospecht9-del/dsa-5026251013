package lw04.Prelab;

import java.util.Scanner;

public class timeTrav {
    static void insertion(int[] a, int passes) {
        for (int i = 1; i <= passes; i++) {
            int key = a[i];
            int j = i - 1;

            while (j >= 0 && a[j] < key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    static int linearSearch(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(timeTrav.class.getResourceAsStream("branches.txt"));

        int n = sc.nextInt();
        int[] branches = new int[n];
        for (int i = 0; i < n; i++) {
            branches[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] shoot = new int[m];
        for (int i = 0; i < m; i++) {
            shoot[i] = sc.nextInt();
        }
        sc.close();
        insertion(branches, 3);

        for (int k = 0; k < n; k++) {
            System.out.print(branches[k] + " ");
        }
        System.out.println();

        for (int i = 0; i < m; i++) {
            int idx = linearSearch(branches, shoot[i]);
            if (idx >= 0) {
                System.out.println(shoot[i] + ": FOUND at index " + idx);
            } else {
                System.out.println(shoot[i] + ": NOT FOUND");
            }
        }
    }
}