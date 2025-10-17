import java.util.Scanner;

// 1) Интерфейс IPaymentStrategy
interface IPaymentStrategy {
    void pay(double amount);
}

// 2) Әртүрлі стратегиялар
class CreditCardPayment implements IPaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("✅ Банктік карта арқылы төлем жасалды: " + amount + " ₸");
    }
}

class PayPalPayment implements IPaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("✅ PayPal арқылы төлем жасалды: " + amount + " ₸");
    }
}

class CryptoPayment implements IPaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("✅ Криптовалюта арқылы төлем жасалды: " + amount + " ₸");
    }
}

// 3) Контекст класы PaymentContext
class PaymentContext {
    private IPaymentStrategy paymentStrategy;

    public void setPaymentStrategy(IPaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(double amount) {
        if (paymentStrategy != null) {
            paymentStrategy.pay(amount);
        } else {
            System.out.println("⚠ Төлем стратегиясы таңдалмаған!");
        }
    }
}

// 4) Клиент коды
public class dom6strategia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PaymentContext context = new PaymentContext();

        System.out.print("Төлем сомасын енгізіңіз: ");
        double amount = scanner.nextDouble();

        System.out.println("Төлем түрін таңдаңыз:");
        System.out.println("1 - Банктік карта");
        System.out.println("2 - PayPal");
        System.out.println("3 - Криптовалюта");
        System.out.print("Сіздің таңдауыңыз: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> context.setPaymentStrategy(new CreditCardPayment());
            case 2 -> context.setPaymentStrategy(new PayPalPayment());
            case 3 -> context.setPaymentStrategy(new CryptoPayment());
            default -> {
                System.out.println("❌ Қате таңдау!");
                return;
            }
        }

        context.executePayment(amount);
        scanner.close();
    }
}
