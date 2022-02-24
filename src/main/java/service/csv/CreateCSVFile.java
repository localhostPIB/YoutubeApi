package service.csv;

import dao.CommentaryDaoHibernateImp;
import dao.YTUserDaoHibernateImp;
import org.apache.commons.csv.*;

import java.io.*;

public class CreateCSVFile {

    private static final Object [] FILE_HEADER = {"Id","likes","publish At","comment","Video-Info","User"};

    private static final String NEW_LINE_SEPARATOR = "\n";

    public void createCSVYTUserFile(String fileName) throws IOException {
        FileWriter out = new FileWriter(fileName+".csv");

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try (CSVPrinter printer = new CSVPrinter(out, csvFileFormat)) {

            printer.printRecord(FILE_HEADER);
            CommentaryDaoHibernateImp commentaryDaoHibernateImp = new CommentaryDaoHibernateImp();
            commentaryDaoHibernateImp.findAllYTCommentaries().forEach((iCommentary) -> {
                try {
                    printer.printRecord(iCommentary.toString()+"Commentaries");

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        }
    }

    public void createCSVYTCommentary(String fileName) throws IOException {
        FileWriter out = new FileWriter(fileName+".csv");
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try (CSVPrinter printer = new CSVPrinter(out, csvFileFormat)) {

            printer.printRecord(FILE_HEADER);
            YTUserDaoHibernateImp ytUserDaoHibernateImp = new YTUserDaoHibernateImp();
            ytUserDaoHibernateImp.findAllYTUsers().forEach((iYoutubeUser) -> {
                try {
                    printer.printRecord(iYoutubeUser.toString()+"Commentaries");

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        }
    }
}
