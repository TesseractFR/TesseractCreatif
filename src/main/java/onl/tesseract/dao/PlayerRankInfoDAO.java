package onl.tesseract.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import onl.tesseract.HibernateUtil;
import onl.tesseract.entity.player.rank.PlayerRankInfo;
import org.hibernate.Session;

import java.util.UUID;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerRankInfoDAO {
    @Getter
    static private final PlayerRankInfoDAO instance = new PlayerRankInfoDAO();


    public PlayerRankInfo get(UUID uuid) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(PlayerRankInfo.class, uuid);
        }
    }

    public void create(PlayerRankInfo playerRankInfo) {
        try {
            HibernateUtil.executeInsideTransaction(session -> session.persist(playerRankInfo));
        } catch (Exception e) {
            log.error("Erreur lors du save du playerRankInfo {}", playerRankInfo.getUuid(), e);
        }
    }

    public void refresh(PlayerRankInfo playerRankInfo) {
        try {
            HibernateUtil.executeInsideTransaction( session -> session.refresh(playerRankInfo));
        } catch (Exception e) {
            log.error("Erreur lors du refresh du playerRankInfo {}", playerRankInfo.getUuid(), e);
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.refresh(playerRankInfo);
        }
    }

    public void save(PlayerRankInfo playerRankInfo) {
        try {
            HibernateUtil.executeInsideTransaction( session -> session.merge(playerRankInfo));
        }catch (Exception e) {
            log.error("Erreur lors du save", e);
        }
    }

}
