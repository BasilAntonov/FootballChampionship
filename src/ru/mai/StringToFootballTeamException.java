package ru.mai;

/**
 * The error class that occurs when converting an invalid
 * string to an object of class <code>FootballTeam</code>
 *
 * @author BasilAn
 * @version 1.0
 */
public class StringToFootballTeamException extends ClassCastException {

    /**
     * The string that caused the error
     */
    private String errorCreator;

    public String getErrorCreator() {
        return errorCreator;
    }

    public StringToFootballTeamException (String message, String errorCreator){
        super(message);
        this.errorCreator = errorCreator;
    }
}