package model.classes;

import lombok.*;
import model.interfaces.IVideoInfo;
import model.interfaces.IYoutubeUser;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class YoutubeUser implements IYoutubeUser {
    @Id
    private String channelId;

    private String userName;

    private String channelUrl;

    private String imageUrl;

    @OneToMany(targetEntity =VideoInfo.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "videoInfo_id")
    private List<IVideoInfo> iVideoInfoList = new ArrayList<>();

    public YoutubeUser(String channelId, String userName, String channelUrl, String imageUrl){
        this.channelId = channelId;
        this.userName = userName;
        this.channelUrl = channelUrl;
        this.imageUrl = imageUrl;
    }

    @Override
    public void addVideoInfo(IVideoInfo iVideoInfo){
        iVideoInfoList.add(iVideoInfo);
    }

    @Override
    public String toString(){
       return getChannelId() +"\t"+ getUserName() +"\t"+ getChannelUrl() +"\t"+ getImageUrl();
    }

}
