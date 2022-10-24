package com.example.bank.controller.facade;

import com.example.bank.controller.dto.CardDto;
import com.example.bank.entity.Card;
import com.example.bank.mapper.CardMapper;
import com.example.bank.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardFacade {

    private final CardService cardService;
    private final CardMapper cardMapper;

    public CardDto findById(Long id) {
        Card card = cardService.findById(id);
        CardDto cardDto = cardMapper.convertToDto(card);
        cardDto.setStatus(cardService.getLogicCardStatus(card));
        return cardDto;
    }

    public CardDto save(CardDto cardDto) {
        Card card = cardMapper.convertToObject(cardDto);
        Card savedCard = cardService.save(card);
        return cardMapper.convertToDto(savedCard);
    }
}
