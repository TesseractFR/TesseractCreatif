package onl.tesseract.entity.player.rank;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public enum RankPlot {
    APPRENTI_PLOT(1,1,0,0),
    CONCEPTEUR_PLOT(2,2,0,0),
    CREATEUR_PLOT(3,3,1,0),
    INGENIEUR_PLOT(4,4,2,0),
    BATISSEUR_PLOT(5,5,3,1),
    VIRTUOSEUR_PLOT(6,6,4,2),

    ;
    final int plot100;
    final int plot250;
    final int plot500;
    final int plot1000;
    RankPlot(int plot100, int plot250, int plot500, int plot1000) {
        this.plot100 = plot100;
        this.plot250 = plot250;
        this.plot500 = plot500;
        this.plot1000 = plot1000;
    }
}
