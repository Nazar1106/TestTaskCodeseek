package com.example.footballmanager.mapper;

import com.example.footballmanager.config.MapperConfig;
import com.example.footballmanager.dto.player.PlayerNameDto;
import com.example.footballmanager.dto.team.CreateTeamDto;
import com.example.footballmanager.dto.team.CreateTeamRequestDto;
import com.example.footballmanager.entity.Player;
import com.example.footballmanager.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(config = MapperConfig.class)
public interface TeamMapper {

    String MAP_PLAYERS_TO_NAME_DTOS_MSG = "mapPlayersToNameDtos";
    String PLAYERS_MSG = "players";

    Team teamDtoToTeam(CreateTeamDto teamDto);

    CreateTeamDto teamToTeamDto2(Team team);

    @Mapping(target = PLAYERS_MSG, source = PLAYERS_MSG, qualifiedByName = MAP_PLAYERS_TO_NAME_DTOS_MSG)
    CreateTeamRequestDto teamToTeamDto(Team team);

    @Named(MAP_PLAYERS_TO_NAME_DTOS_MSG)
    default List<PlayerNameDto> mapPlayersToNameDtos(List<Player> players) {
        if (players == null) {
            return null;
        }
        return players.stream()
                .map(player -> {
                    PlayerNameDto dto = new PlayerNameDto();
                    dto.setName(player.getName());
                    dto.setSurname(player.getSurname());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
