package model.classes;

import lombok.*;
import model.interfaces.ICommentary;
import model.interfaces.IYoutubeUser;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
public class Commentary implements ICommentary {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    public String comment;

    @OneToMany
    private List<Reply> reply = new ArrayList<>();

    @OneToOne(targetEntity = YoutubeUser.class, cascade=CascadeType.ALL,  orphanRemoval = true)
    private IYoutubeUser iYoutubeUser;
}
