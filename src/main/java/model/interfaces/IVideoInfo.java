package model.interfaces;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public interface IVideoInfo {
    String getVideoId();

    void setVideoId(String VideoId);

    void setTimestamp(String timestamp);
}
