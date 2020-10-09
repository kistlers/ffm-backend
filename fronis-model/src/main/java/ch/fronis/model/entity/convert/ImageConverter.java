package ch.fronis.model.entity.convert;

import ch.fronis.model.image.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.persistence.AttributeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageConverter implements AttributeConverter<Image, String> {

    private static final Logger logger = LoggerFactory.getLogger(ImageConverter.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Image image) {
        try {
            return objectMapper.writeValueAsString(image);
        } catch (final JsonProcessingException e) {
            logger.error("JSON writing error", e);
        }

        return null;
    }

    @Override
    public Image convertToEntityAttribute(String image) {
        try {
            return objectMapper.readValue(image, Image.class);
        } catch (final IOException e) {
            logger.error("JSON reading error", e);
        }

        return null;
    }
}
