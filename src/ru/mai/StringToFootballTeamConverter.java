package ru.mai;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that implements data conversion functions
 *
 * @author BasilAn
 * @version 1.2
 */
public class StringToFootballTeamConverter {

    /**
     * Preparatory string analysis
     *
     * @param str - parsing string
     * @return word list
     * @throws StringToFootballTeamException - exception arising from incorrect data
     */
    public static ArrayList<String> strInList(final String str)
            throws StringToFootballTeamException {

        final byte sizeList = 3;

        ArrayList<String> answer = new ArrayList<>();

        Pattern pattern = Pattern.compile("[\"]([^\"]*)[\"]");
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            answer.add(matcher.group(1));
        }

        if (answer.size() != sizeList) {
            throw new StringToFootballTeamException("Conversion incorrect data!", str);
        }

        return answer;
    }

    /**
     * Final data analysis
     *
     * @param arrStr - list of pre-analyzed words
     * @return FootballTeam type object
     * @throws StringToFootballTeamException - exception arising from incorrect data
     */
    public static FootballTeam listInFootballTeam(final ArrayList<String> arrStr)
            throws StringToFootballTeamException {

        final byte indexNumGame = 1;
        final byte indexPoints = 2;

        int numGame;
        int points;

        if (arrStr.get(indexNumGame).matches("Игр-\\d+")) {
            numGame = Integer.parseInt(arrStr.get(indexNumGame).replaceAll("[^0-9]", ""));
        } else {
            throw new StringToFootballTeamException("Conversion incorrect data!", arrStr.get(indexNumGame));
        }

        if (arrStr.get(indexPoints).matches("Очков \\d+")) {
            points = Integer.parseInt(arrStr.get(indexPoints).replaceAll("[^0-9]", ""));
        } else {
            throw new StringToFootballTeamException("Conversion incorrect data!", arrStr.get(indexNumGame));
        }

        return new FootballTeam(arrStr.get(0), numGame, points);
    }
}