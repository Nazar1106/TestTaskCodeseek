package com.example.footballmanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Player {

    public static final String FK_PLAYER_TEAM = "fk_player_team";

    public static final String TEAM_ID = "team_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private Integer age;

    private Integer experienceInMonths;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = TEAM_ID,
            foreignKey = @ForeignKey(name = FK_PLAYER_TEAM))

    private Team team;
}
