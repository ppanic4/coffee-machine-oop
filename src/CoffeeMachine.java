import java.io.FileWriter;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;


public class CoffeeMachine {

    static int currentWater = 400;
    static int currentMilk = 540;
    static int currentCoffeeBeans = 120;
    static int currentCups = 9;
    static double currentMoney = 550;

    private static String filePath = "doc/coffee_machine_status.txt";
    private static String adminUsername = "admin";
    private static String adminPassword = "admin123";

// Check remaining resources method

    public String checkResources(CoffeeCupType coffeeCupType) {
        String missingResources = "";

        if (currentWater < coffeeCupType.getWater()) {
            missingResources += "Not enough water\n";
        }
        if (currentMilk < coffeeCupType.getMilk()) {
            missingResources += "Not enough milk\n";
        }
        if (currentCoffeeBeans < coffeeCupType.getCoffeeBeans()) {
            missingResources += "Not enough coffee beans\n";
        }
        if (currentCups < 1) {
            missingResources += "Not enough cups\n";
        }
        if (missingResources.isEmpty()) {
            return null; // Nema nedostataka
        } else {
            return missingResources.trim();
        }
    }

    // Buy coffee method

    public void buyCoffee(CoffeeCupType coffeeCupType) {
        currentWater = currentWater - coffeeCupType.getWater();
        currentMilk = currentMilk - coffeeCupType.getMilk();
        currentCoffeeBeans = currentCoffeeBeans - coffeeCupType.getCoffeeBeans();
        currentMoney = currentMoney - coffeeCupType.getCost();
        currentCups = currentCups - 1;
    }

    // Fill the machine method

    public void fillMachine(int water, int milk, int coffeeBeans, int cups) {
        currentWater = currentWater + water;
        currentMilk = currentMilk + milk;
        currentCoffeeBeans = currentCoffeeBeans + coffeeBeans;
        currentCups = currentCups + cups;
    }

    //Display machine status

    public String checkRemaining() {
        return "The coffee machine has:\n" +
                currentWater + " ml of water\n" +
                currentMilk + " ml of milk\n" +
                currentCoffeeBeans + " g of coffee beans\n" +
                currentCups + " disposable cups\n" +
                "$" + currentMoney + " of money\n";
    }

    //Take money

    public double takeMoney() {
        double tempMoney = currentMoney;
        currentMoney = 0;
        return tempMoney;
    }

    public void saveState() {
        File file = new File(filePath);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(currentWater + "; " + currentMilk + "; " + currentCoffeeBeans + "; " +
                    currentCups + "; " + currentMoney + "\n");
            writer.write(adminUsername + "; " + adminPassword + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the machine state: " + e.getMessage());
        }
    }

    // Load file

    public void loadState() {
        File file = new File(filePath);

        try (Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter("; |\n");
            scanner.useLocale(Locale.US);

            currentWater = scanner.nextInt();
            currentMilk = scanner.nextInt();
            currentCoffeeBeans = scanner.nextInt();
            currentCups = scanner.nextInt();
            currentMoney = scanner.nextDouble();
            adminUsername = scanner.next();
            adminPassword = scanner.next();
        } catch (IOException e) {
            System.out.println("An error occurred while loading the machine state: " + e.getMessage());
        }
    }

    // Getters and setters

    public static int getCurrentWater() {
        return currentWater;
    }

    public static void setCurrentWater(int currentWater) {
        CoffeeMachine.currentWater = currentWater;
    }

    public static int getCurrentMilk() {
        return currentMilk;
    }

    public static void setCurrentMilk(int currentMilk) {
        CoffeeMachine.currentMilk = currentMilk;
    }

    public static int getCurrentCoffeeBeans() {
        return currentCoffeeBeans;
    }

    public static void setCurrentCoffeeBeans(int currentCoffeeBeans) {
        CoffeeMachine.currentCoffeeBeans = currentCoffeeBeans;
    }

    public static int getCurrentCups() {
        return currentCups;
    }

    public static void setCurrentCups(int currentCups) {
        CoffeeMachine.currentCups = currentCups;
    }

    public static double getCurrentMoney() {
        return currentMoney;
    }

    public static void setCurrentMoney(double currentMoney) {
        CoffeeMachine.currentMoney = currentMoney;
    }

    public static String getAdminUsername() {
        return adminUsername;
    }

    public static String getAdminPassword() {
        return adminPassword;
    }
}
