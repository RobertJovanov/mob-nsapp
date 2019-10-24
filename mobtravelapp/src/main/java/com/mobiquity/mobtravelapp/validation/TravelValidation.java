package com.mobiquity.mobtravelapp.validation;

import org.springframework.validation.annotation.Validated;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TravelValidation {
    private static Pattern pattern = Pattern.compile("^(([A-Z])([a-z])+\\s*)+$");
    /**
     * Reformat the values of station name to adhere to our format standard.
     * Standard: Stations should start with capital letter.
     * If a station name contains multiple words, each word should start with capital letter.
     *
     * @param station name String
     * @return reformatted name String
     */
    public static String reformatStationName(String station) {

        Matcher matcher = pattern.matcher(station);
        if (matcher.matches() == false) {
            String[] words = station.split("\\s");
            List<String> place = new ArrayList<>();
            if (words.length == 1) {
                String word = words[0];
                station = Character.toUpperCase(word.charAt(0)) + word.substring(1);
            } else {
                for (String word : words) {
                    word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
                    place.add(word);
                }
                station = (place.get(0) + " " + place.get(1));
            }
        }
        return station;
    }

    /**
     * Checks if dateTime parameter format is correct.
     *
     * @param dateTime String
     * @return boolean for correct format
     */
    public static boolean checkInputTime(String dateTime) {
        DateFormat sdf = new SimpleDateFormat("yyyy-mm-dd'T'HH:MM:ss");
        try {
            sdf.parse(dateTime);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}

