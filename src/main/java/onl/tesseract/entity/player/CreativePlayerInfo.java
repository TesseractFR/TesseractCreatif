package onl.tesseract.entity.player;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import onl.tesseract.entity.player.rank.PlayerRankInfo;
import org.hibernate.annotations.JdbcTypeCode;

import java.time.Duration;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "t_player_creative")
public class CreativePlayerInfo {
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)", unique = true)
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    UUID uuid;

    @OneToOne(mappedBy = "creativePlayerInfo")
    PlayerPlot playerPlot;

    @OneToOne(mappedBy = "creativePlayerInfo")
    PlayerRankInfo playerRankInfo;

    Duration timePlayed;

}
