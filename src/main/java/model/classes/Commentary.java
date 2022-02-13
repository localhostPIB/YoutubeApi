package model.classes;

import lombok.*;
import model.interfaces.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Commentary implements ICommentary {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    public String comment;

    @OneToOne(targetEntity = YoutubeUser.class, cascade=CascadeType.ALL, orphanRemoval = true)
    private IYoutubeUser iYoutubeUser;

    @OneToMany(targetEntity = Reply.class, cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "reply_id")
    private List<IReply> iReplyList = new ArrayList<>();

    @Override
    public void addIReply(IReply iReply){
        iReplyList.add(iReply);
    }
}
