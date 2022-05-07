package service.classes.api.videoInformations.classes;

import dao.classes.CommentaryDaoHibernateImp;
import dao.classes.ReplyDaoHibernateImp;
import dao.classes.YTUserDaoHibernateImp;
import service.classes.api.Auth;
import service.classes.api.videoInformations.interfaces.IGetYTCommentaries;
import util.*;
import model.interfaces.*;
import util.validator.APIValidator;
import com.google.api.client.util.Lists;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;

import java.io.*;
import java.util.List;

public class GetYTCommentaries implements IGetYTCommentaries {
    private static String CLIENT_SECRET;

    private final YTUserDaoHibernateImp ytUserDaoHibernateImp;

    private final CommentaryDaoHibernateImp commentaryDaoHibernateImp;

    private final ReplyDaoHibernateImp replyDaoHibernateImp;

    private final IVideoInfo iVideoInfo;


    public GetYTCommentaries(YTUserDaoHibernateImp ytUserDaoHibernateImp,
                             CommentaryDaoHibernateImp commentaryDaoHibernateImp,
                             ReplyDaoHibernateImp replyDaoHibernateImp,IVideoInfo iVideoInfo) {
        this.ytUserDaoHibernateImp = ytUserDaoHibernateImp;
        this.commentaryDaoHibernateImp = commentaryDaoHibernateImp;
        this.replyDaoHibernateImp = replyDaoHibernateImp;
        this.iVideoInfo = iVideoInfo;

        try {
            CLIENT_SECRET = PropertyUtils.readPropertyFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private YouTube.CommentThreads.List prepareListRequest(final String videoId) throws Exception {
        try {
            return Auth.getService().commentThreads()
                    .list("snippet,replies")
                    .setKey(CLIENT_SECRET)
                    .setVideoId(videoId)
                    .setMaxResults(100L)
                    .setModerationStatus("published");
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public void getAllMessages(final String videoId) throws Exception {
        try {
            CommentThreadListResponse commentsPage = prepareListRequest(videoId).execute();

            while (true) {
                handleCommentsThreads(commentsPage.getItems());

                String nextPageToken = commentsPage.getNextPageToken();

                if (APIValidator.checkNextPageToken(nextPageToken)) {
                    break;
                }
                commentsPage = prepareListRequest(videoId).setPageToken(nextPageToken).execute();
            }
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    private void handleCommentsThreads(final List<CommentThread> commentThreads) throws Exception {
        try {
            for (CommentThread commentThread : commentThreads) {
                List<Comment> comments = Lists.newArrayList();
                comments.add(commentThread.getSnippet().getTopLevelComment());

                IYoutubeUser iYoutubeUser = StaticModelFactory
                        .getYoutubeUserObject(comments.get(0).getSnippet().getAuthorDisplayName(),
                                comments.get(0).getSnippet().getAuthorChannelUrl(),
                                StringUtils.cutStringBeforeChar(comments.get(0).getSnippet().getAuthorChannelId().toString()),
                                comments.get(0).getSnippet().getAuthorProfileImageUrl());

                iYoutubeUser.setImageUrl(comments.get(0).getSnippet().getAuthorProfileImageUrl());
                iYoutubeUser.addVideoInfo(iVideoInfo);
                saveYTUser(iYoutubeUser);

                ICommentary iCommentary = StaticModelFactory.getCommentaryObject(comments.get(0).getSnippet().getLikeCount(),
                        comments.get(0).getSnippet().getPublishedAt().toString(), comments.get(0).getSnippet().getTextOriginal(),
                        iYoutubeUser, iVideoInfo);
                saveCommentary(iCommentary);

                CommentThreadReplies replies = commentThread.getReplies();

                if (replies != null) {
                    handleReplyThreads(replies, iCommentary);
                }
            }
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    private void handleReplyThreads(final CommentThreadReplies replies, final ICommentary iCommentary) throws Exception {
        try {
            for (Comment reply : replies.getComments()) {
                IYoutubeUser iYoutubeUser = StaticModelFactory
                        .getYoutubeUserObject(reply.getSnippet().getAuthorDisplayName(),
                                reply.getSnippet().getAuthorChannelUrl(),
                                StringUtils.cutStringBeforeChar(reply.getSnippet().getAuthorChannelId().toString()),
                                reply.getSnippet().getAuthorProfileImageUrl());
                iYoutubeUser.addVideoInfo(iVideoInfo);
                saveYTUser(iYoutubeUser);

                IReply iReply = StaticModelFactory.getReplyObject(reply.getSnippet().getTextDisplay(),
                        iYoutubeUser, reply.getSnippet().getLikeCount(),
                        reply.getSnippet().getPublishedAt().toString());

                saveReply(iReply);
                iCommentary.addIReply(iReply);
            }

            saveCommentary(iCommentary);
        }catch (Exception e){
          throw new Exception(e);
        }
    }

    private void saveCommentary(final ICommentary iCommentary) throws Exception {
        try {
            commentaryDaoHibernateImp.saveCommentary(iCommentary);
        }catch (Exception e){
            throw new Exception(e);
        }

    }

    private void saveYTUser(final IYoutubeUser iYoutubeUser) throws Exception {
        try {
            ytUserDaoHibernateImp.saveUser(iYoutubeUser);
        }catch (Exception ex){
            throw new Exception(ex);
        }
    }

    private void saveReply(final IReply iReply) throws Exception {
        try {
            replyDaoHibernateImp.saveReply(iReply);
        }catch (Exception ex){
            throw new Exception(ex);
        }
    }
}
