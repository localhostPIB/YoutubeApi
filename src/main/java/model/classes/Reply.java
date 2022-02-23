package model.classes;

import lombok.*;
import model.interfaces.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Reply implements IReply {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String comment;

    private long likes;

    private String publishAt;

    @OneToOne(targetEntity = YoutubeUser.class, cascade=CascadeType.ALL, orphanRemoval = true)
    private IYoutubeUser iYoutubeUser;

    public Reply(String comment, IYoutubeUser iYoutubeUser, long likes, String publishAt){
        this.comment = comment;
        this.iYoutubeUser = iYoutubeUser;
        this.likes = likes;
        this.publishAt = publishAt;
    }
}
