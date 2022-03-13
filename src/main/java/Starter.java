import service.api.videoInformations.GetYTCommentaries;
import service.api.videoInformations.GetYTVideoInformations;
import dao.*;
import service.csv.CreateCSVFile;
import util.FileUtils;
import util.PropertyUtils;
import util.validator.StringValidator;

public class Starter {

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

            GetYTVideoInformations getYTVideoInformations = new GetYTVideoInformations(new VideoDaoHibernateImp());
            getYTVideoInformations.getYTVideoStatistics(args[0]);

            GetYTCommentaries getYTCommentaries = new GetYTCommentaries(new YTUserDaoHibernateImp(),
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
