package model.classes;

import lombok.*;
import model.interfaces.IVideoInfo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Getter
@Setter
@Entity
public class VideoInfo implements IVideoInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String VideoId;

    @Column(nullable = false)
    private String timestamp;

    //private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss", Locale.GERMANY);
}
