package service.csv;

import dao.*;
import org.apache.commons.csv.*;

import java.io.*;

public class CreateCSVFile {

    private static final Object [] FILE_HEADER_COMMENTARY = {"Likes","Publish at","Comment","Username"};
    private static final String NEW_LINE_SEPARATOR        = "\n";

    public void createCSVCommentaryFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName+"_Commentaries"+".csv");

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try (CSVPrinter printer = new CSVPrinter(fileWriter, csvFileFormat)) {

            printer.printRecord(FILE_HEADER_COMMENTARY);
            CommentaryDaoHibernateImp commentaryDaoHibernateImp = new CommentaryDaoHibernateImp();
            commentaryDaoHibernateImp.findAllYTCommentaries().forEach((iCommentary) -> {
                try {
                    printer.printRecord(iCommentary.toString());

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        }
    }

    public void createCSVYTUserFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName+"_User"+".csv");
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try (CSVPrinter printer = new CSVPrinter(fileWriter, csvFileFormat)) {

            YTUserDaoHibernateImp ytUserDaoHibernateImp = new YTUserDaoHibernateImp();
            ytUserDaoHibernateImp.findAllYTUsers().forEach((iYoutubeUser) -> {
                try {
                    printer.printRecord(iYoutubeUser.toString());

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        }
    }
}
