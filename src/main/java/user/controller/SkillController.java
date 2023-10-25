package user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import user.dto.SkillOfferDto;
import user.entity.SkillOffer;
import user.service.SkillOfferService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SkillController {
    private final SkillOfferService skillOfferService;

    @PostMapping("/{skillId}/user/{userId}")
    public SkillOfferDto createSkillOffer(@Valid @RequestBody SkillOfferDto skillOfferDto,
                                          @PathVariable Long skillId,
                                          @PathVariable Long userId) {
        log.debug("Received request to create skill offer for Skill Id: {} and User Id: {}", skillId, userId);

        return skillOfferService.createSkillOffer(skillOfferDto);
    }

    @GetMapping("/user/{userId}")
    public List<SkillOffer> getAllSkillOffersByUserId(@PathVariable Long userId) {
        return skillOfferService.findAllByUserId(userId);
    }

    @GetMapping("/skill/{skillId}/user/{userId}")
    public List<SkillOffer> getAllSkillOffersOfSkillForUser(
            @PathVariable Long skillId, @PathVariable Long userId) {
        return skillOfferService.findAllOffersOfSkill(skillId, userId);
    }
}