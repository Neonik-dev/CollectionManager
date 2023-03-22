package collection;

import java.util.Arrays;

/**
 * A class that describes the genres of musical groups
 */
public enum MusicGenre {
    PROGRESSIVE_ROCK,
    RAP,
    BLUES,
    PUNK_ROCK;
    private static final String values = Arrays.toString(MusicGenre.values());

    /**
     * @return all possible variants of the genre
     */
    public static String allValues() {
        return values;
    }
}
