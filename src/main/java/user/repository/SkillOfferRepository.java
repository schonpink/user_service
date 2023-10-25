package user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import user.entity.SkillOffer;

import java.util.List;


@Repository
public interface SkillOfferRepository extends CrudRepository<SkillOffer, Long> {

    @Query(value = """
            SELECT so FROM SkillOffer so
            JOIN so.recommendation r
            WHERE so.skill.id = :skillId AND r.receiver.id = :userId
            """)
    List<SkillOffer> findAllOffersOfSkill(long skillId, long userId);

    @Query(value = """
            SELECT so FROM SkillOffer so
            JOIN so.recommendation r
            WHERE r.receiver.id = :userId
            """)
    List<SkillOffer> findAllByUserId(long userId);
}