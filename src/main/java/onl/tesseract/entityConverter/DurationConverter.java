package onl.tesseract.entityConverter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Duration;
@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {

    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        if(duration == null) {
            return null;
        }
        return duration.toSeconds();
    }

    @Override
    public Duration convertToEntityAttribute(Long l) {
        if (l == null){
            return null;
        }
        return Duration.ofSeconds(l);
    }
}
