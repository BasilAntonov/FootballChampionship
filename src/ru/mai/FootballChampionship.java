package ru.mai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FootballChampionship {

    private static final Logger logger = Logger.getLogger(FootballChampionship.class.getName());

    private final static String FILE_READ = "input.txt";
    private final static String FILE_WRITE = "output.txt";
    private final static String FILE_ENCODING = "cp1251";

    public static void main(String[] args) {

        try {
            FileHandler fh1 = new FileHandler("FootballChampionshipTXT.log");
            FileHandler fh = new FileHandler("FootballChampionshipXML.log");
            fh.setFormatter(new SimpleFormatter());

            logger.addHandler(fh1);
            logger.addHandler(fh);
        } catch (SecurityException e) {
            logger.log(Level.SEVERE,
                    "Не удалось создать файл лога из-за политики безопасности.",
                    e);
        } catch (IOException e) {
            logger.log(Level.SEVERE,
                    "Не удалось создать файл лога из-за ошибки ввода-вывода.",
                    e);
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

            logger.log(Level.INFO, "Корректно завершилась работа");
        } catch (Exception e) {
            FileFun.writeFile(FILE_WRITE, e);
            logger.log(Level.SEVERE,
                    "Рухнул main",
                    e);
        }
    }
}