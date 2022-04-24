package service.classes.html;

import dao.VideoDaoHibernateImp;
import model.interfaces.IVideoInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class CreateHTMLFile {

    private VideoDaoHibernateImp videoDaoHibernateImp;

    public CreateHTMLFile() {
        this.videoDaoHibernateImp = new VideoDaoHibernateImp();
    }

    private String arrayListToXHTMLFile(final List<IVideoInfo> iVideoInfoList, final String docName) {
        Date date = new Date();
        StringBuilder xhtmlFile = new StringBuilder("<!DOCTYPE html>");
        xhtmlFile.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"de\" xml:lang=\"de\">");

        xhtmlFile.append("<p>");
        xhtmlFile.append(date);
        xhtmlFile.append("</p>");

        xhtmlFile.append("<head>");
        xhtmlFile.append("<meta charset=\"utf-8\" />");
        xhtmlFile.append("<title>");
        xhtmlFile.append("Youtube Comment Picker Alpha");
        xhtmlFile.append("</title>");
        xhtmlFile.append("<h1>");
        xhtmlFile.append(docName);
        xhtmlFile.append("</h1>");
        xhtmlFile.append("</head>");
        xhtmlFile.append("<body>");
        xhtmlFile.append("<table border=\"2\">");
        xhtmlFile.append("<thead>");
        xhtmlFile.append("<tr>");

        xhtmlFile.append("<th>");
        xhtmlFile.append("Video-Id:");
        xhtmlFile.append("</th>");

        xhtmlFile.append("<th>");
        xhtmlFile.append("Time:");
        xhtmlFile.append("</th>");

        xhtmlFile.append("<th>");
        xhtmlFile.append("Channel-Name:");
        xhtmlFile.append("</th>");

        xhtmlFile.append("<th>");
        xhtmlFile.append("Description:");
        xhtmlFile.append("</th>");

        xhtmlFile.append("<th>");
        xhtmlFile.append("Count:");
        xhtmlFile.append("</th>");

        xhtmlFile.append("<th>");
        xhtmlFile.append("Title:");
        xhtmlFile.append("</th>");

        xhtmlFile.append("<th>");
        xhtmlFile.append("Likes:");
        xhtmlFile.append("</th>");

        xhtmlFile.append("<th>");
        xhtmlFile.append("Favorite");
        xhtmlFile.append("</th>");

        xhtmlFile.append("</tr>");
        xhtmlFile.append("</thead>");

        xhtmlFile.append("<tbody>");
        for (IVideoInfo iVideoInfo : iVideoInfoList) {
            xhtmlFile.append("<tr>");
            xhtmlFile.append("<td>");
            xhtmlFile.append(iVideoInfo.getVideoId());
            xhtmlFile.append("</td>");

            xhtmlFile.append("<td>");
            xhtmlFile.append(iVideoInfo.getTimestamp());
            xhtmlFile.append("</td>");

            xhtmlFile.append("<td>");
            xhtmlFile.append(iVideoInfo.getChannelTitle());
            xhtmlFile.append("</td>");

            xhtmlFile.append("<td>");
            xhtmlFile.append(iVideoInfo.getVideoDescription());
            xhtmlFile.append("</td>");

            xhtmlFile.append("<td>");
            xhtmlFile.append(iVideoInfo.getViewCount());
            xhtmlFile.append("</td>");

            xhtmlFile.append("<td>");
            xhtmlFile.append(iVideoInfo.getTitle());
            xhtmlFile.append("</td>");

            xhtmlFile.append("<td>");

            if(iVideoInfo.getLikes() != null) {
                xhtmlFile.append(iVideoInfo.getLikes());
            }else {
                xhtmlFile.append("??");
            }

            xhtmlFile.append("</td>");

            xhtmlFile.append("<td>");
            xhtmlFile.append(iVideoInfo.getFavorite());
            xhtmlFile.append("</td>");

            xhtmlFile.append("</tr>");
        }
        xhtmlFile.append("</tbody>");

        xhtmlFile.append("</table>");
        xhtmlFile.append("</body>");

        xhtmlFile.append("</html>");

        return xhtmlFile.toString();
    }

    public void writeHTMLFile(final File file, final String docName) throws IOException {
        List<IVideoInfo> iVideoInfoList = this.videoDaoHibernateImp.findAllVideoInfos();
        FileWriter fileWriter = new FileWriter(file);
        String html = arrayListToXHTMLFile(iVideoInfoList, docName);

        fileWriter.write(html);

        fileWriter.close();
    }
}
