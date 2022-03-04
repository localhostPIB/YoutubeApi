package service.csv;

import dao.*;
import java.io.*;
import org.apache.commons.csv.*;

public class CreateCSVFile {

    private static final String FILE_HEADER_COMMENTARY[]  = {"Likes:","Publish at:","Comment:","Username:"};
    private static final String FILE_HEADER_USER[]        = {"Channel-Id:","Username:","ChannelUrl:","ImageUrl:"};
    private static final String NEW_LINE_SEPARATOR        = "\n";

    public void createCSVCommentaryFile(String fileName, String videoId) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName+"_Commentaries"+".csv");

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try (CSVPrinter printer = new CSVPrinter(fileWriter, csvFileFormat)) {

            printer.printRecord(FILE_HEADER_COMMENTARY);
            CommentaryDaoHibernateImp commentaryDaoHibernateImp = new CommentaryDaoHibernateImp();
            commentaryDaoHibernateImp.findAllYTCommentariesByVideoId(videoId).forEach((iCommentary) -> {
                try {
                    printer.printRecord(iCommentary.toString());

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        }
    }

    public void createCSVYTUserFile(String fileName, String videoId) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName+"_User"+".csv");
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try (CSVPrinter printer = new CSVPrinter(fileWriter, csvFileFormat)) {
            printer.printRecord(FILE_HEADER_USER);
            YTUserDaoHibernateImp ytUserDaoHibernateImp = new YTUserDaoHibernateImp();
            ytUserDaoHibernateImp.findAllYTUsersByVideoId(videoId).forEach((iYoutubeUser) -> {
                try {
                    printer.printRecord(iYoutubeUser.toString());

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        }
    }
}
