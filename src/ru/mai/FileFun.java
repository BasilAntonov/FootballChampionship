package ru.mai;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class implements the input / output functions for the file
 *
 * @author BasilAn
 * @version 1.2
 */
public class FileFun {

    private static final Logger logger = Logger.getLogger(FileFun.class.getName());

    /**
     * The function of reading a file with the
     * conversion of the received data into an ArrayList
     *
     * @param nameFile - file name to read
     * @param encoding - file encoding for reading
     * @return - Parsed List
     * @throws FileNotFoundException        - the file with the
     *                                      specified path does not exist
     * @throws UnsupportedEncodingException - the character
     *                                      encoding is not supported.
     */
    public static ArrayList<FootballTeam> readFile(final String nameFile, final String encoding)
            throws FileNotFoundException, UnsupportedEncodingException {

        try {
            FileHandler fh = new FileHandler("FileRead.log");
            logger.addHandler(fh);
        } catch (SecurityException e) {
            logger.log(Level.SEVERE, "Не удалось создать файл лога из-за политики безопасности.", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Не удалось создать файл лога из-за ошибки ввода-вывода.", e);
        }

        ArrayList<FootballTeam> answer = new ArrayList<>();

        boolean flag = false;

        try (Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(nameFile), encoding))) {

            logger.log(Level.INFO, "Началось чтение файла:");

            while (scanner.hasNext()) {
                try {
                    answer.add(StringToFootballTeamConverter.stringToFootballTeam(scanner.nextLine()));
                } catch (StringToFootballTeamException ex) {
                    flag = true;
                    logger.log(Level.SEVERE, ex.getMessage(), ex);
                    logger.log(Level.SEVERE, "Строка породившая ошибку выше: " + ex.getErrorCreator());
                }
            }
        }

        if (flag) {
            logger.log(Level.INFO, "При чтение файла были найденны некорректные данные");
        } else {
            logger.log(Level.INFO, "Чтение файла прошло успешно");
        }

        return answer;
    }

    /**
     * The function of writing to the program
     * results file
     *
     * @param nameFile - the name of the file to write
     * @param data     - data to write to file
     * @throws IOException - exceptions arising from the function
     */
    public static void writeFile(final String nameFile, final ArrayList<FootballTeam> data)
            throws IOException {

        try (Writer write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nameFile),
                StandardCharsets.UTF_8))) {

            boolean finalResult = true;

            for (int i = 1; i < data.size(); i++) {
                if (data.get(0).getNumGame() != data.get(i).getNumGame()) {
                    finalResult = false;
                    break;
                }
            }

            Collections.sort(data);

            int count = 1;

            for (int i = 1; i < data.size(); i++) {
                if (data.get(0).getPoints() != data.get(i).getPoints()) {
                    break;
                } else {
                    count++;
                }
            }

            if (finalResult) {
                if (count == 1) {
                    final String victor = "Победитель турнира - ";

                    write.write(victor);
                    write.write(data.get(0).getName());
                } else {
                    final String victor = "Победители турнира: ";
                    final String space = "\n\t";

                    write.write(victor);

                    for (int i = 0; i < count; i++) {
                        write.write(space);
                        write.write(data.get(i).getName());
                    }
                }
            } else {
                if (count == 1) {
                    final String victor = "Текущий лидер - ";

                    write.write(victor);
                    write.write(data.get(0).getName());
                } else {
                    final String victor = "Текущие лидеры: ";
                    final String space = "\n\t";

                    write.write(victor);

                    for (int i = 0; i < count; i++) {
                        write.write(space);
                        write.write(data.get(i).getName());
                    }
                }
            }
        }
    }
}