package user.mapper;

import org.mapstruct.*;
import user.dto.SkillOfferDto;
import user.entity.SkillOffer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, injectionStrategy = InjectionStrategy.FIELD)
public interface SkillOfferMapper {
    @Mapping(source = "skill", target = "skill.id")
    @Mapping(source = "recommendation", target = "recommendation.id")
    @Mapping(source = "authorId",target = "recommendation.author.id")
    @Mapping(source = "receiverId", target = "recommendation.receiver.id")
    SkillOffer toEntity(SkillOfferDto dto);

    @Mapping(source = "skill.id", target = "skill")
    @Mapping(source = "recommendation.id", target = "recommendation")
    @Mapping(source = "recommendation.author.id", target = "authorId")
    @Mapping(source = "recommendation.receiver.id", target = "receiverId")
    SkillOfferDto toDto(SkillOffer entity);

    @Named("toSkillOfferDtos")
    default List<SkillOfferDto> toSkillOfferDtos(List<SkillOffer> skills) {
        if (skills == null) {
            return Collections.emptyList();
        }
        return skills.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Named("mapAuthorId")
    default Long mapAuthorId(Recommendation recommendation) {
        return recommendation.getAuthor().getId();
    }

    @Named("mapReceiverId")
    default Long mapReceiverId(Recommendation recommendation) {
        return recommendation.getReceiver().getId();
    }
}