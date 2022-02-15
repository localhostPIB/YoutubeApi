package api;

import dao.*;
import util.*;
import model.interfaces.*;
import util.validator.APIValidator;
import com.google.api.client.util.Lists;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;

import java.io.*;
import java.util.List;

public class GetYTCommentaries {
    private static String CLIENT_SECRET;

    private final YTUserDaoHibernateImp ytUserDaoHibernateImp;

    private final CommentaryDaoHibernateImp commentaryDaoHibernateImp;

    private final ReplyDaoHibernateImp replyDaoHibernateImp;


    public GetYTCommentaries(YTUserDaoHibernateImp ytUserDaoHibernateImp,
                             CommentaryDaoHibernateImp commentaryDaoHibernateImp,
                             ReplyDaoHibernateImp replyDaoHibernateImp) {
        this.ytUserDaoHibernateImp = ytUserDaoHibernateImp;
        this.commentaryDaoHibernateImp = commentaryDaoHibernateImp;
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

    private void handleCommentsThreads(List<CommentThread> commentThreads) {

        for (CommentThread commentThread : commentThreads) {
            List<Comment> comments = Lists.newArrayList();
            comments.add(commentThread.getSnippet().getTopLevelComment());

            IYoutubeUser iYoutubeUser = StaticModelFactory
                    .getYoutubeUserObject(comments.get(0).getSnippet().getAuthorDisplayName(),
                            comments.get(0).getSnippet().getAuthorChannelUrl(),
                            comments.get(0).getSnippet().getAuthorChannelId().toString(),
                            comments.get(0).getSnippet().getAuthorProfileImageUrl());

            iYoutubeUser.setImageUrl(comments.get(0).getSnippet().getAuthorProfileImageUrl());
            saveYTUser(iYoutubeUser);

            ICommentary iCommentary = StaticModelFactory.getCommentaryObject();
            iCommentary.setComment(comments.get(0).getSnippet().getTextOriginal());
            iCommentary.setIYoutubeUser(iYoutubeUser);

            CommentThreadReplies replies = commentThread.getReplies();
            if (replies != null) {
                comments.addAll(replies.getComments());

                for (Comment repl : replies.getComments()){
                    IYoutubeUser youtubeUser = StaticModelFactory
                            .getYoutubeUserObject(repl.getSnippet().getAuthorDisplayName(),
                                    repl.getSnippet().getAuthorChannelUrl(),
                                    repl.getSnippet().getAuthorChannelId().toString(),
                                    repl.getSnippet().getAuthorProfileImageUrl());
                    IReply iReply = StaticModelFactory.getReplyObject(repl.getSnippet().getTextDisplay(),youtubeUser);
                    saveReply(iReply);
                    iCommentary.addIReply(iReply);
                }
            }

            saveCommentary(iCommentary);
            //System.out.println("Kommentar von: " + comments.get(0).getSnippet().getAuthorDisplayName() + " Kommentar: "
              //      + comments.get(0).getSnippet().getTextOriginal());
        }
    }

    private void saveCommentary(ICommentary iCommentary){
        commentaryDaoHibernateImp.saveCommentary(iCommentary);
    }

    private void saveYTUser(IYoutubeUser iYoutubeUser){
        ytUserDaoHibernateImp.saveUser(iYoutubeUser);
    }

    private void saveReply(IReply iReply){
        replyDaoHibernateImp.saveReply(iReply);
    }
}