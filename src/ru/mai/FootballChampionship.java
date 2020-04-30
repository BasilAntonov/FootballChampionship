package ru.mai;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * The program of the football championship.
 * The input to the program is a file with the
 * results of the football championship.
 * The program determines the winner or leader
 * based on the data received.
 *
 * @author BasilAn
 * @version 0.2
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
            FileHandler fh1 = new FileHandler("FootballChampionshipTXT.log");
            FileHandler fh = new FileHandler("FootballChampionshipXML.log");
            fh.setFormatter(new SimpleFormatter());

            logger.addHandler(fh1);
            logger.addHandler(fh);
        } catch (SecurityException e) {
            logger.log(Level.SEVERE, "Не удалось создать файл лога из-за политики безопасности.", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Не удалось создать файл лога из-за ошибки ввода-вывода.", e);
        }

        try {
            logger.log(Level.INFO, "Начала работы");

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
        } catch (ClassCastException cce) {
            logger.log(Level.SEVERE, cce.getMessage(), cce);
            try {
                FileFun.writeFile(FILE_WRITE, "Некорректные данные в файле");
            } catch (IOException ioe) {
                logger.log(Level.SEVERE, ioe.getMessage(), ioe);
            }
            logger.log(Level.INFO, "Некорректно завершена работа программы");
        } catch (FileNotFoundException ffe) {
            logger.log(Level.SEVERE, ffe.getMessage(), ffe);
            try {
                FileFun.writeFile(FILE_WRITE, "Нет файла с таким именем");
            } catch (IOException ioe) {
                logger.log(Level.SEVERE, ioe.getMessage(), ioe);
            }
            logger.log(Level.INFO, "Некорректно завершена работа программы");
        } catch (UnsupportedEncodingException uee) {
            logger.log(Level.SEVERE, uee.getMessage(), uee);
            try {
                FileFun.writeFile(FILE_WRITE, "Кодировка символов не поддерживается");
            } catch (IOException ioe) {
                logger.log(Level.SEVERE, ioe.getMessage(), ioe);
            }
            logger.log(Level.INFO, "Некорректно завершена работа программы");
        } catch (IOException ioe) {
            logger.log(Level.SEVERE, ioe.getMessage(), ioe);
            logger.log(Level.INFO, "Некорректно завершена работа программы");
        }
    }
}