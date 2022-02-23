package model.classes;

import com.google.api.client.util.DateTime;
import lombok.*;
import model.interfaces.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Commentary implements ICommentary {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private long likes;

    private String publishAt;

    private String comment;

    @OneToOne(targetEntity = YoutubeUser.class, orphanRemoval = true, cascade=CascadeType.ALL)
    private IYoutubeUser iYoutubeUser;

    @OneToMany(targetEntity = Reply.class)
    @JoinColumn(name = "reply_id")
    private List<IReply> iReplyList = new ArrayList<>();

    public Commentary(long likes, String publishAt, String comment, IYoutubeUser iYoutubeUser){
            this.likes = likes;
            this.publishAt = publishAt;
            this.comment = comment;
            this.iYoutubeUser = iYoutubeUser;
    }

    @Override
    public void addIReply(IReply iReply){
        iReplyList.add(iReply);
    }
}
