package Music_Box;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MusicCatalog {

    @JsonProperty("tracks")
    private List<MusicTrack> musicTrack;

    public MusicCatalog() {
    }

    public List<MusicTrack> getMusicTrack() {
        return musicTrack;
    }

    public void setMusicTrack(List<MusicTrack> musicTrack) {
        this.musicTrack = musicTrack;
    }
}
