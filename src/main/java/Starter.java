import api.GetYTCommentaries;
import api.GetYTVideoInformations;
import dao.*;
import util.validator.StringValidator;

public class Starter {

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        if (StringValidator.validateArgument(args[0])) {
            GetYTVideoInformations getYTVideoInformations = new GetYTVideoInformations(new VideoDaoHibernateImp());
            getYTVideoInformations.getYTVideoStatistics(args[0]);

            GetYTCommentaries getYTCommentaries = new GetYTCommentaries(new YTUserDaoHibernateImp(),
             new CommentaryDaoHibernateImp(), new ReplyDaoHibernateImp());
            getYTCommentaries.getAllMessages(args[0]);
        } else {
            System.err.println("Please specify videoId e.g. FVFGFY5YmBI !");
        }
    }
}
