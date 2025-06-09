package onl.tesseract.creative.util.entityConverter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.time.Duration

@Converter(autoApply = true)
class DurationConverter : AttributeConverter<Duration, Long> {
    override fun convertToDatabaseColumn(duration: Duration): Long {
        print(duration.toSeconds())
        return duration.toSeconds()
    }

    override fun convertToEntityAttribute(l: Long): Duration {
        return Duration.ofSeconds(l)
    }
}
