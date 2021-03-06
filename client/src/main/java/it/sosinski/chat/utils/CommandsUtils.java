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

    public static boolean isAskingToJoinChannel(String text) {
        return text.contains("--join");
    }

    public static boolean isAskingToLeaveChannel(String text) {
        return text.contains("--leave");
    }

    public static boolean isAskingForLoggedUsers(String text) {
        return text.contains("--online");
    }

    public static boolean isAskingToPrintHistory(String text) {
        return text.contains("--history");
    }

    public static boolean isSendingFile(String text) {
        return text.startsWith("\\f");
    }

    public static boolean isAskingToAllowChatWorker(String text) {
        return text.contains("--allow");
    }
}
