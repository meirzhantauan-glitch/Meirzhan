import java.util.ArrayList;
import java.util.List;
// Общий компонент
abstract class FileSystemComponent {
    protected String name;
    public FileSystemComponent(String name) {
        this.name = name;
    }
    public abstract void display(String indent);
    public abstract long getSize();

    // Методы по умолчанию для листьев (файлов)
    public void add(FileSystemComponent component) {
        throw new UnsupportedOperationException("Нельзя добавить в файл");
    }
    public void remove(FileSystemComponent component) {
        throw new UnsupportedOperationException("Нельзя удалить из файла");
    }
}

// Лист: Файл
class File extends FileSystemComponent {
    private long size;
    public File(String name, long size) {
        super(name);
        this.size = size;
    }
    @Override
    public void display(String indent) {
        System.out.println(indent + "Файл: " + name + " (" + size + " KB)");
    }
    @Override
    public long getSize() {
        return size;
    }
}

// Компоновщик: Папка
class Directory extends FileSystemComponent {
    private List<FileSystemComponent> children = new ArrayList<>();
    public Directory(String name) {
        super(name);
    }

    public void add(FileSystemComponent component) {
        if (!children.contains(component)) {
            children.add(component);
            System.out.println("Добавлено в '" + name + "': " + component.name);
        } else {
            System.out.println("Уже существует: " + component.name);
        }
    }
    public void remove(FileSystemComponent component) {
        if (children.remove(component)) {
            System.out.println("Удалено из '" + name + "': " + component.name);
        } else {
            System.out.println("Не найдено: " + component.name);
        }
    }
    @Override
    public void display(String indent) {
        System.out.println(indent + "Папка: " + name);
        for (FileSystemComponent child : children) {
            child.display(indent + "  ");
        }
    }
    @Override
    public long getSize() {
        long total = 0;
        for (FileSystemComponent child : children) {
            total += child.getSize();
        }
        return total;
    }
}
public class dom99 {
    public static void main(String[] args) {
        // Создание файлов
        File file1 = new File("document.pdf", 120);
        File file2 = new File("photo.jpg", 350);
        File file3 = new File("song.mp3", 5200);
        File file4 = new File("video.mp4", 125000);

        // Создание папок
        Directory docs = new Directory("Документы");
        Directory media = new Directory("Медиа");
        Directory root = new Directory("Корневая папка");

        // Построение иерархии
        docs.add(file1);
        docs.add(file2);
        media.add(file3);
        media.add(file4);
        root.add(docs);
        root.add(media);
        root.add(file1);
        System.out.println("\n Структура файловой системы ");
        root.display("");
        System.out.println("\nОбщий размер '" + root.name + "': " + root.getSize() + " KB");
        System.out.println("\nУдаляем папку 'Медиа'...");
        root.remove(media);
        System.out.println("\n После удаления ");
        root.display("");
        System.out.println("Общий размер: " + root.getSize() + " KB");
    }
}