package ru.mai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The program of the football championship.
 * The input to the program is a file with the
 * results of the football championship.
 * The program determines the winner or leader
 * based on the data received.
 *
 * @author BasilAn
 * @version 1.2
 */
public class FootballChampionship {

    private static final Logger logger = Logger.getLogger(FootballChampionship.class.getName());

    /**
     * File name to read
     */
    private final static String FILE_READ = "input.txt";

    /**
     * File name to write
     */
    private final static String FILE_WRITE = "output.txt";

    /**
     * File encoding for reading
     */
    private final static String FILE_ENCODING = "cp1251";

    public static void main(String[] args) {

        try {
            FileHandler fh = new FileHandler("FootballChampionship.log");
            logger.addHandler(fh);
        } catch (SecurityException e) {
            logger.log(Level.SEVERE, "Не удалось создать файл лога из-за политики безопасности.", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Не удалось создать файл лога из-за ошибки ввода-вывода.", e);
        }

        try {
            logger.log(Level.INFO, "Начала работы программы");

            ArrayList<FootballTeam> data = FileFun.readFile(FILE_READ, FILE_ENCODING);

            FileFun.writeFile(FILE_WRITE, data);

            logger.log(Level.INFO, "Корректно завершена работа программы");

        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            logger.log(Level.INFO, "Некорректно завершена работа программы");
        }
    }
}