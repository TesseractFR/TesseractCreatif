package onl.tesseract.entityConverter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.time.Duration

@Converter(autoApply = true)
class DurationConverter : AttributeConverter<Duration?, Long?> {
    override fun convertToDatabaseColumn(duration: Duration?): Long? {
        if (duration == null) {
            return null
        }
        print(duration.toSeconds())
        return duration.toSeconds()
    }

    override fun convertToEntityAttribute(l: Long?): Duration? {
        if (l == null) {
            return null
        }
        return Duration.ofSeconds(l)
    }
}
