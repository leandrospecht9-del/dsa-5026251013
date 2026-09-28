package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import lw01.prelab.Main;


public class main2 {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        while (scanner.hasNextLine()) {
            String[] transaction = new String[3];
            transaction[0] = scanner.next();
            transaction[1] = scanner.next();
            transaction[2] = scanner.next();
            transactions.add(transaction);
        }
        scanner.close();

        queue.addAll(transactions);

        while(!queue.isEmpty()){
            String[] transaction = queue.poll();

            String name = transaction[0];
            String type = transaction[1];
            String amountStr = transaction[2];

            int amount;
            try {
                amount = Integer.parseInt(amountStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount for " + name + ": " + amountStr);
                failed.push(transaction);
                continue;
            }

            String[] customer = null;

            for (String[] data : customers){
                if(data[0].equals(name)){
                    customer = data;
                    break;  
                }
            }

            if(customer == null){
                customer = new String[]{name, "0"};
                customers.add(customer);
            }

            int currentBalance = Integer.parseInt(customer[1]);

            if(type.equals("deposit")){
                currentBalance += amount;
                customer[1] = String.valueOf(currentBalance);
            } else if(type.equals("withdraw")){
                if(currentBalance >= amount){
                    currentBalance -= amount;
                    customer[1] = String.valueOf(currentBalance);
                } else {
                    System.out.println("Insufficient funds for " + name + ": " + currentBalance + " requested: " + amount);
                    failed.push(transaction);
                }
            } else {
                System.out.println("Unknown transaction type for " + name + ": " + type);
                failed.push(transaction);
            }
        }

        System.out.println("\n=== Final Balances ===");

        for(String[] customer : customers){
            System.out.println(customer[0] + ": " + customer[1]);
        }

        System.out.println("\n=== Failed Transactions ===");
        while(!failed.isEmpty()){
            String[] transaction = failed.pop();
            
            System.out.println(transaction[0] + ": " + transaction[1] + " " + transaction[2]);
        }
    }
}
