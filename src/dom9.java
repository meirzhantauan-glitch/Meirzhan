
package dom9;
class TV {
    public void on() {
        System.out.println("Телевизор қосылды");
    }
    public void off() {
        System.out.println("Телевизор өшірілді");
    }

    public void setInputChannel(String source) {
        System.out.println("Телевизор: кіріс '" + source + "' таңдалды");
    }
}
class AudioSystem {
    public void on() {
        System.out.println("Аудиожүйе қосылды");
    }
    public void off() {
        System.out.println("Аудиожүйе өшірілді");
    }
    public void setVolume(int level) {
        System.out.println("Аудиожүйе: дыбыс деңгейі " + level + " болып орнатылды");
    }
}

class DVDPlayer {
    public void on() {
        System.out.println("DVD ойнатқыш қосылды");
    }
    public void off() {
        System.out.println("DVD ойнатқыш өшірілді");
    }
    public void play() {
        System.out.println("DVD: ойнату басталды");
    }
    public void pause() {
        System.out.println("DVD: кідіртілді");
    }
    public void stop() {
        System.out.println("DVD: тоқтатылды");
    }
}
class GameConsole {
    public void on() {
        System.out.println("Ойын консолі қосылды");
    }
    public void off() {
        System.out.println("Ойын консолі өшірілді");
    }
    public void startGame(String game) {
        System.out.println("Ойын '" + game + "' консольде іске қосылды");
    }
}
class HomeTheaterFacade {
    private TV tv;
    private AudioSystem audio;
    private DVDPlayer dvd;
    private GameConsole console;
    public HomeTheaterFacade(TV tv, AudioSystem audio, DVDPlayer dvd, GameConsole console) {
        this.tv = tv;
        this.audio = audio;
        this.dvd = dvd;
        this.console = console;
    }
    public void watchMovie() {
        System.out.println("\nФИЛЬМ КӨРУ БАСТАЛДЫ");
        tv.on();
        audio.on();
        audio.setVolume(15);
        tv.setInputChannel("HDMI 1 (DVD)");
        dvd.on();
        dvd.play();
    }
    public void listenToMusic() {
        System.out.println("\nМУЗЫКА ТЫҢДАУ БАСТАЛДЫ");
        tv.on();
        audio.on();
        audio.setVolume(20);
        tv.setInputChannel("AUX");
        System.out.println("Музыка аудиожүйе арқылы ойнатылуда...");
    }
    public void playGame(String game) {
        System.out.println("\nОЙЫН БАСТАЛДЫ");
        tv.on();
        console.on();
        tv.setInputChannel("HDMI 2 (Console)");
        console.startGame(game);
    }
    public void endSession() {
        System.out.println("\nЖҮЙЕ ӨШІРІЛУДЕ...");
        dvd.stop();
        dvd.off();
        console.off();
        audio.off();
        tv.off();
    }
    public void setVolume(int level) {
        audio.setVolume(level);
    }
}

public class dom9 {
    public static void main(String[] args) {
        // Құрылғыларды құру
        TV tv = new TV();
        AudioSystem audio = new AudioSystem();
        DVDPlayer dvd = new DVDPlayer();
        GameConsole console = new GameConsole();
        // Фасадты құру
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(tv, audio, dvd, console);
        // 1. Фильм көру
        homeTheater.watchMovie();
        homeTheater.setVolume(18);
        homeTheater.endSession();
        // 2. Ойын ойнау
        homeTheater.playGame("FIFA 2025");
        // 3. Музыка тыңдау
        homeTheater.listenToMusic();
        homeTheater.endSession();
    }
}