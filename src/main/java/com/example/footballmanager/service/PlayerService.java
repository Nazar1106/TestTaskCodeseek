package com.example.footballmanager.service;

import com.example.footballmanager.dto.player.CreatePlayerRequestDto;
import com.example.footballmanager.dto.player.UpdatePlayerDto;
import java.util.List;

public interface PlayerService {

    CreatePlayerRequestDto savePlayer(CreatePlayerRequestDto requestDto);

    List<CreatePlayerRequestDto> findAll();

    CreatePlayerRequestDto getPlayerBydId(Long id);

    CreatePlayerRequestDto updatePlayer(Long id, UpdatePlayerDto updatePlayerDto);

    void deleteById(Long id);
}
