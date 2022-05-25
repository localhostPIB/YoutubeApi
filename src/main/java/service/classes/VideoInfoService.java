package service.classes;

import dao.classes.VideoInfoDaoHibernateImp;
import dao.interfaces.IVideoInfoDaoHibernate;
import model.interfaces.IVideoInfo;
import service.classes.api.videoInformations.classes.GetYTVideoInformations;
import service.classes.csv.classes.CreateCSVFile;
import service.classes.csv.interfaces.ICreateCSVFile;
import service.classes.html.classes.CreateHTMLFile;
import service.classes.html.interfaces.ICreateHTMLFile;
import service.classes.pdf.classes.CreatePDFFile;
import service.classes.pdf.interfaces.ICreatePDFFile;
import service.interfaces.IVideoInfoService;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.List;

public class VideoInfoService implements IVideoInfoService {

    private final GetYTVideoInformations getYTVideoInformations;

    private final IVideoInfoDaoHibernate iVideoInfoDaoHibernate;

    public VideoInfoService() {
        this.iVideoInfoDaoHibernate = new VideoInfoDaoHibernateImp();
        this.getYTVideoInformations = new GetYTVideoInformations(this.iVideoInfoDaoHibernate);
    }

    @Override
    public void getVideoInformations(final IVideoInfo iVideoInfo) throws Exception {
        this.getYTVideoInformations.getYTVideoStatistics(iVideoInfo);
    }

    @Override
    public void createVideoInfosAsCSV(final File file) throws Exception {
        ICreateCSVFile iCreateCSVFile = new CreateCSVFile();
        try {
            iCreateCSVFile.createCSVVideoInfos(file);
        } catch (IOException ioException) {
            throw new IOException(ioException);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void createVideoInfosAsHTML(final File file, String docName) throws IOException {
        ICreateHTMLFile iCreateHTMLFile = new CreateHTMLFile();
        try {
            iCreateHTMLFile.writeHTMLFile(file, docName);
        } catch (IOException ioException) {
            throw new IOException(ioException);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createVideoInfosAsPDF(final File file, String docName) throws IOException {
        ICreatePDFFile iCreatePDFFile = new CreatePDFFile();
        try {
            iCreatePDFFile.writePDFFile(file);
        } catch (IOException ioException) {
            throw new IOException(ioException);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IVideoInfo callVideoInformations(final String videoId) throws GeneralSecurityException, IOException {
        try {
            return this.getYTVideoInformations.callYTVideoStatistics(videoId);
        } catch (GeneralSecurityException generalSecurityException) {
            throw new GeneralSecurityException(generalSecurityException);
        } catch (IOException ioException) {
            throw new IOException(ioException);
        }
    }

    @Override
    public void deleteVideoInfoById(final int id) throws Exception {
        this.iVideoInfoDaoHibernate.deleteVideoInfoById(id);
    }

    @Override
    public List<IVideoInfo> getAllVideoInfos() throws Exception {
        return this.iVideoInfoDaoHibernate.findAllVideoInfos();
    }

    @Override
    public void initClientId() throws FileNotFoundException {
        try {
            this.getYTVideoInformations.initClientSecret();
        } catch (FileNotFoundException fileNotFoundException) {
            throw new FileNotFoundException(fileNotFoundException.getMessage());
        }
    }

    @Override
    public String getClientId() {
        return this.getYTVideoInformations.getClientSecret();
    }
}
