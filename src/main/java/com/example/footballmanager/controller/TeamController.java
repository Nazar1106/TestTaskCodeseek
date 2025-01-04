package com.example.footballmanager.controller;

import com.example.footballmanager.dto.team.CreateTeamDto;
import com.example.footballmanager.dto.team.CreateTeamRequestDto;
import com.example.footballmanager.dto.team.UpdateTeamDto;
import com.example.footballmanager.service.TeamService;
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
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public List<CreateTeamRequestDto> getAll() {
        return teamService.findAll();
    }

    @GetMapping("/{id}")
    public  CreateTeamRequestDto getTeamById(@PathVariable Long id) {
        return teamService.getTeamBydId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateTeamDto createTeam(@Valid @RequestBody CreateTeamDto dto) {
        return teamService.saveTeam(dto);
    }

    @PutMapping("/{id}")
    public CreateTeamRequestDto updateTeam(@PathVariable Long id, @Valid @RequestBody UpdateTeamDto updateTeamDto) {
        return teamService.updateTeam(id, updateTeamDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTeamById(@PathVariable Long id) {
        teamService.deleteById(id);
    }
}
