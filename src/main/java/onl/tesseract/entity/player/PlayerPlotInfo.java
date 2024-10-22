package onl.tesseract.entity.player;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.UUID;

@Entity
@Table(name = "t_player_plot")
@Data
public class PlayerPlotInfo {
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)", unique = true)
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    UUID uuid;

    @OneToOne
    @PrimaryKeyJoinColumn
    private CreativePlayerInfo creativePlayerInfo;

    @Column
    int nbPlot100;
    @Column
    int nbPlot250;
    @Column
    int nbPlot500;
    @Column
    int nbPlot1000;

}
