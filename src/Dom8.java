public class Dom8 {

    //  1. Негізгі интерфейс
    interface Beverage {
        String getDescription();
        double getCost();
    }
    //  2. Нақты сусындар
    static class Espresso implements Beverage {
        public String getDescription() {
            return "Espresso";
        }
        public double getCost() {
            return 500;
        }
    }
    static class Tea implements Beverage {
        public String getDescription() {
            return "Tea";
        }
        public double getCost() {
            return 300;
        }
    }
    static class Latte implements Beverage {
        public String getDescription() {
            return "Latte";
        }
        public double getCost() {
            return 600;
        }
    }
    static class Mocha implements Beverage {
        public String getDescription() {
            return "Mocha";
        }
        public double getCost() {
            return 650;
        }
    }

    //  3. Абстрактты декоратор
    static abstract class BeverageDecorator implements Beverage {
        protected Beverage beverage;
        public BeverageDecorator(Beverage beverage) {
            this.beverage = beverage;
        }
        public String getDescription() {
            return beverage.getDescription();
        }
        public double getCost() {
            return beverage.getCost();
        }
    }

    //  4. Нақты декораторлар
    static class Milk extends BeverageDecorator {
        public Milk(Beverage beverage) {
            super(beverage);
        }
        public String getDescription() {
            return beverage.getDescription() + ", Milk";
        }
        public double getCost() {
            return beverage.getCost() + 100;
        }
    }
    static class Sugar extends BeverageDecorator {
        public Sugar(Beverage beverage) {
            super(beverage);
        }
        public String getDescription() {
            return beverage.getDescription() + ", Sugar";
        }
        public double getCost() {
            return beverage.getCost() + 50;
        }
    }
    static class WhippedCream extends BeverageDecorator {
        public WhippedCream(Beverage beverage) {
            super(beverage);
        }
        public String getDescription() {
            return beverage.getDescription() + ", Whipped Cream";
        }
        public double getCost() {
            return beverage.getCost() + 150;
        }
    }
    static class Caramel extends BeverageDecorator {
        public Caramel(Beverage beverage) {
            super(beverage);
        }
        public String getDescription() {
            return beverage.getDescription() + ", Caramel";
        }
        public double getCost() {
            return beverage.getCost() + 120;
        }
    }
    static class Vanilla extends BeverageDecorator {
        public Vanilla(Beverage beverage) {
            super(beverage);
        }
        public String getDescription() {
            return beverage.getDescription() + ", Vanilla";
        }
        public double getCost() {
            return beverage.getCost() + 130;
        }
    }

    //  5. Клиенттік код
    public static void main(String[] args) {
        Beverage espresso = new Espresso();
        System.out.println(espresso.getDescription() + " → " + espresso.getCost() + " ₸");

        Beverage espressoWithMilkAndSugar = new Sugar(new Milk(new Espresso()));
        System.out.println(espressoWithMilkAndSugar.getDescription() + " → " + espressoWithMilkAndSugar.getCost() + " ₸");

        Beverage latteWithCaramelAndCream = new WhippedCream(new Caramel(new Latte()));
        System.out.println(latteWithCaramelAndCream.getDescription() + " → " + latteWithCaramelAndCream.getCost() + " ₸");

        Beverage mochaDeluxe = new WhippedCream(new Milk(new Vanilla(new Mocha())));
        System.out.println(mochaDeluxe.getDescription() + " → " + mochaDeluxe.getCost() + " ₸");

        Beverage teaCombo = new Sugar(new Milk(new Tea()));
        System.out.println(teaCombo.getDescription() + " → " + teaCombo.getCost() + " ₸");
    }
}
