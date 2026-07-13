package Music_Box;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MusicBox {

    private String catalogFileName = "catalog.json";
    private Clip currentClip;
    private Integer currentTrack;
    private MusicCatalog catalog;

    public MusicBox() {
        this.catalog = loadCatalog();
        if (catalog == null) {
            catalog = new MusicCatalog();
        }
    }

    public void showMenu() throws FileNotExistException {
        System.out.println("\nМЕНЮ");
        System.out.println("1. Загрузка каталога");
        System.out.println("2. Ввод нового трека");
        System.out.println("3. Удаление трека");
        System.out.println("4. Просмотр всех треков");
        System.out.println("5. Поиск трека по номеру");
        System.out.println("6. Поиск трека по названию");
        System.out.println("7. Поиск трека по автору/исполнителю");
        System.out.println("8. Проигрывание трека");
        System.out.println("9. Остановка трека");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
        userChoiceHandler();
    }

    public void userChoiceHandler() throws FileNotExistException {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            int number = scanner.nextInt();
            scanner.nextLine();
            switch (number) {
                case 1:
                    loadCatalog();
                    break;
                case 2:
                    System.out.println("Введите название трека, автора трека и путь к файлу через enter");
                    String name = scanner.nextLine();
                    String author = scanner.nextLine();
                    String file = scanner.nextLine();
                    addTrack(name, author, file);
                    break;

                case 3:
                    System.out.println("Введите номер трека");
                    int num1 = scanner.nextInt();
                    scanner.nextLine();
                    delete(num1);
                    break;

                case 4:
                    showAllTracks();
                    break;

                case 5:
                    System.out.println("Введите номер трека");
                    int num2 = scanner.nextInt();
                    scanner.nextLine();
                    findByNumber(num2);
                    break;

                case 6:
                    System.out.println("Введите название трека");
                    String name1 = scanner.nextLine();
                    findByName(name1);
                    break;

                case 7:
                    System.out.println("Введите автора трека");
                    String author1 = scanner.nextLine();
                    findByAuthor(author1);
                    break;

                case 8:
                    System.out.println("Введите номер трека");
                    int num3 = scanner.nextInt();
                    scanner.nextLine();
                    play(num3);
                    break;

                case 9:
                    stopClip();
                    break;

                case 0:
                    System.out.println("Программа завершена");
                    return;

            }
        }
    }

    public Clip play(int trackNumber) {
        // запустить воспроизведение в отдельном потоке
        try {
            MusicTrack track = findByNumber(trackNumber);
            if (track == null) {
                System.out.println("Трек не найден");
                return null;
            }
            File audioFile = new File(track.getFile());
            if (!audioFile.exists()) {
                System.out.println("Файл не найден: " + track.getFile());
                return null;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            //останавливаем предыдущий трек
            if (currentClip != null) {
                if (currentClip.isRunning()) {
                    currentClip.stop();
                }
                currentClip.close();
            }

            currentTrack = trackNumber;

            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);
            currentClip.start();

            //добавляем Listener, чтобы закрыть stream после завершения
            currentClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    try {
                        audioStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            return currentClip;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public MusicCatalog loadCatalog() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(catalogFileName);
            if (!file.exists()) {
                System.out.println("Каталог не найден, создаем новый");
                return new MusicCatalog();
            }
            return mapper.readValue(file, MusicCatalog.class); //десериализация
        } catch (IOException e) {
            e.printStackTrace();
            return new MusicCatalog();
        }
    }

    public void saveCatalog() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(catalogFileName), catalog); //сериализация
            System.out.println("Каталог сохранен");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void showAllTracks() {
        System.out.println("Список всех треков:");
        if (catalog.getMusicTrack() != null) {
            catalog.getMusicTrack().stream()
                    .filter(track -> track != null)
                    .forEach(track -> System.out.println(track.getName() + " (исполняет: " + track.getAuthor() + ")"));
        } else {
            System.out.println("Пустой каталог");
        }
    }

    public List<MusicTrack> findByName(String name) {
        MusicCatalog catalog = loadCatalog();
        return catalog.getMusicTrack().stream()
                .filter(track ->
                        track.getName()
                                .toLowerCase()
                                .contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public MusicTrack findByNumber(int trackNumber) {
        if (catalog == null || catalog.getMusicTrack() == null) {
            return null;
        }
        int index = trackNumber - 1;
        if (index < 0 || index >= catalog.getMusicTrack().size()) {
            System.out.println("Трека с таким номером не существует");
            return null;
        }
        return catalog.getMusicTrack().get(index);
    }

    public List<MusicTrack> findByAuthor(String author) {
        if (catalog == null || catalog.getMusicTrack() == null) {
            return new ArrayList<>();
        }
        return catalog.getMusicTrack().stream()
                .filter(track -> track != null && track.getAuthor() != null)
                .filter(track -> track.getAuthor()
                        .toLowerCase()
                        .contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void stopClip() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            System.out.println("Воспроизведение остановлено");
        } else {
            System.out.println("Ничего не воспроизводится");
        }
    }

    public void delete(int trackNumber) throws FileNotExistException {
        List<MusicTrack> traks = this.catalog.getMusicTrack();
        if (traks == null || traks.isEmpty()) {
            System.out.println("Каталог пуст.");
            showMenu();
            return;
        }
        int index = trackNumber - 1;
        if (index < 0 || index >= traks.size()) {
            System.out.println("Трека с таким номером нет.");
            showMenu();
            return;
        }
        MusicTrack deletedTrack = traks.remove(index);
        saveCatalog();
        System.out.println("Трек удалён: " +
                deletedTrack.getName() +
                " - " + deletedTrack.getAuthor());
    }

    public MusicTrack addTrack(String name, String author, String file) throws FileNotExistException {
        Path path = Paths.get(file);
        if (!Files.exists(path)) {
            throw new FileNotExistException();
        }

        MusicTrack toAdd = new MusicTrack(name, author, file);

        if (this.catalog.getMusicTrack() == null) {
            this.catalog.setMusicTrack(new ArrayList<>());
        }

        this.catalog.getMusicTrack().add(toAdd);
        saveCatalog();
        return toAdd;
    }
}