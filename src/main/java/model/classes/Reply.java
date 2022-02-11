package model.classes;

import com.google.api.services.youtube.model.Comment;
import lombok.*;
import model.interfaces.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Reply implements IReply {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    public String comment;

    @OneToOne(targetEntity = YoutubeUser.class, cascade=CascadeType.ALL, orphanRemoval = true)
    private IYoutubeUser iYoutubeUser;
}
