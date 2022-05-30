package model.classes;

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

    @ManyToOne(targetEntity = VideoInfo.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private IVideoInfo iVideoInfo;

    @OneToOne(targetEntity = YoutubeUser.class, orphanRemoval = true, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private IYoutubeUser iYoutubeUser;

    @OneToMany(targetEntity = Reply.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "reply_id")
    private List<IReply> iReplyList = new ArrayList<>();

    public Commentary(long likes, String publishAt, String comment, IYoutubeUser iYoutubeUser, IVideoInfo iVideoInfo){
            this.likes = likes;
            this.publishAt = publishAt;
            this.comment = comment;
            this.iYoutubeUser = iYoutubeUser;
            this.iVideoInfo = iVideoInfo;
    }

    @Override
    public void addIReply(IReply iReply){
        iReplyList.add(iReply);
    }

    @Override
    public String toString(){
        return getLikes() +"\t"+ getPublishAt() +"\t"+ getComment() +"\t"+  iYoutubeUser.getUserName();
    }
}
