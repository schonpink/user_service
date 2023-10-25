package user.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.dto.SkillOfferDto;
import user.entity.SkillOffer;
import user.service.SkillOfferService;
import java.util.ArrayList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class SkillControllerTest {
    @InjectMocks
    private SkillController skillController;
    @Mock
    private SkillOfferService skillOfferService;

    @Test
    void testCreateSkillOffer_ValidDto() {
        SkillOfferDto requestDto = new SkillOfferDto();
        when(skillOfferService.createSkillOffer(requestDto)).thenReturn(requestDto);

        SkillOfferDto response = skillController.createSkillOffer(requestDto, 1L, 2L);

        assertNotNull(response);
        assertEquals(requestDto, response);
        verify(skillOfferService, times(1)).createSkillOffer(requestDto);
    }

    @Test
    void getAllSkillOffersByUserId_ValidUserId_ReturnsSkillOffers() {
        long userId = 1L;
        List<SkillOffer> skillOffers = new ArrayList<>();
        when(skillOfferService.findAllByUserId(userId)).thenReturn(skillOffers);

        List<SkillOffer> response = skillController.getAllSkillOffersByUserId(userId);

        assertEquals(skillOffers, response);
    }

    @Test
    void getAllSkillOffersOfSkillForUser_ValidSkillIdAndUserId() {
        long skillId = 1L;
        long userId = 2L;
        List<SkillOffer> skillOffers = new ArrayList<>();
        when(skillOfferService.findAllOffersOfSkill(skillId, userId)).thenReturn(skillOffers);

        List<SkillOffer> response = skillController.getAllSkillOffersOfSkillForUser(skillId, userId);

        assertEquals(skillOffers, response);
    }
}