package ch.ffm.model.entity;

import ch.ffm.model.entity.convert.Base64PngConverter;
import ch.ffm.model.image.Image;
import java.time.ZonedDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity(name = "t_news")
public class News {

    @Id
    @Column(name = "pk_news_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "publish_timestamp", nullable = false)
    private ZonedDateTime publishTimestamp;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image")
    @Convert(converter = Base64PngConverter.class)
    private Image image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ZonedDateTime getPublishTimestamp() {
        return publishTimestamp;
    }

    public void setPublishTimestamp(ZonedDateTime publishTimestamp) {
        this.publishTimestamp = publishTimestamp;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
