package collection;

import exceptions.IncorrectDataEntryExceptions;
import exceptions.IncorrectDataEntryInFileExceptions;
import exceptions.UnexpectedCommandExceptions;
import utils.WalWriter;


import java.util.*;

public class MusicBandLinkedList {
    private static final LinkedList<MusicBand> musicBandLinkedList = new LinkedList<>();
    private static final Date dateCreated = new Date();

    public static LinkedList<MusicBand> getMusicBandLinkedList() {
        return (LinkedList<MusicBand>) musicBandLinkedList.clone();
    }

    public static String getDateCreated() {
        return dateCreated.toString();
    }

    public void clear() {
        musicBandLinkedList.clear();
        MusicBand.getAllId().clear();
    }

    public void add() throws IncorrectDataEntryExceptions {
        try {
            MusicBand newInstance = new MusicBand().createNew();
            WalWriter.openFile("add" + "\n" + newInstance.rawData());
            musicBandLinkedList.add(newInstance);
        } catch (UnexpectedCommandExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public void add(HashMap<String, String> data, HashMap<String, String> coordinates) throws IncorrectDataEntryInFileExceptions {
        try {
            musicBandLinkedList.add(new MusicBand().createNewFromFile(data, coordinates));
        } catch (UnexpectedCommandExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int id) throws IncorrectDataEntryExceptions {
        try {
            MusicBand updateMusicBand = getMusicBandLinkedListById(id);
            updateMusicBand.update();
            WalWriter.openFile("update " + id + "\n" + updateMusicBand.rawData());
        } catch (UnexpectedCommandExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete() {
        if (musicBandLinkedList.isEmpty())
            System.out.println("Коллекция пустая, удалять нечего.");
        else {
            WalWriter.openFile("remove_head " + "\n");
            MusicBand deletedElement = musicBandLinkedList.poll();
            System.out.println("Remove element -> " + deletedElement.toString());
            MusicBand.getAllId().remove(deletedElement.getId());
        }
    }

    public void delete(int id) {
        WalWriter.openFile("remove_by_id " + id + "\n");
        musicBandLinkedList.remove(getMusicBandLinkedListById(id));
        MusicBand.getAllId().remove(id);
    }

    public void show() {
        for (MusicBand musicBand : musicBandLinkedList) {
            System.out.println(musicBand.toString());
        }
    }

    private MusicBand getMusicBandLinkedListById(int id) {
        MusicBand musicBand = null;
        for (MusicBand elem : musicBandLinkedList) {
            if (elem.get(id)) {
                musicBand = elem;
                break;
            }
        }
        return musicBand;
    }

    public void UniqueNumberOfParticipants() {
        Set<Long> uniqueParticipants = new HashSet<>();
        for (MusicBand musicBand : musicBandLinkedList) {
            musicBand.getNumberOfParticipants().ifPresent(uniqueParticipants::add);
        }
        System.out.println(uniqueParticipants);
    }

    public int countNumberOfParticipants(long number) {
        int count = 0;
        for (MusicBand musicBand : musicBandLinkedList) {
            Optional<Long> participants = musicBand.getNumberOfParticipants();
            if (participants.isPresent())
                count += participants.get() < number ? 1 : 0;
        }
        return count;
    }

    public String toString() {
        return musicBandLinkedList.toString();
    }
}
