package com.example.footballmanager.service.impl;

import com.example.footballmanager.dto.team.CreateTeamDto;
import com.example.footballmanager.dto.team.CreateTeamRequestDto;
import com.example.footballmanager.dto.team.UpdateTeamDto;
import com.example.footballmanager.entity.Team;
import com.example.footballmanager.exception.ApiException;
import com.example.footballmanager.exception.BusinessException;
import com.example.footballmanager.mapper.TeamMapper;
import com.example.footballmanager.repository.TeamRepository;
import com.example.footballmanager.service.TeamService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private static final String CAN_T_DELETE_TEAM_BY_ID_MSG = "Can't delete team by Id ";
    private static final String TEAM_NOT_FOUND_WITH_ID_MSG = "Team not found with id ";
    private static final String TEAM_WITH_NAME_MSG = "Team with name ";
    private static final String ALREADY_EXISTS_MSG = " already exists.";

    private final TeamRepository teamRepository;

    private final TeamMapper teamMapper;

    @Override
    public CreateTeamDto saveTeam(CreateTeamDto requestDto) {
        String teamName = requestDto.getName();
        if (teamRepository.existsByName(teamName)) {
            throw new ApiException(TEAM_WITH_NAME_MSG + teamName + ALREADY_EXISTS_MSG);
        }
        Team team = teamMapper.teamDtoToTeam(requestDto);
        teamRepository.save(team);
        return teamMapper.teamToTeamDto2(team);
    }

    @Transactional
    @Override
    public List<CreateTeamRequestDto> findAll() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream()
                .map(teamMapper::teamToTeamDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CreateTeamRequestDto getTeamBydId(Long id) {
        Optional<Team> getTeamById = teamRepository.findById(id);
        return getTeamById.map(teamMapper::teamToTeamDto)
                .orElseThrow(() -> new BusinessException(TEAM_NOT_FOUND_WITH_ID_MSG + id));
    }

    @Transactional
    @Override
    public CreateTeamRequestDto updateTeam(Long id, UpdateTeamDto updateTeamDto) {
        Team team = teamRepository.findById(id).orElseThrow(
                () -> new BusinessException(TEAM_NOT_FOUND_WITH_ID_MSG + id));
        team.setName(updateTeamDto.getName());
        team.setBalance(updateTeamDto.getBalance());
        team.setCommissionRate(updateTeamDto.getCommissionRate());
        teamRepository.save(team);
        return teamMapper.teamToTeamDto(team);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Team> getById = teamRepository.findById(id);
        if (getById.isPresent()) {
            teamRepository.deleteById(id);
        } else {
            throw new BusinessException(CAN_T_DELETE_TEAM_BY_ID_MSG + id);
        }
    }
}
