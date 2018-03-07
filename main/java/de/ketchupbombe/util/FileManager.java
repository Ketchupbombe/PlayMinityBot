package de.ketchupbombe.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class FileManager {

    private File file;

    public FileManager(String path) {
        this.file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public FileManager(File file) {
        this.file = file;
    }

    public void writeLineWithDate(String arg0) {
        try {
            FileWriter fileWriter = new FileWriter(this.file, true);

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat();
            String format = "<dd.MM.yy-HH:mm:ss> ";
            formatter.applyPattern(format);

            fileWriter.write(formatter.format(date) + arg0 + System.getProperty("line.separator"));
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLine(String arg0) {
        try {
            FileWriter fileWriter = new FileWriter(this.file, true);
            fileWriter.write(arg0 + System.getProperty("line.separator"));
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public int getLines() {
        int i = 0;

        try {
            FileReader fileReader = new FileReader(this.file);
            LineNumberReader numberReader = new LineNumberReader(fileReader);
            while (numberReader.readLine() != null) {
            }

            i = numberReader.getLineNumber();
            numberReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return i;
    }

    public String getRandomLine() throws IOException {
        Random random = new Random();
        int line = random.nextInt(getLines() + 1);

        return this.readLine(line);

    }

    public String readLine(int line) throws IOException {
        FileReader tempFileReader;
        BufferedReader tempBufferedReader;
        tempFileReader = new FileReader(this.file);
        tempBufferedReader = new BufferedReader(tempFileReader);

        String returnStr;
        for (int i = 0; i < line - 1; i++) {
            tempBufferedReader.readLine();

        }
        returnStr = tempBufferedReader.readLine();


        return returnStr;
    }

    public ArrayList<String> readLines() throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 1; i < this.getLines() + 1; i++) {
            lines.add(readLine(i));
        }
        return lines;
    }

}
