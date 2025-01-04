package com.example.footballmanager.dto.team;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateTeamDto {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Double balance;
    @NotNull
    private Double commissionRate;
}
