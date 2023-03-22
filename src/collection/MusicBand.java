package collection;

import command.BaseCommand;
import command.Commands;
import exceptions.IncorrectDataEntryExceptions;
import exceptions.IncorrectDataEntryInFileExceptions;
import exceptions.IncorrectLongTypeExceptions;
import exceptions.UnexpectedCommandExceptions;
import utils.CheckLongType;
import utils.InputHandler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * A class for storing collection items. One of the most important classes in the entire program
 */
public class MusicBand implements Comparable<MusicBand>{
    private static final Map<String, BaseCommand> commands = new Commands().getAllCommand();
    private static final HashSet<Integer> ALL_ID = new HashSet<>();
    private static int ID = 0;
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long numberOfParticipants; //Поле может быть null, Значение поля должно быть больше 0
    private String description; //Поле не может быть null
    private java.util.Date establishmentDate; //Поле может быть null
    private MusicGenre genre; //Поле не может быть null
    private Studio studio; //Поле не может быть null

    /**
     * Creates a unique id
     * @return id
     */
    private int generatorId() {
        while (ALL_ID.contains(++ID)) {
        }
        ALL_ID.add(ID);
        return ID;
    }

    /**
     * Allows the user to fill in all the fields of the item, for further saving the item to the collection
     * @return an instance of an element with filled in fields
     * @throws IncorrectDataEntryExceptions If the connection with the user is broken, or the user has entered invalid data
     */
    public MusicBand createNew() throws IncorrectDataEntryExceptions, UnexpectedCommandExceptions{
        id = generatorId();
        creationDate = LocalDate.now();
        return update();
    }

    /**
     * Fills in the fields of this object using an open input data stream
     * @return This Object with the fields filled in
     * @throws IncorrectDataEntryExceptions If the connection with the user is broken, or the user has entered invalid data
     */
    public MusicBand update() throws IncorrectDataEntryExceptions, UnexpectedCommandExceptions {
        Scanner scanner = InputHandler.scanner;
        try {
            do
                System.out.print("Введите название музыкальной группы: " + (name == null ? "" : "(" + name + ") "));
            while (!setName(scanner.nextLine()));

            do
                System.out.print("Введите координаты (в формате: долгота широта): " + (coordinates == null ? "" : "(" + coordinates.x() + " " + coordinates.y() + ") "));
            while (!setCoordinates(scanner.nextLine().split(" ")));

            do
                System.out.print("Введите количество участников группы: " + (numberOfParticipants == null ? "" : "(" + numberOfParticipants + ") "));
            while (!setNumberOfParticipants(scanner.nextLine()));

            do
                System.out.print("Опишите группу: " + (description == null ? "" : "(" + description + ") "));
            while (!setDescription(scanner.nextLine()));

            do
                System.out.print("Введите дату создания группы (в формате дд.мм.гггг): " + (establishmentDate == null ? "" : "(" + new SimpleDateFormat("dd.MM.yyyy").format(establishmentDate) + ") "));
            while (!setEstablishmentDate(scanner.nextLine()));

            do
                System.out.print("Выберите из списка музыкальный жанр группы " + MusicGenre.allValues() + ": " + (genre == null ? "" : "(" + genre + ") "));
            while (!setGenre(scanner.nextLine()));

            System.out.print("Введите адрес студии группы: " + (studio == null || studio.getAddress().isEmpty() ? "" : "(" + studio.getAddress().get() + ") "));
            setStudio(scanner.nextLine());

            return this;
        } catch (NoSuchElementException e) {
            InputHandler.exit = true;
            throw new IncorrectDataEntryExceptions("\nФайл завершился, не завершив заполнение элемента коллекции");
        }
    }

