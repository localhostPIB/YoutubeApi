package model.interfaces;

import java.util.List;

public interface IYoutubeUser {

     String getUserName();

     void setUserName(String userName);

     String getChannelUrl();

     List<IVideoInfo> getIVideoInfoList();

     void setChannelUrl(String channelUrl);

     void setChannelId(String channelId);

     String getChannelId();

     String getImageUrl();

     void setImageUrl(String imageUrl);

     void addVideoInfo(IVideoInfo iVideoInfo);
}
