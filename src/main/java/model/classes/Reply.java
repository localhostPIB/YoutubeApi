package model.classes;

import lombok.*;
import model.interfaces.IYoutubeUser;
import javax.persistence.*;

@Getter
@Setter
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    public String comment;

    @OneToOne(targetEntity = YoutubeUser.class, cascade=CascadeType.ALL,  orphanRemoval = true)
    private IYoutubeUser iYoutubeUser;
}
