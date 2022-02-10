package model.classes;

import lombok.*;
import model.interfaces.IYoutubeUser;

import javax.persistence.*;
import javax.persistence.Id;


@Getter
@Setter
@Entity
public class YoutubeUser implements IYoutubeUser {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    public String userName;

    public String channelUrl;

    public String channelId;

    public YoutubeUser(String userName, String channelUrl){
        this.channelUrl = channelUrl;
        this.userName = userName;
    }

    public YoutubeUser() {

    }
}
