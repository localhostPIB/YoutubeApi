package service.classes.html.interfaces;

import java.io.*;

public interface ICreateHTMLFile {
    void writeHTMLFile(final File file, final String docName) throws Exception;
}
