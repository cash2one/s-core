package com.seo.text.words;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWordProvider {
    private final static Map<String, String> CHARACTER_MAP = new HashMap<String, String>();
    private final static Map<String, String> REPLACE_MAP = new HashMap<String, String>();

    static {
        CHARACTER_MAP.put("А", "A");
        CHARACTER_MAP.put("Б", "B");
        CHARACTER_MAP.put("В", "V");
        CHARACTER_MAP.put("Г", "G");
        CHARACTER_MAP.put("Д", "D");
        CHARACTER_MAP.put("Е", "E");
        CHARACTER_MAP.put("Ё", "E");
        CHARACTER_MAP.put("Ж", "Zh");
        CHARACTER_MAP.put("З", "Z");
        CHARACTER_MAP.put("И", "I");
        CHARACTER_MAP.put("Й", "I");
        CHARACTER_MAP.put("К", "K");
        CHARACTER_MAP.put("Л", "L");
        CHARACTER_MAP.put("М", "M");
        CHARACTER_MAP.put("Н", "N");
        CHARACTER_MAP.put("О", "O");
        CHARACTER_MAP.put("П", "P");
        CHARACTER_MAP.put("Р", "R");
        CHARACTER_MAP.put("С", "S");
        CHARACTER_MAP.put("Т", "T");
        CHARACTER_MAP.put("У", "Y");
        CHARACTER_MAP.put("Ф", "F");
        CHARACTER_MAP.put("Х", "H");
        CHARACTER_MAP.put("Ц", "Ts");
        CHARACTER_MAP.put("Ч", "Ch");
        CHARACTER_MAP.put("Ш", "Sh");
        CHARACTER_MAP.put("Щ", "Sch");
        CHARACTER_MAP.put("Ъ", "");
        CHARACTER_MAP.put("Ы", "Y");
        CHARACTER_MAP.put("Ь", "");
        CHARACTER_MAP.put("Э", "E");
        CHARACTER_MAP.put("Ю", "Yu");
        CHARACTER_MAP.put("Я", "Ya");
        CHARACTER_MAP.put(" ", "_");

        for (Map.Entry<String, String> entry : CHARACTER_MAP.entrySet()) {
            REPLACE_MAP.put(entry.getKey(), entry.getValue());
            REPLACE_MAP.put(entry.getKey().toLowerCase(), entry.getValue().toLowerCase());
        }
    }

    protected String toTranslit(String text) {
        char charBuffer[] = text.toCharArray();
        StringBuilder sb = new StringBuilder(text.length());

        for (char symbol : charBuffer) {
            String symbolString = String.valueOf(symbol);

            String replace = REPLACE_MAP.get(symbolString);
            sb.append(replace == null ? symbolString : replace);
        }

        return sb.toString();
    }

}
