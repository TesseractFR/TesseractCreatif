package onl.tesseract.entity.player.rank;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import onl.tesseract.entity.player.CreativePlayerInfo;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.UUID;

@Entity
@Table(name = "t_player_ranks")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerRankInfo {

    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)", unique = true)
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    private UUID uuid;

    @OneToOne
    @MapsId
    @JoinColumn(name = "uuid")
    private CreativePlayerInfo creativePlayerInfo;

    private boolean isVirtuose;

    private PlayerRank playerRank;

    private StaffRank staffRank;

}
