package ru.mai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
 * @version 1.1
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

            boolean finalResult = true;

            final int buff = data.get(0).getNumGame();

            for (int i = 1; i < data.size(); i++) {
                if (buff != data.get(i).getNumGame()) {
                    finalResult = false;
                    break;
                }
            }

            Collections.sort(data);

            FileFun.writeFile(FILE_WRITE, finalResult, data);

            logger.log(Level.INFO, "Корректно завершена работа программы");

        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            logger.log(Level.INFO, "Некорректно завершена работа программы");
        }
    }
}