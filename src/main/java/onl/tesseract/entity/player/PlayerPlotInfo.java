package onl.tesseract.entity.player;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.UUID;

@Entity
@Table(name = "t_player_plot")
public class PlayerPlotInfo {
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)", unique = true)
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    UUID player_uuid;

    @OneToOne
    @MapsId
    @JoinColumn(name = "uuid")
    private CreativePlayerInfo creativePlayerInfo;

    @Column
    int nbPlot100;
    @Column
    int nbPlot200;
    @Column
    int nbPlot500;
    @Column
    int nbPlot1000;

}
