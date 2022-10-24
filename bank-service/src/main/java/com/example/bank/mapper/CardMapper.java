package com.example.bank.mapper;

import com.example.bank.controller.dto.CardDto;
import com.example.bank.entity.Account;
import com.example.bank.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardDto convertToDto(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setId(card.getId());
        cardDto.setNumber(card.getNumber());
        cardDto.setName(card.getName());
        cardDto.setSurname(card.getSurname());
        cardDto.setAccountId(card.getAccount().getId());
        return cardDto;
    }

    public Card convertToObject(CardDto cardDto) {
        Card card = new Card();
        card.setNumber(cardDto.getNumber());
        card.setName(cardDto.getName());
        card.setStatus(cardDto.getStatus());
        Account account = new Account();
        account.setId(cardDto.getId());
        card.setAccount(account);
        return card;
    }
}
