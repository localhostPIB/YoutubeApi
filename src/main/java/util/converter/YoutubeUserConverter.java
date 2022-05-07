package util.converter;

import javafx.beans.property.SimpleStringProperty;
import model.classes.fx.YoutubeUserFx;
import model.interfaces.IYoutubeUser;
import model.interfaces.fx.IYoutubeUserFx;

public class YoutubeUserConverter {

    public static IYoutubeUserFx convertYoutubeUserToYoutubeUserFx(final IYoutubeUser iYoutubeUser){
        IYoutubeUserFx iYoutubeUserFx = new YoutubeUserFx();
        iYoutubeUserFx.setUserName(new SimpleStringProperty(iYoutubeUser.getUserName()));
        iYoutubeUserFx.setChannelId(new SimpleStringProperty(iYoutubeUser.getChannelId()));
        iYoutubeUserFx.setImageUrl(new SimpleStringProperty(iYoutubeUser.getImageUrl()));
        iYoutubeUserFx.setImageUrl(new SimpleStringProperty(iYoutubeUser.getImageUrl()));

        return iYoutubeUserFx;
    }
}
