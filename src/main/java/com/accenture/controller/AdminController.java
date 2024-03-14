package com.accenture.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class AdminController {

        @GetMapping
        String admin() {
            return "Partie privée de l'application";
        }
}
