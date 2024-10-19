package onl.tesseract.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import onl.tesseract.HibernateUtil;
import onl.tesseract.entity.player.CreativePlayerInfo;
import org.hibernate.Session;

import java.util.UUID;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreativePlayerInfoDAO {
    @Getter
    static private final CreativePlayerInfoDAO instance = new CreativePlayerInfoDAO();


    public CreativePlayerInfo get(UUID uuid) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(CreativePlayerInfo.class, uuid);
        }
    }

    public void create(CreativePlayerInfo creativePlayerInfo) {
        try {
            HibernateUtil.executeInsideTransaction(session -> session.persist(creativePlayerInfo));
            log.info("Created new creativePlayerInfo {}", creativePlayerInfo.getUuid().toString());
        } catch (Exception e) {
            log.error("Erreur lors du save du creativePlayerInfo {}", creativePlayerInfo.getUuid(), e);
        }
    }

    public void refresh(CreativePlayerInfo creativePlayerInfo) {
        try {
            HibernateUtil.executeInsideTransaction( session -> session.refresh(creativePlayerInfo));
        } catch (Exception e) {
            log.error("Erreur lors du refresh du creativePlayerInfo {}", creativePlayerInfo.getUuid(), e);
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.refresh(creativePlayerInfo);
        }
    }

    public void save(CreativePlayerInfo creativePlayerInfo) {
        try {
            HibernateUtil.executeInsideTransaction( session -> session.merge(creativePlayerInfo));
        }catch (Exception e) {
            log.error("Erreur lors du save", e);
        }
    }

}
