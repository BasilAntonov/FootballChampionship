package ru.mai;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class implements the input / output functions for the file
 *
 * @author BasilAn
 * @version 0.2
 */
public class FileFun {

    /**
     * The function of reading a file with the
     * conversion of the received data into an ArrayList
     *
     * @param nameFile - file name to read
     * @param encoding - file encoding for reading
     * @return - Parsed List
     * @throws FileNotFoundException - the file with the
     * specified path does not exist
     * @throws UnsupportedEncodingException - the character
     * encoding is not supported.
     */
    public static ArrayList<FootballTeam> readFile(final String nameFile, final String encoding)
            throws FileNotFoundException, UnsupportedEncodingException {

        ArrayList<FootballTeam> answer = new ArrayList<>();

        try(Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(nameFile), encoding))) {

            while (scanner.hasNext()) {
                answer.add(ConvertFun.listInFootballTeam(ConvertFun.strInList(scanner.nextLine())));
            }
        }

        return answer;
    }

    /**
     * The function of writing to the program
     * results file
     *
     * @param nameFile - the name of the file to write
     * @param flag - flag for file entry type
     * @param data - data to write to file
     * @throws IOException - exceptions arising from the function
     */
    public static void writeFile(final String nameFile, boolean flag, final ArrayList<FootballTeam> data)
            throws IOException {

        try(Writer write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nameFile),
                StandardCharsets.UTF_8))) {

            int count = 1;

            int buff = data.get(0).getPoints();

            for (int i = 1; i < data.size(); i++) {
                if (buff != data.get(i).getPoints()) {
                    break;
                } else {
                    count++;
                }
            }

            if (flag) {
                if (count == 1){
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
                if (count == 1){
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

    /**
     * Function to write a string to a file
     *
     * @param nameFile - the name of the file to write
     * @param massage - string for writing
     * @throws IOException - exceptions arising from the function
     */
    public static void writeFile(final String nameFile, final String massage) throws IOException {

        try(Writer write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nameFile),
                StandardCharsets.UTF_8))) {

            write.write(massage);
        }
    }
}