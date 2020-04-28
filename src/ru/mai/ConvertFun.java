package ru.mai;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author BasilAn
 * @version 1.0
 */
public class ConvertFun {

    /**
     * Preparatory string analysis
     *
     * @param str - parsing string
     * @return word list
     */
    public static ArrayList<String> strInList(final String str) {

        ArrayList<String> answer = new ArrayList<>();

        Pattern pattern = Pattern.compile("[\"]([^\"]*)[\"]");
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            answer.add(matcher.group(1));
        }

        return answer;
    }

    /**
     * Final data analysis
     *
     * @param arrStr - list of pre-analyzed words
     * @return FootballTeam type object
     * @throws Exception - exception arising from incorrect data
     */
    public static FootballTeam listInFootballTeam(final ArrayList<String> arrStr) throws Exception {

        final byte indexNumGame = 1;
        final byte indexPoints = 2;
        final byte sizeList = 3;

        int numGame;
        int points;

        if (arrStr.size() != sizeList) {
            throw new Exception("Conversion incorrect data!");
        } else {
            Pattern pattern = Pattern.compile("\\d+");

            int count = 0;

            Matcher matcher = pattern.matcher(arrStr.get(indexNumGame));

            while (matcher.find()){
                count++;
            }

            if (count == 1) {
                numGame = Integer.parseInt(arrStr.get(indexNumGame).replaceAll("[^0-9]", ""));
            } else {
                throw new Exception("Conversion incorrect data!");
            }

            count = 0;

            matcher = pattern.matcher(arrStr.get(indexPoints));

            while (matcher.find()){
                count++;
            }

            if (count == 1){
                points = Integer.parseInt(arrStr.get(indexPoints).replaceAll("[^0-9]", ""));
            } else {
                throw new Exception("Conversion incorrect data!");
            }
        }

        return new FootballTeam(arrStr.get(0), numGame, points);
    }
}