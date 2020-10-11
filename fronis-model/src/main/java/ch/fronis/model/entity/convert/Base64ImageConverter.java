package ch.fronis.model.entity.convert;

import ch.fronis.model.image.Image;
import java.util.Base64;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class Base64ImageConverter implements AttributeConverter<Image, byte[]> {
    @Override
    public byte[] convertToDatabaseColumn(Image image) {
        return Base64.getDecoder()
                .decode(image.getData().replaceFirst("data:image/png;base64,", ""));
    }

    @Override
    public Image convertToEntityAttribute(byte[] dbData) {
        if (dbData == null) {
            return null;
        }
        return Image.from("data:image/png;base64," + Base64.getEncoder().encodeToString(dbData));
    }
}
