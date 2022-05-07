package service.classes;

import service.classes.api.videoInformations.interfaces.IGetYTCommentaries;
import service.inferfaces.ICommentService;

public class CommentService implements ICommentService {

    private final IGetYTCommentaries iGetYTCommentaries;

    public CommentService(IGetYTCommentaries iGetYTCommentaries) {
        this.iGetYTCommentaries = iGetYTCommentaries;
    }

    public void getAllYTVideoMessages(final String videoId) throws Exception {
        try {
            iGetYTCommentaries.getAllMessages(videoId);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
