import java.io.*;
import java.util.HashMap;
import java.util.Map;

class ConfigurationManager {
    private static volatile ConfigurationManager instance; // жалғыз объект
    private Map<String, String> settings;

    // Приватный конструктор
    private ConfigurationManager() {
        settings = new HashMap<>();
    }

    // Потокобезопасный Singleton
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    // Настройка қосу
    public void setSetting(String key, String value) {
        settings.put(key, value);
    }

    // Настройка алу
    public String getSetting(String key) {
        return settings.get(key);
    }

    // Файлға сақтау
    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : settings.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Файлдан жүктеу
    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            settings.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    settings.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ConfigurationManager config1 = ConfigurationManager.getInstance();
        config1.setSetting("language", "Kazakh");
        config1.setSetting("theme", "Dark");

        config1.saveToFile("config.txt");
        ConfigurationManager config2 = ConfigurationManager.getInstance();
        System.out.println("Theme from config2: " + config2.getSetting("theme"));
        config2.loadFromFile("config.txt");
        System.out.println("Language: " + config2.getSetting("language"));
    }
}
