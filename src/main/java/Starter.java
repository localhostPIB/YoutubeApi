import api.PrintYTCommentaries;
import dao.*;
import util.validator.StringValidator;

public class Starter {

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        if (args.length >= 1 && StringValidator.validateVideoId(args[0])) {
            PrintYTCommentaries printYTCommentaries = new PrintYTCommentaries(new VideoDaoHibernateImp(),
                    new YTUserDaoHibernateImp(), new CommentaryDaoHibernateImp(),
                    new ReplyDaoHibernateImp());
            printYTCommentaries.getAllMessages(args[0]);
        } else {
            System.err.println("Please specify videoId e.g. FVFGFY5YmBI !");
        }
    }
}
