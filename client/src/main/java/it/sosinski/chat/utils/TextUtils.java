package it.sosinski.chat.utils;

public class TextUtils {

    public static String getTextFromParentheses(String text) {
        return text.substring(text.indexOf("\"") + 1, text.lastIndexOf("\""));
    }
}
