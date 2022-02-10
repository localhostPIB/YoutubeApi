package api;

import com.google.api.client.util.Lists;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import dao.*;
import model.interfaces.*;
import util.PropertiesUtils;
import util.StaticModelFactory;
import util.validator.APIValidator;

import java.io.*;
import java.util.List;

public class PrintYTCommentaries {
    private static String CLIENT_SECRET = null;

    private YTUserDaoHibernateImp ytUserDaoHibernateImp;

    private CommentaryDaoHibernateImp commentaryDaoHibernateImp;

    private VideoDaoHibernateImp videoDaoHibernateImp;

    public PrintYTCommentaries(VideoDaoHibernateImp videoDaoHibernateImp,
                               YTUserDaoHibernateImp ytUserDaoHibernateImp,
                               CommentaryDaoHibernateImp commentaryDaoHibernateImp) {
        this.ytUserDaoHibernateImp = ytUserDaoHibernateImp;
        this.commentaryDaoHibernateImp = commentaryDaoHibernateImp;
        this.videoDaoHibernateImp = videoDaoHibernateImp;

        try {
            CLIENT_SECRET = PropertiesUtils.readPropertyFile();
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
            }
            IYoutubeUser youtubeUser = StaticModelFactory.getYoutubeUser(comments.get(0).getSnippet().getAuthorDisplayName(), comments.get(0).getSnippet().getAuthorChannelUrl());
            youtubeUser.setChannelId(comments.get(0).getSnippet().getChannelId());
            ICommentary iCommentary = StaticModelFactory.getCommentary();
            iCommentary.setComment(comments.get(0).getSnippet().getTextOriginal());
            iCommentary.setIYoutubeUser(youtubeUser);
            ytUserDaoHibernateImp.saveUser(youtubeUser);
            commentaryDaoHibernateImp.saveCommentary(iCommentary);
            System.out.println("Kommentar von: " + comments.get(0).getSnippet().getAuthorDisplayName() + " Kommentar: " + comments.get(0).getSnippet().getTextOriginal());
        }
    }
}