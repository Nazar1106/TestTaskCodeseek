package com.example.footballmanager.controller;

import com.example.footballmanager.dto.player.CreatePlayerRequestDto;
import com.example.footballmanager.dto.player.UpdatePlayerDto;
import com.example.footballmanager.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public List<CreatePlayerRequestDto> getAll() {
        return playerService.findAll();
    }

    @GetMapping("/{id}")
    public CreatePlayerRequestDto getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerBydId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreatePlayerRequestDto createPlayer(@RequestBody CreatePlayerRequestDto requestPlayerDto) {
        return playerService.savePlayer(requestPlayerDto);
    }

    @PutMapping("/{id}")
    public CreatePlayerRequestDto updatePlayer(@PathVariable Long id,
                                  @Valid @RequestBody UpdatePlayerDto updatePlayerDto) {
        return playerService.updatePlayer(id, updatePlayerDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePlayerById(@PathVariable Long id) {
        playerService.deleteById(id);
    }
}
