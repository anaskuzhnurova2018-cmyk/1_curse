package Music_Box;

public class MusicTrack {
    private String name;
    private String author;
    private String file;

    public MusicTrack() {
    }

    public MusicTrack(String name, String author, String file) {
        this.name = name;
        this.author = author;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
