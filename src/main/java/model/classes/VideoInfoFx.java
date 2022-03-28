package model.classes;

import javafx.beans.property.StringProperty;
import lombok.*;

import javax.persistence.Column;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoInfoFx {

    private StringProperty videoId;

    private StringProperty timestamp;

    private StringProperty title;

    private StringProperty channelTitle;

    private StringProperty viewCount;

    private StringProperty likes;

    private StringProperty videoDescription;

    private StringProperty commentCount;

    private StringProperty favorite;
}
