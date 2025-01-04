package com.example.footballmanager.dto.team;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTeamDto {

    @NotNull
    private String name;

    @NotNull
    private Double balance;

    @NotNull
    private Double commissionRate;
}
