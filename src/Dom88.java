public class Dom88 {

    //  1. Интерфейс IPaymentProcessor
    interface IPaymentProcessor {
        void processPayment(double amount);
    }

    //  2. Негізгі жүйе: PayPal
    static class PayPalPaymentProcessor implements IPaymentProcessor {
        @Override
        public void processPayment(double amount) {
            System.out.println("[PayPal] Processing payment of " + amount + " ₸...");
            System.out.println("[PayPal] Payment successful ✅");
        }
    }

    //  3. Сторонняя система: Stripe
    static class StripePaymentService {
        public void makeTransaction(double totalAmount) {
            System.out.println("[Stripe] Performing transaction of " + totalAmount + " ₸...");
            System.out.println("[Stripe] Transaction completed ✅");
        }
    }

    //  4. Адаптер: StripePaymentAdapter
    static class StripePaymentAdapter implements IPaymentProcessor {
        private final StripePaymentService stripeService;
        public StripePaymentAdapter(StripePaymentService stripeService) {
            this.stripeService = stripeService;
        }
        @Override
        public void processPayment(double amount) {
            System.out.println("[Adapter] Converting request to Stripe format...");
            stripeService.makeTransaction(amount);
        }
    }

    //  5. Тағы бір сторонняя система: Qiwi
    static class QiwiPaymentService {
        public void payWithQiwi(double money) {
            System.out.println("[Qiwi] Paying " + money + " ₸ through Qiwi...");
            System.out.println("[Qiwi] Payment confirmed ✅");
        }
    }

    //  6. Qiwi адаптері
    static class QiwiPaymentAdapter implements IPaymentProcessor {
        private final QiwiPaymentService qiwiService;
        public QiwiPaymentAdapter(QiwiPaymentService qiwiService) {
            this.qiwiService = qiwiService;
        }
        @Override
        public void processPayment(double amount) {
            System.out.println("[Adapter] Translating call for Qiwi...");
            qiwiService.payWithQiwi(amount);
        }
    }

    //  7. Клиенттік код
    public static void main(String[] args) {
        System.out.println("=== Интернет-дүкен төлем жүйесі ===");
        // PayPal арқылы төлем
        IPaymentProcessor paypal = new PayPalPaymentProcessor();
        paypal.processPayment(5000);
        System.out.println();
        // Stripe арқылы төлем (адаптер арқылы)
        StripePaymentService stripeService = new StripePaymentService();
        IPaymentProcessor stripe = new StripePaymentAdapter(stripeService);
        stripe.processPayment(8500);
        System.out.println();
        // Qiwi арқылы төлем (адаптер арқылы)
        QiwiPaymentService qiwiService = new QiwiPaymentService();
        IPaymentProcessor qiwi = new QiwiPaymentAdapter(qiwiService);
        qiwi.processPayment(4200);
    }
}
