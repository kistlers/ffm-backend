package ch.fronis.model.entity.convert;

import ch.fronis.model.image.Image;
import java.util.Base64;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter(autoApply = true)
public class Base64SvgConverter implements AttributeConverter<Image, byte[]> {

    private static final Logger logger = LoggerFactory.getLogger(Base64SvgConverter.class);

    @Override
    public byte[] convertToDatabaseColumn(Image image) {
        if (image == null || image.getData() == null) {
            return null;
        }
        logger.debug(image.getData());
        return Base64.getDecoder().decode(image.getData().replaceFirst("data:image/svg\\+xml;base64,", ""));
    }

    @Override
    public Image convertToEntityAttribute(byte[] dbData) {
        if (dbData == null) {
            return null;
        }
        return Image.from("data:image/svg+xml;base64," + Base64.getEncoder().encodeToString(dbData));
    }
}
