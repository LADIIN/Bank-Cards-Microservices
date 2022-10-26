package com.example.history.domain.mapper;

import com.example.history.domain.entity.CardHistory;
import org.example.message.CardEvent;
import org.springframework.stereotype.Component;

@Component
public class CardHistoryMapper {

    public CardHistory mapEventToObject(CardEvent cardEvent) {
        CardHistory cardHistory = new CardHistory();
        cardHistory.setCardId(cardEvent.getCardId());
        cardHistory.setAccountId(cardEvent.getAccountId());
        cardHistory.setCreationTime(cardEvent.getCreationTime());
        return cardHistory;
    }
}
