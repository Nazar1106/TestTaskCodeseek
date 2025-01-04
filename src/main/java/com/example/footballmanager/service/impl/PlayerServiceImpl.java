package com.example.footballmanager.service.impl;

import com.example.footballmanager.dto.player.CreatePlayerRequestDto;
import com.example.footballmanager.dto.player.UpdatePlayerDto;
import com.example.footballmanager.entity.Player;
import com.example.footballmanager.entity.Team;
import com.example.footballmanager.exception.BusinessException;
import com.example.footballmanager.mapper.PlayerMapper;
import com.example.footballmanager.repository.PlayerRepository;
import com.example.footballmanager.repository.TeamRepository;
import com.example.footballmanager.service.PlayerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private static final String CAN_T_REMOVE_PLAYER_BY_ID_MSG = "Can't remove player by id ";
    private static final String NOT_FOUND_WITH_ID_MSG = "Player not found with id ";
    private static final String TEAM_MSG = "Team ";
    private static final String T_EXIST = " doesn't exist";

    private final PlayerRepository playerRepository;

    private final PlayerMapper playerMapper;

    private final TeamRepository teamRepository;

    @Override
    public CreatePlayerRequestDto savePlayer(CreatePlayerRequestDto requestDto) {
        String teamName = requestDto.getTeamName();
        Team team = teamRepository
                .findByName(teamName)
                .orElseThrow(() -> new BusinessException(TEAM_MSG + requestDto.getTeamName()
                        + T_EXIST));
        Player player = playerMapper.toPlayer(requestDto);
        player.setTeam(team);
        playerRepository.save(player);
        return playerMapper.toRequestPlayerDto(player);
    }

    @Transactional
    @Override
    public List<CreatePlayerRequestDto> findAll() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(playerMapper::toRequestPlayerDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CreatePlayerRequestDto getPlayerBydId(Long id) {
        Optional<Player> getPlayerById = playerRepository.findById(id);
        return getPlayerById.map(playerMapper::toRequestPlayerDto)
                .orElseThrow(() -> new BusinessException(NOT_FOUND_WITH_ID_MSG + id));
    }

    @Transactional
    @Override
    public CreatePlayerRequestDto updatePlayer(Long id, UpdatePlayerDto updatePlayerDto) {
        Player player = playerRepository.findById(id).orElseThrow(
                () -> new BusinessException(NOT_FOUND_WITH_ID_MSG + id));

        player.setName(updatePlayerDto.getName());
        player.setSurname(updatePlayerDto.getSurname());
        player.setAge(updatePlayerDto.getAge());
        player.setExperienceInMonths(updatePlayerDto.getExperienceInMonths());
        playerRepository.save(player);
        return playerMapper.toRequestPlayerDto(player);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Player> getPlayerById = playerRepository.findById(id);
        if (getPlayerById.isPresent()) {
            playerRepository.deleteById(id);
        } else {
            throw new BusinessException(CAN_T_REMOVE_PLAYER_BY_ID_MSG + id);
        }
    }
}
