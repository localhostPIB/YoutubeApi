package model.classes;

import lombok.*;
import model.interfaces.IVideoInfo;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class VideoInfo implements IVideoInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String videoId;

    @Column(nullable = false)
    private String timestamp;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String channelTitle;

    @Column(nullable = false)
    private BigInteger viewCount;

    @Column(nullable = false)
    private BigInteger likes;

    private String videoDescription;

    @Column(nullable = false)
    private BigInteger commentCount;

    @Column(nullable = false)
    private BigInteger favorite;

    public VideoInfo(String videoId, String timestamp, BigInteger viewCount, BigInteger likes,
                     BigInteger commentCount, BigInteger favorite, String title,
                     String channelTitle, String videoDescription){

        this.videoId = videoId;
        this.timestamp = timestamp;
        this.viewCount = viewCount;
        this.likes = likes;
        this.commentCount = commentCount;
        this.favorite = favorite;
        this.title = title;
        this.channelTitle = channelTitle;
        this.videoDescription = videoDescription;
    }
}
