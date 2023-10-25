package user.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import user.dto.SkillOfferDto;
import user.entity.SkillOffer;
import user.exception.DataValidationException;
import user.mapper.SkillOfferMapper;
import user.publisher.EventSkillOfferedPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import user.repository.SkillOfferRepository;
import user.validator.SkillOfferValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillOfferService {
    private final SkillOfferRepository skillOfferRepository;
    private final SkillOfferMapper skillOfferMapper;
    private final EventSkillOfferedPublisher skillOfferedPublisher;
    private final SkillOfferValidator validator;

    public SkillOffer getSkillOffer(Long id) {
        return skillOfferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill offer not found"));
    }

    @Transactional
    public SkillOfferDto createSkillOffer(SkillOfferDto dto) {
        validator.validate(dto);
        SkillOffer entity = skillOfferMapper.toEntity(dto);
        if (skillOfferRepository.existsById(dto.getId())) {
            throw new DataValidationException("Such skill offer already exists");
        }
        SkillOfferDto savedDto = skillOfferMapper.toDto(skillOfferRepository.save(entity));
        skillOfferedPublisher.publish(SkillOfferDto.builder()
                .id(dto.getId())
                .skill(dto.getSkill())
                .authorId(dto.getAuthorId())
                .receiverId(dto.getReceiverId())
                .build());
        return savedDto;
    }

    public List<SkillOffer> findAllOffersOfSkill(long skillId, long userId) {
        return skillOfferRepository.findAllOffersOfSkill(skillId, userId);
    }

    public List<SkillOffer> findAllByUserId(long userId) {
        return skillOfferRepository.findAllByUserId(userId);
    }
}