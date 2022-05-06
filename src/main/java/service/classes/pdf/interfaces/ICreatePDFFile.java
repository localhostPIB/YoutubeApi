package service.classes.pdf.interfaces;

import java.io.*;

public interface ICreatePDFFile {
    void writePDFFile(final File file) throws Exception;
}
