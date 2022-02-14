package model.classes;

import lombok.*;
import model.interfaces.IYoutubeUser;

import javax.persistence.*;
import javax.persistence.Id;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class YoutubeUser implements IYoutubeUser {

    @Id
    public String channelId;

    public String userName;

    public String channelUrl;

    public String imageUrl;

}
