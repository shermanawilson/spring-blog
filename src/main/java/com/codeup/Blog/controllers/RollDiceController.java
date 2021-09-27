package com.codeup.Blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String rollDice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDiceGuess(@PathVariable int n, Model model) {

        Random rand = new Random();
        int random = rand.nextInt((6 - 1) + 1) + 1;

        model.addAttribute("diceRoll", random);
        model.addAttribute("userGuess", n);
        model.addAttribute("isCorrect", random == n);

        return "roll-dice";
    }
}
