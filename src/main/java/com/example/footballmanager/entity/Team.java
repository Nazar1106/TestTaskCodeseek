package com.example.footballmanager.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Team {

    public static final String COMMISSION_RATE_MSG = "commission_rate";

    public static final String TEAM_MSG = "team";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Double balance;

    @Column(name = COMMISSION_RATE_MSG)
    private Double commissionRate;

    @OneToMany(mappedBy = TEAM_MSG, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players;
}
