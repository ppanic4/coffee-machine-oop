import java.util.Scanner;

public class CoffeeUI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CoffeeMachine coffeeMachine = new CoffeeMachine();

        coffeeMachine.loadState();

        while (true) {  // Main loop
            System.out.println("Write action (buy, login, exit):");
            String action = scanner.next().toLowerCase();

            if (action.equals("buy")) {  // User buying coffee
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                String choice = scanner.next().toLowerCase();

                switch (choice) {
                    case "1":
                        CoffeeCupType espresso = new CoffeeCupType(250, 0, 16, 4.0);
                        if (coffeeMachine.checkResources(espresso) == null) {
                            System.out.println("I have enough resources, making you an espresso!");
                            coffeeMachine.buyCoffee(espresso);
                        } else {
                            System.out.println(coffeeMachine.checkResources(espresso));
                        }
                        break;

                    case "2":
                        CoffeeCupType latte = new CoffeeCupType(350, 75, 20, 7.0);
                        if (coffeeMachine.checkResources(latte) == null) {
                            System.out.println("I have enough resources, making you a latte!");
                            coffeeMachine.buyCoffee(latte);
                        } else {
                            System.out.println(coffeeMachine.checkResources(latte));
                        }
                        break;

                    case "3":
                        CoffeeCupType cappuccino = new CoffeeCupType(200, 100, 12, 6.0);
                        if (coffeeMachine.checkResources(cappuccino) == null) {
                            System.out.println("I have enough resources, making you a cappuccino!");
                            coffeeMachine.buyCoffee(cappuccino);
                        } else {
                            System.out.println(coffeeMachine.checkResources(cappuccino));
                        }
                        break;

                    default:
                        System.out.println("Invalid choice.");
                        break;
                }

            } else if (action.equals("login")) {  // Administrator login
                System.out.print("Enter username: ");
                String username = scanner.next();
                System.out.print("Enter password: ");
                String password = scanner.next();

                if (username.equals(coffeeMachine.getAdminUsername()) && password.equals(coffeeMachine.getAdminPassword())) {
                    while (true) {  // Admin actions loop
                        System.out.println("Write action (fill, remaining, take, exit):");
                        String adminAction = scanner.next().toLowerCase();

                        switch (adminAction) {
                            case "fill":
                                System.out.print("Write how many ml of water you want to add: ");
                                int addedWater = scanner.nextInt();
                                System.out.print("Write how many ml of milk you want to add: ");
                                int addedMilk = scanner.nextInt();
                                System.out.print("Write how many grams of coffee beans you want to add: ");
                                int addedCoffeeBeans = scanner.nextInt();
                                System.out.print("Write how many disposable cups you want to add: ");
                                int addedCups = scanner.nextInt();

                                coffeeMachine.fillMachine(addedWater, addedMilk, addedCoffeeBeans, addedCups);
                                System.out.println("Machine refilled.");
                                break;

                            case "remaining":
                                System.out.println(coffeeMachine.checkRemaining());
                                break;

                            case "take":
                                double moneyTaken = coffeeMachine.takeMoney();
                                System.out.printf("I gave you $%.2f%n", moneyTaken);
                                break;

                            case "exit":
                                break;

                            default:
                                System.out.println("Invalid admin action.");
                                break;
                        }

                        if (adminAction.equals("exit")) {
                            break;  // Exit admin actions loop
                        }
                    }
                } else {
                    System.out.println("Wrong username or password");
                }

            } else if (action.equals("exit")) {  // Exit the program
                coffeeMachine.saveState();
                System.out.println("Thank you! Shutting down...");
                break;

            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
