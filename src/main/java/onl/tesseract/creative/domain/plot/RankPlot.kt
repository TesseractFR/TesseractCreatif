package onl.tesseract.creative.domain.plot

import lombok.AccessLevel
import lombok.Getter
import lombok.experimental.FieldDefaults

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
enum class RankPlot(val plot100: Int, val plot250: Int, val plot500: Int, val plot1000: Int) {
    APPRENTI_PLOT(1, 1, 0, 0),
    CONCEPTEUR_PLOT(2, 2, 0, 0),
    CREATEUR_PLOT(3, 3, 1, 0),
    INGENIEUR_PLOT(4, 4, 2, 0),
    BATISSEUR_PLOT(5, 5, 3, 1),
    VIRTUOSEUR_PLOT(6, 6, 4, 2),
}