import model.interfaces.IYoutubeUser;
import service.api.GetYTCommentaries;
import service.api.GetYTVideoInformations;
import dao.*;
import service.csv.CreateCSVFile;
import util.validator.StringValidator;

import java.util.List;

public class Starter {

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 1 && StringValidator.validateArgument(args[0])) {
            GetYTVideoInformations getYTVideoInformations = new GetYTVideoInformations(new VideoDaoHibernateImp());
            getYTVideoInformations.getYTVideoStatistics(args[0]);

            GetYTCommentaries getYTCommentaries = new GetYTCommentaries(new YTUserDaoHibernateImp(),
             new CommentaryDaoHibernateImp(), new ReplyDaoHibernateImp(), getYTVideoInformations.getIVideoInfo());
            getYTCommentaries.getAllMessages(args[0]);

            CreateCSVFile createCSVFile = new CreateCSVFile();
            createCSVFile.createCSVYTUserFile(getYTVideoInformations.getIVideoInfo().getTitle(),getYTVideoInformations.getIVideoInfo().getVideoId() );
            createCSVFile.createCSVCommentaryFile(getYTVideoInformations.getIVideoInfo().getTitle(),getYTVideoInformations.getIVideoInfo().getVideoId());

        } else {
            System.err.println("Please specify videoId e.g. FVFGFY5YmBI !");
        }
    }
}
