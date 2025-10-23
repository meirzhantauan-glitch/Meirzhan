// Abstract class with template method
abstract class Beverage {
    // Template method
    final void prepareBeverage() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }
    // Common methods
    void boilWater() {
        System.out.println("Boiling water");
    }
    void pourInCup() {
        System.out.println("Pouring into cup");
    }
    // Abstract methods to be implemented by subclasses
    abstract void brew();
    abstract void addCondiments();
    // Hook method
    boolean customerWantsCondiments() {
        return true;
    }
}
// Concrete classes
class Tea extends Beverage {
    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }
    @Override
    void addCondiments() {
        System.out.println("Adding lemon");
    }
}
class Coffee extends Beverage {
    @Override
    void brew() {
        System.out.println("Dripping coffee through filter");
    }
    @Override
    void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
    @Override
    boolean customerWantsCondiments() {
        // Simulate user input, default to false for testing
        return false;
    }
}
class HotChocolate extends Beverage {
    @Override
    void brew() {
        System.out.println("Mixing cocoa powder with hot water");
    }
    @Override
    void addCondiments() {
        System.out.println("Adding whipped cream");
    }
}
// Client code
public class dom77 {
    public static void main(String[] args) {
        System.out.println("Preparing Tea:");
        Beverage tea = new Tea();
        tea.prepareBeverage();
        System.out.println("\nPreparing Coffee:");
        Beverage coffee = new Coffee();
        coffee.prepareBeverage();
        System.out.println("\nPreparing Hot Chocolate:");
        Beverage hotChocolate = new HotChocolate();
        hotChocolate.prepareBeverage();
    }
}
