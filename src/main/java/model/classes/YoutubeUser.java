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
    private String channelId;

    private String userName;

    private String channelUrl;

    private String imageUrl;

}
