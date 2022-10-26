package com.example.bank.facade;

import com.example.bank.controller.dto.CardDto;
import com.example.bank.domain.entity.Account;
import com.example.bank.domain.entity.Card;
import com.example.bank.facade.mapper.CardMapper;
import com.example.bank.domain.service.AccountService;
import com.example.bank.domain.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardFacade {

    private final CardService cardService;
    private final AccountService accountService;
    private final CardMapper cardMapper;

    public CardDto findById(Long id) {
        Card card = cardService.findById(id);
        CardDto cardDto = cardMapper.convertToDto(card);
        cardDto.setStatus(cardService.getLogicCardStatus(card));
        return cardDto;
    }

    public CardDto save(CardDto cardDtoRequest) {
        Account account = accountService.getReferenceById(cardDtoRequest.getAccountId());
        Card card = cardMapper.convertToObject(cardDtoRequest);
        card.setAccount(account);
        Card savedCard = cardService.save(card);
        CardDto cardDtoResponse = cardMapper.convertToDto(savedCard);
        cardDtoResponse.setStatus(cardService.getLogicCardStatus(savedCard));
        return cardDtoResponse;
    }
}
