package lw03.unguided;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Map<String, Integer> enrol = new LinkedHashMap<>();
        List<String> courses = new ArrayList<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));
        int rejected = 0;
        
        while (sc.hasNextLine()){
            String line = sc.nextLine();

            String[] parts = line.split(" ");

            String type = parts[0];
            String course  = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("REGISTER")){
                courses.add(course);
                if(enrol.containsKey(course)){
                    int seats = enrol.get(course);
                    enrol.put(course, seats + quantity);
                } else {
                    enrol.put(course, quantity);
                } 
            } else if(type.equals("WITHDRAW")){
                if(enrol.containsKey(course) && enrol.get(course) > quantity){
                    int seats = enrol.get(course);
                    enrol.put(course, seats - quantity);
                } else {
                    rejected++;
                } 
            } else if(type.equals("CHECK")){
                System.out.println("===== " + "Enrollment Checks" + " =====");
                if(enrol.containsKey(course)){
                    System.out.println(course + ": " + enrol.get(course) + " Students");
                } else {
                    System.out.println(course + ": Not Found");
                }
            }
        } sc.close();

        while (courses.size() > 0){
            String course = courses.get(0);
            if(enrol.get(course) == 0){
                enrol.remove(course);
            }
            courses.remove(0);
        }

        System.out.println("===== " + "Final Enrollment" + " =====");
        for (String course : enrol.keySet()){
            System.out.println(course + ": " + enrol.get(course) + " Students");
        }
        System.out.println();
        System.out.println("Rejected operations: " + rejected);
    }
}
