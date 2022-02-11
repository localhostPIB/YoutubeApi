package api;

import com.google.api.client.util.Lists;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import dao.*;
import model.classes.Reply;
import model.interfaces.*;
import util.PropertyUtils;
import util.StaticModelFactory;
import util.validator.APIValidator;

import java.io.*;
import java.util.List;

public class PrintYTCommentaries {
    private static String CLIENT_SECRET = null;

    private YTUserDaoHibernateImp ytUserDaoHibernateImp;

    private CommentaryDaoHibernateImp commentaryDaoHibernateImp;

    private VideoDaoHibernateImp videoDaoHibernateImp;

    private ReplyDaoHibernateImp replyDaoHibernateImp;

    public PrintYTCommentaries(VideoDaoHibernateImp videoDaoHibernateImp,
                               YTUserDaoHibernateImp ytUserDaoHibernateImp,
                               CommentaryDaoHibernateImp commentaryDaoHibernateImp,
                                ReplyDaoHibernateImp replyDaoHibernateImp) {
        this.ytUserDaoHibernateImp = ytUserDaoHibernateImp;
        this.commentaryDaoHibernateImp = commentaryDaoHibernateImp;
        this.videoDaoHibernateImp = videoDaoHibernateImp;
        this.replyDaoHibernateImp = replyDaoHibernateImp;
        try {
            CLIENT_SECRET = PropertyUtils.readPropertyFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private YouTube.CommentThreads.List prepareListRequest(String videoId) throws Exception {

        return Auth.getService().commentThreads()
                .list("snippet,replies")
                .setKey(CLIENT_SECRET)
                .setVideoId(videoId)
                .setMaxResults(100L)
                .setModerationStatus("published");
    }

    public void getAllMessages(String videoId) throws Exception {
        IVideoInfo iVideoInfo = StaticModelFactory.getVideoInfoObject();
        iVideoInfo.setVideoId(videoId);
        iVideoInfo.setTimestamp(StaticModelFactory.getActualDate());
        videoDaoHibernateImp.saveVideo(iVideoInfo);

        CommentThreadListResponse commentsPage = prepareListRequest(videoId).execute();

        while (true) {
            handleCommentsThreads(commentsPage.getItems());

            String nextPageToken = commentsPage.getNextPageToken();

            if (APIValidator.checkNextPageToken(nextPageToken)) {
                break;
            }
            commentsPage = prepareListRequest(videoId).setPageToken(nextPageToken).execute();
        }
    }

    private void handleCommentsThreads(List<CommentThread> commentThreads) throws IOException {
        for (CommentThread commentThread : commentThreads) {
            List<Comment> comments = Lists.newArrayList();
            comments.add(commentThread.getSnippet().getTopLevelComment());

            CommentThreadReplies replies = commentThread.getReplies();
            if (replies != null) {
                comments.addAll(replies.getComments());

                for (Comment repl : replies.getComments()){
                    IReply iReply = new Reply();
                    iReply.setComment(repl.getSnippet().getTextDisplay());
                    IYoutubeUser youtubeUser = StaticModelFactory
                            .getYoutubeUserObject(repl.getSnippet().getAuthorDisplayName(),
                                    repl.getSnippet().getAuthorChannelUrl());
                    iReply.setIYoutubeUser(youtubeUser);
                    replyDaoHibernateImp.saveCommentary(iReply);
                }
            }


            IYoutubeUser youtubeUser = StaticModelFactory
                    .getYoutubeUserObject(comments.get(0).getSnippet().getAuthorDisplayName(),
                    comments.get(0).getSnippet().getAuthorChannelUrl());

            youtubeUser.setChannelId(comments.get(0).getSnippet().getAuthorChannelId().toString());
            youtubeUser.setImageUrl(comments.get(0).getSnippet().getAuthorProfileImageUrl());
            ICommentary iCommentary = StaticModelFactory.getCommentaryObject();
            iCommentary.setComment(comments.get(0).getSnippet().getTextOriginal());
            iCommentary.setIYoutubeUser(youtubeUser);
            ytUserDaoHibernateImp.saveUser(youtubeUser);
            commentaryDaoHibernateImp.saveCommentary(iCommentary);
            System.out.println("Kommentar von: " + comments.get(0).getSnippet().getAuthorDisplayName() + " Kommentar: "
                    + comments.get(0).getSnippet().getTextOriginal());
        }
    }
}