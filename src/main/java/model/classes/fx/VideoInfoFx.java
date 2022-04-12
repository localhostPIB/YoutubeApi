package model.classes.fx;

import javafx.beans.property.StringProperty;
import lombok.*;
import model.interfaces.fx.IVideoInfoFx;

import javax.persistence.Column;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoInfoFx implements IVideoInfoFx {

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
