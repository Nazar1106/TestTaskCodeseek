package com.example.footballmanager.service.impl;

import com.example.footballmanager.entity.Player;
import com.example.footballmanager.entity.Team;
import com.example.footballmanager.exception.BusinessException;
import com.example.footballmanager.exception.InsufficientBalanceException;
import com.example.footballmanager.repository.PlayerRepository;
import com.example.footballmanager.repository.TeamRepository;
import com.example.footballmanager.service.TransferService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private static final String CAN_T_GET_TEAM_WITH_ID_MSG = "Can't get team with id ";
    private static final String CAN_T_GET_PLAYER_WITH_ID_MSG = "Can't get player with id ";
    private static final String TEAM_MSG = "Team ";
    private static final String BALANCE_FOR_THE_TRANSFER_REQUIRED_MSG = " does not have enough balance for the transfer. Required: ";
    private static final String AVAILABLE_MSG = ", Available: ";
    private static final int ONE_HUNDRED = 100;
    private static final double PERCENTAGE = 100000.0;

    private final PlayerRepository playerRepository;

    private final TeamRepository teamRepository;

    @Transactional
    @Override
    public void transferPlayer(Long playerId, Long newTeamId) {

        Player player = getPlayerById(playerId);
        Team currentTeam = getPlayerTeam(player);
        Team newTeam = getTeamById(newTeamId);

        double baseCost = calculateBaseCost(player);
        double commission = calculateCommission(baseCost, currentTeam.getCommissionRate());
        double totalCost = baseCost + commission;

        validateTeamBalance(newTeam, totalCost);

        newTeam.setBalance(newTeam.getBalance() - totalCost);
        currentTeam.setBalance(currentTeam.getBalance() + totalCost);

        player.setTeam(newTeam);

        playerRepository.save(player);
    }

    private double calculateBaseCost(Player player) {
        return (player.getExperienceInMonths() * PERCENTAGE) / player.getAge();
    }

    private double calculateCommission(double baseCost, double commissionRate) {
        return baseCost * (commissionRate / ONE_HUNDRED);
    }

    private void validateTeamBalance(Team team, double totalCost) {
        if (team.getBalance() < totalCost) {
            throw new InsufficientBalanceException(TEAM_MSG
                    + team.getName()
                    + BALANCE_FOR_THE_TRANSFER_REQUIRED_MSG
                    + totalCost
                    + AVAILABLE_MSG
                    + team.getBalance());
        }
    }

    private Player getPlayerById(Long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new BusinessException(CAN_T_GET_PLAYER_WITH_ID_MSG + playerId));
    }

    private Team getPlayerTeam(Player player) {
        return player.getTeam();
    }

    private Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new BusinessException(CAN_T_GET_TEAM_WITH_ID_MSG + teamId));
    }
}
