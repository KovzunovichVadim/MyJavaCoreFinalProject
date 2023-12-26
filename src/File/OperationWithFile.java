package File;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OperationWithFile {
    private static final String INPUT_PATH = "src\\Input\\";
    private static final String ARCHIVE_PATH = "src\\Archive\\";
    private static final String FILE_ACCOUNT_NUMBER_PATH = "src\\AccountNumber\\Account.txt";
    private static final String FILE_REPORT_PATH = "src\\Report\\FileReport.txt";
    private static File file;

    public static String[] getFileInputNames() {
        file = new File(INPUT_PATH);
        return file.list();
    }

    public static boolean deleteFileInput(String fileName) {
        return new File(INPUT_PATH + fileName).delete();
    }

    public static String fileInputReading(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        try (FileReader fileReader = new FileReader(INPUT_PATH + fileName)) {
            while ((i = fileReader.read()) != -1) {
                stringBuilder.append((char) i);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return stringBuilder.toString();
    }

    public static String fileAccountNumberReading() {
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        try (FileReader fileReader = new FileReader(FILE_ACCOUNT_NUMBER_PATH)) {
            while ((i = fileReader.read()) != -1) {
                stringBuilder.append((char) i);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return stringBuilder.toString();
    }

    public static String fileReportReading() {
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        try (FileReader fileReader = new FileReader(FILE_REPORT_PATH)) {
            while ((i = fileReader.read()) != -1) {
                stringBuilder.append((char) i);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return stringBuilder.toString();
    }

    public static boolean fileArchiveWriting(String fileName, String writeInFile) {
        try (FileWriter fileWriter = new FileWriter(ARCHIVE_PATH + fileName)) {
            fileWriter.write(writeInFile);
            fileWriter.flush();
            file = new File(ARCHIVE_PATH + fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return file.exists();
    }

    public static boolean fileAccountNumberWriting(String writeInFile) {
        try (FileWriter fileWriter = new FileWriter(FILE_ACCOUNT_NUMBER_PATH)) {
            fileWriter.write(writeInFile);
            fileWriter.flush();
            file = new File(FILE_ACCOUNT_NUMBER_PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return file.exists();
    }

    public static boolean fileReportWriting(String writeInFile) {
        try (FileWriter fileWriter = new FileWriter(FILE_REPORT_PATH, true)) {
            fileWriter.write(writeInFile + "\n");
            fileWriter.flush();
            file = new File(FILE_REPORT_PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return file.exists();
    }
}
