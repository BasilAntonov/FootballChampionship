package ru.mai;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that implements data conversion functions
 *
 * @author BasilAn
 * @version 2.0
 */
public class StringToFootballTeamConverter {

    /**
     * Parsing strings and transformations to a FootballTeam object
     *
     * @param str - parsing string
     * @return FootballTeam type object
     * @throws StringToFootballTeamException - exception arising from incorrect data
     */
    public static FootballTeam stringToFootballTeam(final String str)
            throws StringToFootballTeamException {

        final String regex = "\\\"(.+)\\\",\\\"Игр-(\\d+)\\\"\\\"Очков\\s(\\d+)\\\"";

        final byte nameGroup = 1;
        final byte numGameGroup = 2;
        final byte pointsGroup = 3;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.matches()) {
            return new FootballTeam(
                    matcher.group(nameGroup),
                    Integer.parseInt(matcher.group(numGameGroup)),
                    Integer.parseInt(matcher.group(pointsGroup))
            );
        } else {
            throw new StringToFootballTeamException("Conversion incorrect data!", str);
        }
    }
}