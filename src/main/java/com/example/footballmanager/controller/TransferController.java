package com.example.footballmanager.controller;

import com.example.footballmanager.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransferController {

    private static final String SUCCESSFULLY_MSG = "Player transferred successfully";

    private final TransferService transferService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @PostMapping("/{playerId}/transfer")
    public ResponseEntity<String> transferPlayer(@PathVariable Long playerId, @RequestParam Long newTeamId) {
        try {
            transferService.transferPlayer(playerId, newTeamId);
            return ResponseEntity.ok(SUCCESSFULLY_MSG);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
