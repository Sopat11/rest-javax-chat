package it.sosinski.chat.utils;

public class CommandsUtils {

    public static boolean isServerCommand(String text) {
        return text != null && text.startsWith("cht");
    }

    public static boolean isAskingToCreateChannel(String text) {
        return text.contains("--create");
    }

    public static boolean isAskingToPrintChannels(String text) {
        return text.contains("--channels");
    }

    public static boolean hasPrivateFlag(String text) {
        return text.contains("--p");
    }
}
