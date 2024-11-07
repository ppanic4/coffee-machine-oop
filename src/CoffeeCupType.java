public class CoffeeCupType {

    static int water;
    static int milk;
    static int coffeeBeans;
    static double cost;

    public CoffeeCupType (int water, int milk, int coffeeBeans, double cost){
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cost = cost;
    }

    public static double getCost() {
        return cost;
    }

    public static int getCoffeeBeans() {
        return coffeeBeans;
    }

    public static int getMilk() {
        return milk;
    }

    public static int getWater() {
        return water;
    }

}
