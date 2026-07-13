package Music_Box;

public class Main {
    public static void main(String[] args) {
        try {
            MusicBox musicBox = new MusicBox();
            musicBox.showMenu();
        } catch (FileNotExistException e) {
            System.out.println("Ошибка: файл не найден");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
