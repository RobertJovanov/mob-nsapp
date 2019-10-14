package com.mobiquity.mobtravelapp.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TravelValidation {


    public static String checkInputStations(String station){
        Boolean flag;
        Pattern pattern=Pattern.compile("^(([A-Z])([a-z])+\\s*)+$");
        Matcher matcher=pattern.matcher(station);
        if(matcher.matches()==false){
            String[] words = station.split("\\s");
            List<String> place = new ArrayList<>();
            if (words.length == 1) {
                String word = words[0];
                station = Character.toUpperCase(word.charAt(0)) + word.substring(1);

            } else {
                for (String word : words) {
                    word = Character.toUpperCase(word.charAt(0))+ word.substring(1);
                    place.add(word);
                }
                station = (place.get(0) + " " + place.get(1));
                System.out.println(station);
            }
        }
        return station;
    }


    public static boolean checkInputTime(String dateTime) {
        DateFormat sdf = new SimpleDateFormat("yyyy-mm-dd'T'HH:MM:ss'Z'");
        //sdf.setLenient();
        try{
            sdf.parse(dateTime);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }


}

