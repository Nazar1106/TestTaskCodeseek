package com.example.footballmanager.mapper;

import com.example.footballmanager.config.MapperConfig;
import com.example.footballmanager.dto.player.CreatePlayerRequestDto;
import com.example.footballmanager.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface PlayerMapper {

    String ID = "id";
    String TEAM_NAME_SOURCE = "team.name";
    String TEAM_NAME_TARGET = "teamName";

    Player toPlayer(CreatePlayerRequestDto createPlayerRequestDto);

    @Mapping(source = ID, target = ID)
    @Mapping(source = TEAM_NAME_SOURCE, target = TEAM_NAME_TARGET)
    CreatePlayerRequestDto toRequestPlayerDto(Player player);
}
