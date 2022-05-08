package service.classes;

import dao.classes.CommentaryDaoHibernateImp;
import dao.interfaces.ICommentaryDaoHibernate;
import model.interfaces.ICommentary;
import service.classes.api.videoInformations.interfaces.IGetYTCommentaries;
import service.inferfaces.ICommentService;

import java.util.List;

public class CommentService implements ICommentService {

    private final ICommentaryDaoHibernate iCommentaryDaoHibernate;


    public CommentService() {
        this.iCommentaryDaoHibernate = new CommentaryDaoHibernateImp();
    }

    public void saveAllYTVideoMessagesByVideoId(final String videoId, IGetYTCommentaries iGetYTCommentaries) throws Exception {
        try {
            iGetYTCommentaries.getAllMessages(videoId);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public List<ICommentary> getAllYTVideoMessagesByVideoId(final String videoId) throws Exception {
        try {
           return this.iCommentaryDaoHibernate.findAllYTCommentariesByVideoId(videoId);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
