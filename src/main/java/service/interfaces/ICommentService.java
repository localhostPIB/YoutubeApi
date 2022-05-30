package service.interfaces;

import model.interfaces.ICommentary;
import service.classes.api.videoInformations.interfaces.IGetYTCommentaries;

import java.util.List;

public interface ICommentService {

   void saveAllYTVideoMessagesByVideoId(final String videoId, final IGetYTCommentaries iGetYTCommentaries) throws Exception;

   List<ICommentary> getAllYTVideoMessagesByVideoId(final String videoId) throws Exception;

   void deleteAllYTVideoMessagesByVideoId(final String VideoId) throws Exception;
}
