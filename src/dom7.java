// Interface for commands
interface ICommand {
    void execute();
    void undo();
}
// Receiver classes
class Light {
    public void turnOn() {
        System.out.println("Light is ON");
    }
    public void turnOff() {
        System.out.println("Light is OFF");
    }
}
class Door {
    public void open() {
        System.out.println("Door is OPEN");
    }
    public void close() {
        System.out.println("Door is CLOSED");
    }
}
class Thermostat {
    private int temperature = 20;
    public void increaseTemperature() {
        temperature++;
        System.out.println("Temperature increased to " + temperature + "°C");
    }
    public void decreaseTemperature() {
        temperature--;
        System.out.println("Temperature decreased to " + temperature + "°C");
    }
}
class TV {
    public void turnOn() {
        System.out.println("TV is ON");
    }
    public void turnOff() {
        System.out.println("TV is OFF");
    }
}
// Concrete commands
class LightOnCommand implements ICommand {
    private Light light;
    public LightOnCommand(Light light) {
        this.light = light;
    }
    @Override
    public void execute() {
        light.turnOn();
    }
    @Override
    public void undo() {
        light.turnOff();
    }
}
class LightOffCommand implements ICommand {
    private Light light;
    public LightOffCommand(Light light) {
        this.light = light;
    }
    @Override
    public void execute() {
        light.turnOff();
    }
    @Override
    public void undo() {
        light.turnOn();
    }
}
class DoorOpenCommand implements ICommand {
    private Door door;
    public DoorOpenCommand(Door door) {
        this.door = door;
    }
    @Override
    public void execute() {
        door.open();
    }
    @Override
    public void undo() {
        door.close();
    }
}
class DoorCloseCommand implements ICommand {
    private Door door;
    public DoorCloseCommand(Door door) {
        this.door = door;
    }
    @Override
    public void execute() {
        door.close();
    }
    @Override
    public void undo() {
        door.open();
    }
}
class ThermostatIncreaseCommand implements ICommand {
    private Thermostat thermostat;
    public ThermostatIncreaseCommand(Thermostat thermostat) {
        this.thermostat = thermostat;
    }
    @Override
    public void execute() {
        thermostat.increaseTemperature();
    }
    @Override
    public void undo() {
        thermostat.decreaseTemperature();
    }
}
class ThermostatDecreaseCommand implements ICommand {
    private Thermostat thermostat;
    public ThermostatDecreaseCommand(Thermostat thermostat) {
        this.thermostat = thermostat;
    }
    @Override
    public void execute() {
        thermostat.decreaseTemperature();
    }
    @Override
    public void undo() {
        thermostat.increaseTemperature();
    }
}
class TVOnCommand implements ICommand {
    private TV tv;
    public TVOnCommand(TV tv) {
        this.tv = tv;
    }
    @Override
    public void execute() {
        tv.turnOn();
    }
    @Override
    public void undo() {
        tv.turnOff();
    }
}
// Invoker class
class Invoker {
    private ICommand[] commands;
    private java.util.Stack<ICommand> history;
    public Invoker(int slots) {
        commands = new ICommand[slots];
        history = new java.util.Stack<>();
    }
    public void setCommand(int slot, ICommand command) {
        if (slot >= 0 && slot < commands.length) {
            commands[slot] = command;
        } else {
            System.out.println("Invalid slot: " + slot);
        }
    }
    public void executeCommand(int slot) {
        if (slot >= 0 && slot < commands.length && commands[slot] != null) {
            commands[slot].execute();
            history.push(commands[slot]);
        } else {
            System.out.println("No command assigned to slot " + slot);
        }
    }
    public void undoLastCommand() {
        if (history.isEmpty()) {
            System.out.println("No commands to undo");
            return;
        }
        ICommand command = history.pop();
        command.undo();
    }
}
// Client code
public class dom7 {
    public static void main(String[] args) {
        Light light = new Light();
        Door door = new Door();
        Thermostat thermostat = new Thermostat();
        TV tv = new TV();
        Invoker invoker = new Invoker(5);
        invoker.setCommand(0, new LightOnCommand(light));
        invoker.setCommand(1, new DoorOpenCommand(door));
        invoker.setCommand(2, new ThermostatIncreaseCommand(thermostat));
        invoker.setCommand(3, new ThermostatDecreaseCommand(thermostat));
        invoker.setCommand(4, new TVOnCommand(tv));
        // Test commands
        invoker.executeCommand(0); // Light ON
        invoker.executeCommand(1); // Door OPEN
        invoker.executeCommand(2); // Temperature increase
        invoker.executeCommand(3); // Temperature decrease
        invoker.executeCommand(4); // TV ON
        // Test undo
        invoker.undoLastCommand(); // Undo TV ON
        invoker.undoLastCommand(); // Undo Temperature decrease
        invoker.undoLastCommand(); // Undo Temperature increase
        // Test invalid slot
        invoker.executeCommand(5); // Invalid slot
        // Test no commands to undo
        invoker.undoLastCommand();
        invoker.undoLastCommand();
        invoker.undoLastCommand(); // No commands to undo
    }
}
