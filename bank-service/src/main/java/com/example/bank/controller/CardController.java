package com.example.bank.controller;

import com.example.bank.controller.dto.CardDto;
import com.example.bank.controller.facade.CardFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardFacade cardFacade;

    @GetMapping("{id}")
    public CardDto getById(@PathVariable Long id) {
        return cardFacade.findById(id);
    }

    @PostMapping
    public CardDto save(@RequestBody CardDto cardDto) {
        return cardFacade.save(cardDto);
    }
}
