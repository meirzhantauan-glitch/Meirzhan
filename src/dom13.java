// dom13.java   ← осы файлдың аты дәл осылай болуы керек
import java.util.Scanner;

public class dom13 {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Билет автоматы іске қосылды (Java версия)\n");

        while (true) {
            System.out.println("Қазіргі күй: " + machine.getCurrentState().getClass().getSimpleName());
            System.out.println("1 — Билет таңдау");
            System.out.println("2 — Ақша салу");
            System.out.println("3 — Операцияны тоқтату");
            System.out.println("0 — Шығу");
            System.out.print("Таңдаңыз: ");
            String choice = scanner.nextLine();

            if (choice.equals("0")) break;

            switch (choice) {
                case "1" -> machine.selectTicket();
                case "2" -> {
                    System.out.print("Қанша теңге саласыз? ");
                    try {
                        int amount = Integer.parseInt(scanner.nextLine());
                        machine.insertMoney(amount);
                    } catch (NumberFormatException e) {
                        System.out.println("Дұрыс сан енгізіңіз!");
                    }
                }
                case "3" -> machine.cancelTransaction();
                default -> System.out.println("Қате таңдау!");
            }
            System.out.println();
        }
        scanner.close();
    }
}

// =========================================================
// VendingMachine және барлық күйлер (State Pattern)
// =========================================================

class VendingMachine {
    private State currentState;
    private int insertedMoney = 0;
    private final int ticketPrice = 500;

    private final State idleState;
    private final State waitingForMoneyState;
    private final State moneyReceivedState;
    private final State ticketDispensedState;
    private final State transactionCanceledState;

    public VendingMachine() {
        idleState = new IdleState(this);
        waitingForMoneyState = new WaitingForMoneyState(this);
        moneyReceivedState = new MoneyReceivedState(this);
        ticketDispensedState = new TicketDispensedState(this);
        transactionCanceledState = new TransactionCanceledState(this);

        currentState = idleState;
    }

    public State getCurrentState() { return currentState; }
    public void setState(State state) { this.currentState = state; }

    public int getInsertedMoney() { return insertedMoney; }
    public void addMoney(int amount) { this.insertedMoney += amount; }
    public void resetMoney() { this.insertedMoney = 0; }
    public int getTicketPrice() { return ticketPrice; }

    public State getIdleState() { return idleState; }
    public State getWaitingForMoneyState() { return waitingForMoneyState; }
    public State getMoneyReceivedState() { return moneyReceivedState; }
    public State getTicketDispensedState() { return ticketDispensedState; }
    public State getTransactionCanceledState() { return transactionCanceledState; }

    public void selectTicket() { currentState.selectTicket(); }
    public void insertMoney(int amount) { currentState.insertMoney(amount); }
    public void dispenseTicket() { currentState.dispenseTicket(); }
    public void cancelTransaction() { currentState.cancelTransaction(); }
}

// =========================================================
// State интерфейсі және барлық күйлер
// =========================================================

interface State {
    void selectTicket();
    void insertMoney(int amount);
    void dispenseTicket();
    void cancelTransaction();
}

class IdleState implements State {
    private final VendingMachine machine;
    public IdleState(VendingMachine machine) { this.machine = machine; }

    @Override public void selectTicket() {
        System.out.println("Билет таңдалды. Ақша салыңыз.");
        machine.setState(machine.getWaitingForMoneyState());
    }
    @Override public void insertMoney(int amount) { System.out.println("Алдымен билет таңдаңыз!"); }
    @Override public void dispenseTicket() { System.out.println("Ешнәрсе таңдалмаған."); }
    @Override public void cancelTransaction() { System.out.println("Операция жоқ."); }
}

class WaitingForMoneyState implements State {
    private final VendingMachine machine;
    public WaitingForMoneyState(VendingMachine machine) { this.machine = machine; }

    @Override public void selectTicket() { System.out.println("Билет әлдеқашан таңдалған."); }

    @Override public void insertMoney(int amount) {
        machine.addMoney(amount);
        System.out.println(amount + " теңге салынды. Барлығы: " + machine.getInsertedMoney() + "/" + machine.getTicketPrice());

        if (machine.getInsertedMoney() >= machine.getTicketPrice()) {
            System.out.println("Жеткілікті ақша! Билет беріледі...");
            machine.setState(machine.getMoneyReceivedState());
            machine.dispenseTicket();
        }
    }

    @Override public void dispenseTicket() { System.out.println("Ақша жеткіліксіз!"); }

    @Override public void cancelTransaction() {
        if (machine.getInsertedMoney() > 0) {
            System.out.println("Операция тоқтатылды. " + machine.getInsertedMoney() + " теңге қайтарылды.");
            machine.resetMoney();
        } else {
            System.out.println("Операция тоқтатылды.");
        }
        machine.setState(machine.getTransactionCanceledState());
    }
}

class MoneyReceivedState implements State {
    private final VendingMachine machine;
    public MoneyReceivedState(VendingMachine machine) { this.machine = machine; }

    @Override public void selectTicket() { System.out.println("Билет таңдалған."); }
    @Override public void insertMoney(int amount) { System.out.println("Ақша қабылданбайды – билет беріледі."); }

    @Override public void dispenseTicket() {
        System.out.println("Билет беріледі... Рахмет!");
        int change = machine.getInsertedMoney() - machine.getTicketPrice();
        if (change > 0) System.out.println("Сдача: " + change + " теңге");
        machine.resetMoney();
        machine.setState(machine.getTicketDispensedState());
    }

    @Override public void cancelTransaction() {
        System.out.println("Тым кеш – билет беріледі.");
        dispenseTicket();
    }
}

class TicketDispensedState implements State {
    private final VendingMachine machine;
    public TicketDispensedState(VendingMachine machine) {
        this.machine = machine;
        System.out.println("Билет шықты! 3 секундтан кейін автомат босайды...");

        new Thread(() -> {
            try { Thread.sleep(3000); }
            catch (InterruptedException ignored) {}
            System.out.println("Автомат бос! Жаңа клиентті күтеді.\n");
            machine.setState(machine.getIdleState());
        }).start();
    }

    @Override public void selectTicket() { System.out.println("Операция аяқталды."); }
    @Override public void insertMoney(int amount) { System.out.println("Операция аяқталды."); }
    @Override public void dispenseTicket() { System.out.println("Билет берілді."); }
    @Override public void cancelTransaction() { System.out.println("Операция аяқталды."); }
}

class TransactionCanceledState implements State {
    private final VendingMachine machine;
    public TransactionCanceledState(VendingMachine machine) {
        this.machine = machine;
        new Thread(() -> {
            try { Thread.sleep(2000); }
            catch (InterruptedException ignored) {}
            System.out.println("Автомат бос күйге оралды.\n");
            machine.setState(machine.getIdleState());
        }).start();
    }

    @Override public void selectTicket() { System.out.println("Операция тоқтатылды."); }
    @Override public void insertMoney(int amount) { System.out.println("Операция тоқтатылды."); }
    @Override public void dispenseTicket() { System.out.println("Операция тоқтатылды."); }
    @Override public void cancelTransaction() { System.out.println("Операция тоқтатылды."); }
}