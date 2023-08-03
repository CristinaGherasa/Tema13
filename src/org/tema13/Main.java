package org.tema13;


import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DailyPlanner planner = new DailyPlanner();

        planner.addActivity(Day.MONDAY, "Meeting");
        planner.addActivity(Day.MONDAY, "Workout");
        planner.addActivity(Day.TUESDAY, "Study");
        planner.addActivity(Day.TUESDAY, "Working");
        planner.addActivity(Day.WEDNESDAY, "Shopping");
        planner.addActivity(Day.WEDNESDAY, "Lunch");

        planner.removeActivity(Day.MONDAY, "Workout");

        List<String> tuesdayActivities = planner.getActivities(Day.TUESDAY);
        System.out.println("Tuesday Activities: " + tuesdayActivities);

        try {
            Map<Day, List<String>> plan = planner.endPlanning();
            System.out.println("Daily Plan: " + plan);
        } catch (NoActivitiesForDayException e) {
            System.out.println("No activities for one or more days: " + e.getMessage());
        }


        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1) Add an activity");
            System.out.println("2) Remove an activity");
            System.out.println("3) List all activities");
            System.out.println("4) End planning");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addActivity(planner, scanner);
                    break;
                case 2:
                    removeActivity(planner, scanner);
                    break;
                case 3:
                    listAllActivities(planner, scanner);
                    break;
                case 4:
                    endPlanning(planner);
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addActivity(DailyPlanner planner, Scanner scanner) {
        System.out.println("Enter the day:");
        Day day = Day.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Enter the activity:");
        String activity = scanner.nextLine();

        try {
            planner.addActivity(day, activity);
            System.out.println("Activity added successfully.");
        } catch (NoActivityException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void removeActivity(DailyPlanner planner, Scanner scanner) {
        System.out.println("Enter the day:");
        Day day = Day.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Enter the activity:");
        String activity = scanner.nextLine();

        try {
            planner.removeActivity(day, activity);
            System.out.println("Activity removed successfully.");
        } catch (NoActivityException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listAllActivities(DailyPlanner planner, Scanner scanner) {
        System.out.println("Enter the day:");
        Day day = Day.valueOf(scanner.nextLine().toUpperCase());

        List<String> activities = planner.getActivities(day);
        System.out.println("Activities for " + day + ": " + activities);
    }

    private static void endPlanning(DailyPlanner planner) {
        try {
            Map<Day, List<String>> plan = planner.endPlanning();
            System.out.println("Daily Plan: " + plan);
        } catch (NoActivitiesForDayException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}