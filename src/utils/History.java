package utils;

import java.util.LinkedList;
import java.util.Queue;

public class History {
    private static final Queue<String> history = new LinkedList<>();

    public static void add(String command) {
        if (history.size() == 13)
            history.poll();
        history.add(command);
    }

    public static String printHistory() {
        StringBuilder text = new StringBuilder();
        int commandNumber = 1;
        for (String command : history) {
            text.append(commandNumber).append(" - ").append(command).append("\n");
            commandNumber++;
        }
        text.deleteCharAt(text.length() - 1);
        return text.toString();
    }

    public void clear() {
        history.clear();
    }
}
