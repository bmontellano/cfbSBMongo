package com.mdb.bem.cfb.fbs.service;

import com.mdb.bem.cfb.fbs.entity.CfbPlayer;
import com.mdb.bem.cfb.fbs.entity.CfbTeam;
import com.mdb.bem.cfb.fbs.repository.CfbPlayerRepository;
import com.mdb.bem.cfb.fbs.repository.CfbTeamRepository;
import com.mdb.bem.cfb.fbs.request.GetPlayerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CfbService {

    @Autowired
    CfbPlayerRepository cfbPlayerRepository;

    @Autowired
    CfbTeamRepository cfbTeamRepository;

    public List<CfbPlayer> getPlayers(){
        return cfbPlayerRepository.findAll();
    }

    public CfbPlayer savePlayer(CfbPlayer player){
        return cfbPlayerRepository.save(player);
    }

    public CfbPlayer getPlayerByFullName(GetPlayerRequest playerRequest) {
        return cfbPlayerRepository.findPlayerByFirstNameAndMiddleNameAndLastName(
                playerRequest.getFirstName(),
                playerRequest.getMiddleName(),
                playerRequest.getLastName()
        );
    }

    public CfbTeam saveTeam(CfbTeam team) {
        return cfbTeamRepository.save(team);
    }

    public CfbTeam getTeamBySchoolName(String schoolName) {
        return cfbTeamRepository.findCfbTeamBySchoolName(schoolName);
    }

    public List<CfbPlayer> getPlayersBySchoolName(String schoolName){
        List<CfbPlayer> players = getPlayers();
        List<CfbPlayer> playersFiltered = new ArrayList<>();
        for (CfbPlayer player : players) {
            if (player.getCfbTeam() != null && player.getCfbTeam().getSchoolName().equals(schoolName) ) {
                playersFiltered.add(player);
            }
        }
        return playersFiltered;
    }

    public CfbTeam addPlayersToTeam(CfbTeam cfbTeam, List<CfbPlayer> cfbPlayers) {
        List<CfbPlayer> currentPlayers = cfbTeam.getCfbPlayers();
        boolean trigger = true;
        if (Objects.isNull(currentPlayers) ) {
            currentPlayers = new ArrayList<>();
        }
        //Add players if they don't exist on roster
        for(CfbPlayer incomingPlayer : cfbPlayers) {
            for (CfbPlayer currentPlayer : currentPlayers) {
                if (incomingPlayer.getPlayerFullName().equals(currentPlayer.getPlayerFullName())) {
                    trigger = false;
                }
            }
            if (trigger) {
                currentPlayers.add(incomingPlayer);
            }
        }
        cfbTeam.setCfbPlayers(currentPlayers);
        return cfbTeamRepository.save(cfbTeam);
    }
}

