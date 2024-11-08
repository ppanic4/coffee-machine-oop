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
        if (currentWater < coffeeCupType.getWater()) {
            return "Not enough water";
        } else if (currentMilk < coffeeCupType.getMilk()) {
            return "Not enough milk";
        } else if (currentCoffeeBeans < coffeeCupType.getCoffeeBeans()) {
            return "Not enough coffee beans";
        } else if (currentCups < 1) {
            return "Not enough cups";
        } else {
            return null;
        }
    }

    // Buy coffee method

    public void buyCoffee(CoffeeCupType coffeeCupType) {
        this.currentWater = this.currentWater - coffeeCupType.getWater();
        this.currentMilk = this.currentMilk - coffeeCupType.getMilk();
        this.currentCoffeeBeans = this.currentCoffeeBeans - coffeeCupType.getCoffeeBeans();
        this.currentMoney = this.currentMoney - coffeeCupType.getCost();
        this.currentCups = this.currentCups - 1;
    }

    // Fill the machine method

    public void fillMachine(int water, int milk, int coffeeBeans, int cups) {
        this.currentWater = this.currentWater + water;
        this.currentMilk = this.currentMilk + milk;
        this.currentCoffeeBeans = this.currentCoffeeBeans + coffeeBeans;
        this.currentCups = this.currentCups + cups;
    }

    //Display machine status

    public String checkRemaining() {
        return "The coffee machine has:\n" +
                getCurrentWater() + " ml of water\n" +
                getCurrentMilk() + " ml of milk\n" +
                getCurrentCoffeeBeans() + " g of coffee beans\n" +
                getCurrentCups() + " disposable cups\n" +
                "$" + getCurrentMoney() + " of money\n";
    }

    //Take money

    public double takeMoney() {
        double tempMoney = currentMoney;
        currentMoney = 0;
        return tempMoney;
    }

    // Save state

    public void saveState() {
        File file = new File(filePath);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(getCurrentWater() + "; " + getCurrentMilk() + "; " + getCurrentCoffeeBeans() + "; " +
                    getCurrentCups() + "; " + getCurrentMoney() + "\n");
            writer.write(getAdminUsername() + "; " + getAdminPassword() + "\n");
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
