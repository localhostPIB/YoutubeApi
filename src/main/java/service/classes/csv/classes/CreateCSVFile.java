package service.classes.csv.classes;

import dao.classes.*;
import service.classes.csv.interfaces.ICreateCSVFile;
import util.*;
import java.io.*;
import org.apache.commons.csv.*;

public class CreateCSVFile implements ICreateCSVFile {

    private static final String FILE_HEADER_COMMENTARY[]  = {"Likes:","Publish at:","Comment:","Username:"};
    private static final String FILE_HEADER_USER[]        = {"Channel-Id:","Username:","ChannelUrl:","ImageUrl:"};
    private static final String FILE_HEADER_VIDEOINFO[]   = {"Video-Id:","Time:","Channel-Name:","Description:","Favorite:", "Count:"};
    private static final String NEW_LINE_SEPARATOR        = "\n";
    private static File dir;

    static {
       dir = FileUtils.createDirectory("CSVFiles");
    }

    private String cutAllInvalidSymbols(String fileName){
        return StringUtils.deleteInvalidSymbols(fileName);
    }

    public void createCSVVideoInfos(final File file) throws Exception {
        FileWriter fileWriter = new FileWriter(file);

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try (CSVPrinter printer = new CSVPrinter(fileWriter, csvFileFormat)) {

            printer.printRecord(FILE_HEADER_VIDEOINFO);
            VideoInfoDaoHibernateImp videoDaoHibernateImp = new VideoInfoDaoHibernateImp();
            videoDaoHibernateImp.findAllVideoInfos().forEach((iVideoInfo) -> {
                try {
                    printer.printRecord(iVideoInfo.toString());

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        }catch (IOException ioException){
            ioException.printStackTrace();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void createCSVCommentaryFile(final String fileName, final String videoId) throws Exception {
        String fileNameWithoutInvalidSymbols = cutAllInvalidSymbols(fileName);

        FileWriter fileWriter = new FileWriter(new File(dir,fileNameWithoutInvalidSymbols+"_Commentaries"+".csv"));

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
        }catch (IOException ioException){
            ioException.printStackTrace();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void createCSVYTUserFile(final String fileName,final String videoId) throws Exception {
        String fileNameWithoutInvalidSymbols = cutAllInvalidSymbols(fileName);

        FileWriter fileWriter = new FileWriter(new File(dir,fileNameWithoutInvalidSymbols+"_User"+".csv"));
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
