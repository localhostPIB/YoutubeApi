package model.classes;

import lombok.*;
import model.interfaces.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;

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

    @OneToMany(targetEntity = VideoInfo.class, fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @Column(name = "ytVideoList")
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
