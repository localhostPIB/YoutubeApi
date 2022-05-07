package model.interfaces.fx;

import javafx.beans.property.StringProperty;

public interface IYoutubeUserFx {
    StringProperty getChannelId();

    StringProperty getUserName();

    StringProperty getChannelUrl();

    StringProperty getImageUrl();

    void setChannelId(StringProperty channelId);

    void setUserName(StringProperty userName);

    void setChannelUrl(StringProperty channelUrl);

    void setImageUrl(StringProperty imageUrl);
}
