package com.epam.mjc;

import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public static List<String> splitByDelimiters(String source, Collection<String> delimiters) {

        StringJoiner sj = new StringJoiner("", "[", "]+");
        for(String s : delimiters){
            sj.add(s);
        }
        return Arrays.stream(source.split(sj.toString()))
                .filter( s -> s.length() > 0)
                .collect(Collectors.toList());
    }
}
