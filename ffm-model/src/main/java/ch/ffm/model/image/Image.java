package ch.ffm.model.image;

import java.io.Serializable;

public class Image implements Serializable {

    private String data;

    public static Image from(final String data) {
        final var image = new Image();
        image.setData(data);
        return image;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
