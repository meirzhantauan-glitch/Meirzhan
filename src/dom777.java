import java.util.ArrayList;
import java.util.List;
// Mediator interface
interface IMediator {
    void sendMessage(String message, User sender);
    void sendPrivateMessage(String message, User sender, User receiver);
    void addUser(User user);
    void removeUser(User user);
}
// Concrete mediator
class ChatRoom implements IMediator {
    private List<User> users = new ArrayList<>();
    @Override
    public void sendMessage(String message, User sender) {
        if (!users.contains(sender)) {
            System.out.println("Error: " + sender.getName() + " is not in the chat");
            return;
        }
        for (User user : users) {
            if (user != sender) {
                user.receiveMessage(message);
            }
        }
    }
    @Override
    public void sendPrivateMessage(String message, User sender, User receiver) {
        if (!users.contains(sender)) {
            System.out.println("Error: " + sender.getName() + " is not in the chat");
            return;
        }
        if (!users.contains(receiver)) {
            System.out.println("Error: " + receiver.getName() + " is not in the chat");
            return;
        }
        receiver.receiveMessage("Private from " + sender.getName() + ": " + message);
    }
    @Override
    public void addUser(User user) {
        users.add(user);
        sendMessage(user.getName() + " has joined the chat", user);
    }
    @Override
    public void removeUser(User user) {
        if (users.remove(user)) {
            sendMessage(user.getName() + " has left the chat", user);
        }
    }
}
// User class
class User {
    private String name;
    private IMediator mediator;
    public User(String name, IMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
    public String getName() {
        return name;
    }
    public void sendMessage(String message) {
        mediator.sendMessage(message, this);
    }
    public void sendPrivateMessage(String message, User receiver) {
        mediator.sendPrivateMessage(message, this, receiver);
    }
    public void receiveMessage(String message) {
        System.out.println(name + " received: " + message);
    }
}

// Client code
public class dom777 {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();
        User alice = new User("Alice", chatRoom);
        User bob = new User("Bob", chatRoom);
        User charlie = new User("Charlie", chatRoom);
        // Test adding users
        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);
        // Test group messages
        alice.sendMessage("Hello everyone!");
        // Test private messages
        bob.sendPrivateMessage("Hi Alice!", alice);
        // Test removing user
        chatRoom.removeUser(charlie);
        // Test error handling
        charlie.sendMessage("Can you hear me?"); // Should show error
        bob.sendPrivateMessage("Hey Charlie!", charlie); // Should show error
    }
}
