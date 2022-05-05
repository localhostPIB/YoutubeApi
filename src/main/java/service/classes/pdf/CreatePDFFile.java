package service.classes.pdf;

import dao.classes.VideoInfoDaoHibernateImp;
import model.interfaces.IVideoInfo;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionURI;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;

import java.io.*;
import java.util.*;


public class CreatePDFFile {
    private final VideoInfoDaoHibernateImp videoDaoHibernateImp;

    public CreatePDFFile() {
        this.videoDaoHibernateImp = new VideoInfoDaoHibernateImp();
    }

    private void arrayListToPDFFile(final List<IVideoInfo> iVideoInfoList, final String docName) throws IOException {
        try (PDDocument doc = new PDDocument()) {
            PDPage pdPage = new PDPage();
            doc.addPage(pdPage);

            try (PDPageContentStream pdPageContentStream = new PDPageContentStream(doc, pdPage)) {
                pdPageContentStream.beginText();
                pdPageContentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                pdPageContentStream.setLeading(14.5f);
                pdPageContentStream.newLineAtOffset(25, 700);

                Date date = new Date();
                pdPageContentStream.showText(date.toString());
                pdPageContentStream.newLine();
                pdPageContentStream.newLine();

                for (IVideoInfo iVideoInfo : iVideoInfoList) {
                    pdPageContentStream.showText("Video-Id: " +iVideoInfo.getVideoId());
                    pdPageContentStream.newLine();
                    pdPageContentStream.showText("Title: " +iVideoInfo.getTitle());
                    pdPageContentStream.newLine();
                    pdPageContentStream.showText("Uploader: " +iVideoInfo.getChannelTitle());
                    pdPageContentStream.newLine();
                    pdPageContentStream.showText("Likes: " +iVideoInfo.getLikes());
                    pdPageContentStream.newLine();
                    pdPageContentStream.showText("Comments: " +iVideoInfo.getCommentCount());
                    pdPageContentStream.newLine();
                    pdPageContentStream.showText("Views: " +iVideoInfo.getViewCount());
                    pdPageContentStream.newLine();
                    pdPageContentStream.newLine();
                }

                pdPageContentStream.endText();
            }
            doc.save(docName);
        }
    }

    public void writePDFFile(final File file) throws IOException {
        List<IVideoInfo> iVideoInfoList = this.videoDaoHibernateImp.findAllVideoInfos();
        arrayListToPDFFile(iVideoInfoList, file.getPath());
    }
}
