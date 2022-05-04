package it.sosinski.chat.utils;

public class ServerPrinter {

    private static final String RESET = "\033[0m";
    private static final String ANSI_RED = "\u001B[31m";

    public static void print(String message) {
        System.out.println(ANSI_RED + message + RESET);
    }
}
