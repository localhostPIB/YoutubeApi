import dao.classes.*;
import service.classes.api.videoInformations.classes.*;
import service.classes.api.videoInformations.interfaces.IGetYTCommentaries;
import util.validator.StringValidator;
import service.classes.csv.classes.CreateCSVFile;
import util.*;

@Deprecated
public class StarterCLI {

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        if (args.length >= 1 && StringValidator.validateArgument(args[0])) {

            if (args.length == 2 && StringValidator.validateArgument(args[1])
                                 && !FileUtils.isFileExists("res/config.properties")) {
                PropertyUtils.writeInPropertyFile(args[1]);
            }

            GetYTVideoInformations getYTVideoInformations = new GetYTVideoInformations(new VideoInfoDaoHibernateImp());
            getYTVideoInformations.initClientSecret();
            getYTVideoInformations.callYTVideoStatistics(args[0]);

            IGetYTCommentaries getYTCommentaries = new GetYTCommentaries(new YTUserDaoHibernateImp(),
             new CommentaryDaoHibernateImp(), new ReplyDaoHibernateImp(), getYTVideoInformations.getIVideoInfo());
            getYTCommentaries.getAllMessages(args[0]);

            CreateCSVFile createCSVFile = new CreateCSVFile();
            createCSVFile.createCSVYTUserFile(getYTVideoInformations.getIVideoInfo().getTitle(),getYTVideoInformations.getIVideoInfo().getVideoId());
            createCSVFile.createCSVCommentaryFile(getYTVideoInformations.getIVideoInfo().getTitle(),getYTVideoInformations.getIVideoInfo().getVideoId());

        } else {
            System.err.println("Please specify videoId e.g. FVFGFY5YmBI !");
        }
    }
}