    /**
     * Fills in the fields that are received from the xml file
     * @param data Fields filled in from the file in the Map structure
     * @param coordinates Filled in field coordinates
     * @return This Object with the fields filled in
     * @throws IncorrectDataEntryInFileExceptions If the data from the file was incorrect
     */
    public MusicBand createNewFromFile(HashMap<String, String> data, HashMap<String, String> coordinates) throws IncorrectDataEntryInFileExceptions, UnexpectedCommandExceptions {
        setStudio(data.get("studio"));
        if (setId(data.get("id")) &&
                setCreationDate(data.get("creationDate")) &&
                setName(data.get("name")) &&
                setNumberOfParticipants(data.get("numberOfParticipants")) &&
                setCoordinates(coordinates.values().toArray(new String[0])) &&
                setDescription(data.get("description")) &&
                setEstablishmentDate(data.get("establishmentDate")) &&
                setGenre(data.get("genre")))
            return this;
        throw new IncorrectDataEntryInFileExceptions("Элементы коллекции в файле указаны неверно");
    }

    /**
     * Installing and validating the creationDate field
     * @param date user input data
     * @return false -> when an error occurs, true -> the field has been filled in successfully
     */
    public boolean setCreationDate(String date) throws UnexpectedCommandExceptions {
        try {
            checkCommand(date);
            creationDate = LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Поле creationDate невалидно! (формат: гггг-мм-дд)");
        } catch (NullPointerException e) {
            System.out.println("Поле creationDate не может быть пустым");
        }
        return false;
    }

    /**
     * Installing and validating the id field
     * @param rawId user input data
     * @return false -> when an error occurs, true -> the field has been filled in successfully
     */
    public boolean setId(String rawId) throws UnexpectedCommandExceptions {
        if (rawId == null || rawId.isEmpty()) {
            System.out.println("Поле id не может быть пустым");
            return false;
        }
        checkCommand(rawId);
        try {
            int id = Integer.parseInt(rawId);
            if (id <= 0)
                System.out.println("Число должно быть больше 0");
            else if (ALL_ID.contains(id))
                System.out.println("Поле id не уникальное");
            else {
                this.id = id;
                ALL_ID.add(id);
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Необходимо ввести целое число!");
        }
        return false;
    }

    /**
     * Installing and validating the name field
     * @param name user input data
     * @return false -> when an error occurs, true -> the field has been filled in successfully
     */
    public boolean setName(String name) throws UnexpectedCommandExceptions {
        if (name != null && !name.isEmpty()) {
            checkCommand(name);
            this.name = name;
            return true;
        }
        System.out.println("Название группы не может быть пустым!");
        return false;
    }

    /**
     * Installing and validating the Coordinates field
     * @param coordinates user input data
     * @return false -> when an error occurs, true -> the field has been filled in successfully
     */
    public boolean setCoordinates(String[] coordinates) throws UnexpectedCommandExceptions {
        checkCommand(coordinates[0]);
        if (coordinates.length != 2) {
            System.out.println("Необходимо ввести 2 числа - долготу и широту");
            return false;
        }
        try {
            Double x = Double.parseDouble(coordinates[0]);
            Long y = Long.parseLong(coordinates[1]);
            this.coordinates = new Coordinates(x, y);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Долгота должна быть вещественным числом (в качестве разделителя использовать '.'), " +
                    "а широта - целое число (-2^63 <= широта <= 2^63 -1).");
        }
        return false;
    }

