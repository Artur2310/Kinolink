package ru.kinolink.service.util;

public abstract class NumberParserUtil {

    public static int parseWithDefault(String s, int defaultVal) {
        return s != null && s.matches("-?\\d+") ? Integer.parseInt(s) : defaultVal;
    }
}
