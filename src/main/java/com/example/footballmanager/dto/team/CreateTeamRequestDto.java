package com.example.footballmanager.dto.team;

import com.example.footballmanager.dto.player.PlayerNameDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CreateTeamRequestDto {

    private Long id;
    @NotNull
    private String name;
    private Double balance;
    private Double commissionRate;
    private List<PlayerNameDto> players;
}