    /**
     * Installing and validating the numberOfParticipants field
     * @param number user input data
     * @return false -> when an error occurs, true -> the field has been filled in successfully
     */
    public boolean setNumberOfParticipants(String number) throws UnexpectedCommandExceptions {
        if (number == null || number.isEmpty()) {
            this.numberOfParticipants = null;
            return true;
        }
        checkCommand(number);
        try {
            long parseNumber = CheckLongType.checkLong(number);
            if (parseNumber > 0) {
                this.numberOfParticipants = parseNumber;
                return true;
            }
            System.out.println("Число должно быть больше 0");
        } catch (IncorrectLongTypeExceptions e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Installing and validating the description field
     * @param description user input data
     * @return false -> when an error occurs, true -> the field has been filled in successfully
     */
    public boolean setDescription(String description) throws UnexpectedCommandExceptions {
        if (description != null && !description.isEmpty()) {
            checkCommand(description);
            this.description = description;
            return true;
        }
        System.out.println("Описание группы не может быть пустым!");
        return false;
    }

    /**
     * Installing and validating the establishmentDate field
     * @param rawDate user input data
     * @return false -> when an error occurs, true -> the field has been filled in successfully
     */
    public boolean setEstablishmentDate(String rawDate) throws UnexpectedCommandExceptions {
        if (rawDate == null || rawDate.isEmpty()) {
            this.establishmentDate = null;
            return true;
        }
        checkCommand(rawDate);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            dateFormat.setLenient(false);
            this.establishmentDate = dateFormat.parse(rawDate);
            if (this.establishmentDate.before(new Date()))
                return true;

            System.out.println("Укажите дату, меньше текущей");
        } catch (ParseException e) {
            System.out.println("К сожалению, дата в неправильном формате");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Installing and validating the genre field
     * @param genre user input data
     * @return false -> when an error occurs, true -> the field has been filled in successfully
     */
    public boolean setGenre(String genre) throws UnexpectedCommandExceptions{
        if (genre == null || genre.isEmpty()) {
            System.out.println("Поле genre не может быть пустым, необходимо выбрать один вариант из списка.");
            return false;
        }
        checkCommand(genre);
        genre = genre.toUpperCase();
        try {
            this.genre = MusicGenre.valueOf(genre);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Необходимо выбрать один из перечисленных вариантов!");
        }
        return false;
    }

    /**
     * Installing and validating the studio field
     * @param address user input data
     */
    public void setStudio(String address) throws UnexpectedCommandExceptions {
        if (address != null && address.isEmpty()) {
            checkCommand(address);
            address = null;
        }
        this.studio = new Studio(address);
    }

    /**
     * Outputs all significant fields of the object and their values
     * @return A string that lists all fields and their values
     */
    @Override
    public String toString() {
        return '[' +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "coordinates = [x = " + coordinates.x() + ", y = " + coordinates.y() + "], " +
                "creationDate = " + creationDate + ", " +
                "numberOfParticipants = " + numberOfParticipants + ", " +
                "description = " + description + ", " +
                "establishmentDate = " + new SimpleDateFormat("dd.MM.yyyy").format(establishmentDate) + ", " +
                "genre = " + genre + ", " +
                "studio = [address: " + (studio.getAddress().isPresent() ? studio.getAddress().get() : "") + "]" +
                "]";
    }

    public String rawData() {
        return name + "\n" +
                coordinates.x() + " " + coordinates.y() + "\n" +
                numberOfParticipants + "\n" +
                description + "\n" +
                new SimpleDateFormat("dd.MM.yyyy").format(establishmentDate) + "\n" +
                genre + "\n" +
                (studio.getAddress().isPresent() ? studio.getAddress().get() : "") + "\n";

    }
    public static HashSet<Integer> getAllId() {
        return ALL_ID;
    }

    public boolean get(int id) {
        return id == this.id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Studio getStudio() {
        return studio;
    }

    public Optional<Date> getEstablishmentDate() {
        return Optional.ofNullable(establishmentDate);
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Optional<Long> getNumberOfParticipants() {
        return Optional.ofNullable(numberOfParticipants);
    }

    public MusicGenre getGenre() {
        return genre;
    }

    /**
     * Default sorting of this object
     * @param o the object to be compared.
     */
    @Override
    public int compareTo(MusicBand o) {
        return Integer.compare(o.getId(), id);
    }

    public void checkCommand(String line) throws UnexpectedCommandExceptions {
        if (commands.containsKey(line.split(" ")[0])) {
            InputHandler.duplicateCommand = line;
            throw new UnexpectedCommandExceptions("Вы вышли из заполнения элемента коллекции");
        }
    }
}
