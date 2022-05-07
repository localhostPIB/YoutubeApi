package model.classes.fx;

import javafx.beans.property.*;
import lombok.*;
import model.interfaces.fx.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class YoutubeUserFx implements IYoutubeUserFx {
    private StringProperty channelId;

    private StringProperty userName;

    private StringProperty channelUrl;

    private StringProperty imageUrl;

    private ListProperty<IVideoInfoFx> iVideoInfoFxListProperty;
}
