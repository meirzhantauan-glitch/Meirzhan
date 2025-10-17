import java.util.*;

// 1) Интерфейс IObserver
interface IObserver {
    void update(String currency, double rate);
}

// 2) Интерфейс ISubject
interface ISubject {
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}

// 3) Субъект класы CurrencyExchange
class CurrencyExchange implements ISubject {
    private final Map<String, Double> rates = new HashMap<>();
    private final List<IObserver> observers = new ArrayList<>();

    public void setRate(String currency, double rate) {
        rates.put(currency, rate);
        System.out.println("\n💱 " + currency + " бағамы жаңартылды: " + rate);
        notifyObservers();
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
        System.out.println("👤 Бақылаушы қосылды: " + observer.getClass().getSimpleName());
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
        System.out.println("🚫 Бақылаушы өшірілді: " + observer.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            for (String currency : rates.keySet()) {
                observer.update(currency, rates.get(currency));
            }
        }
    }
}

// 4) Наблюдатель кластар
class Trader implements IObserver {
    @Override
    public void update(String currency, double rate) {
        System.out.println("💼 Трейдер жаңа курс алды: " + currency + " = " + rate);
    }
}

class NewsAgency implements IObserver {
    @Override
    public void update(String currency, double rate) {
        System.out.println("📰 Жаңалық агенттігі хабарлады: " + currency + " бағамы өзгерді: " + rate);
    }
}

class AnalyticsService implements IObserver {
    @Override
    public void update(String currency, double rate) {
        System.out.println("📊 Аналитика қызметі есепке алды: " + currency + " -> " + rate);
    }
}

// 5) Клиент коды
public class dom6Observer {
    public static void main(String[] args) {
        CurrencyExchange exchange = new CurrencyExchange();

        // 3 түрлі бақылаушы
        IObserver trader = new Trader();
        IObserver news = new NewsAgency();
        IObserver analytics = new AnalyticsService();

        exchange.addObserver(trader);
        exchange.addObserver(news);
        exchange.addObserver(analytics);

        // Валюта бағамдарын жаңарту
        exchange.setRate("USD", 476.5);
        exchange.setRate("EUR", 502.3);

        // Бір бақылаушыны алып тастау
        exchange.removeObserver(news);

        // Тағы жаңарту
        exchange.setRate("USD", 480.2);
    }
}

