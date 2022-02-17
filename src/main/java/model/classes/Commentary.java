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

    public long likes;

    public String publishAt;

    public String comment;

    @OneToOne(targetEntity = YoutubeUser.class, cascade=CascadeType.ALL, orphanRemoval = true)
    private IYoutubeUser iYoutubeUser;

    @OneToMany(targetEntity = Reply.class, cascade=CascadeType.ALL)
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
