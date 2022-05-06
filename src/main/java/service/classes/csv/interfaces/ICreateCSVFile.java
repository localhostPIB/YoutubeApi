package service.classes.csv.interfaces;

import java.io.*;

public interface ICreateCSVFile {

    void createCSVVideoInfos(final File file) throws Exception;

    void createCSVCommentaryFile(final String fileName, final String videoId) throws Exception;
}
