package onl.tesseract.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import onl.tesseract.HibernateUtil;
import onl.tesseract.entity.player.PlayerPlotInfo;
import org.hibernate.Session;

import java.util.UUID;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerPlotInfoDAO {
    @Getter
    static private final PlayerPlotInfoDAO instance = new PlayerPlotInfoDAO();


    public PlayerPlotInfo get(UUID uuid) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(PlayerPlotInfo.class, uuid);
        }
    }

    public void create(PlayerPlotInfo playerPlotInfo) {
        try {
            HibernateUtil.executeInsideTransaction(session -> session.persist(playerPlotInfo));
        } catch (Exception e) {
            log.error("Erreur lors du save du playerPlotInfo {}", playerPlotInfo.getUuid(), e);
        }
    }

    public void refresh(PlayerPlotInfo playerPlotInfo) {
        try {
            HibernateUtil.executeInsideTransaction( session -> session.refresh(playerPlotInfo));
        } catch (Exception e) {
            log.error("Erreur lors du refresh du playerPlotInfo {}", playerPlotInfo.getUuid(), e);
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.refresh(playerPlotInfo);
        }
    }

    public void save(PlayerPlotInfo playerPlotInfo) {
        try {
            HibernateUtil.executeInsideTransaction( session -> session.merge(playerPlotInfo));
        }catch (Exception e) {
            log.error("Erreur lors du save", e);
        }
    }

}
