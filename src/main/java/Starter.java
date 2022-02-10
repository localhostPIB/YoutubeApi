import api.PrintYTCommentaries;
import dao.CommentaryDaoHibernateImp;
import dao.VideoDaoHibernateImp;
import dao.YTUserDaoHibernateImp;
import util.PropertiesUtils;
import util.validator.StringValidator;

public class Starter {

    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        PropertiesUtils.readPropertyFile();
        PrintYTCommentaries printYTCommentaries = new PrintYTCommentaries(new VideoDaoHibernateImp(),
                new YTUserDaoHibernateImp(), new CommentaryDaoHibernateImp());
        if(StringValidator.validateVideoId(args[0])) {
            printYTCommentaries.getAllMessages(args[0]);
        }
    }
}
