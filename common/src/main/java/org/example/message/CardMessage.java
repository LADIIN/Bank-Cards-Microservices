package org.example.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CardMessage {

    Long id;
    String number;
    String status;
    String name;
    String surname;
    Long accountId;
}
