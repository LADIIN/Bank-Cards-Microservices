package org.example.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardEvent {

    Long cardId;
    Long accountId;
    Timestamp creationTime;
    EventStatus status;
}
