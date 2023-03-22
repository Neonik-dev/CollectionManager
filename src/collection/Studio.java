package collection;

import java.util.Optional;

/**
 * Describes the music group's studio
 */
public class Studio {
    private final String address; //Поле может быть null

    /**
     * When initializing the class, sets the value to the address field
     * @param address The address of the band's studio
     */
    public Studio(String address) {
        this.address = address;
    }

    /**
     * Method for getting the address of the band's studio
     * @return the address of the band's studio
     */
    public Optional<String> getAddress() {
        return Optional.ofNullable(address);
    }
}