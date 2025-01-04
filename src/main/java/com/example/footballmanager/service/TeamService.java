package com.example.footballmanager.service;

import com.example.footballmanager.dto.team.CreateTeamDto;
import com.example.footballmanager.dto.team.CreateTeamRequestDto;
import com.example.footballmanager.dto.team.UpdateTeamDto;
import java.util.List;

public interface TeamService {

    CreateTeamDto saveTeam(CreateTeamDto requestDto);

    List<CreateTeamRequestDto> findAll();

    CreateTeamRequestDto getTeamBydId(Long id);

    CreateTeamRequestDto updateTeam(Long id, UpdateTeamDto updateTeamDto);

    void deleteById(Long id);
}
