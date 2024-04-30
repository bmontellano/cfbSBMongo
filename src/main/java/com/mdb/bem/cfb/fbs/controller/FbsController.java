package com.mdb.bem.cfb.fbs.controller;

import com.mdb.bem.cfb.fbs.entity.CfbPlayer;
import com.mdb.bem.cfb.fbs.entity.CfbTeam;
import com.mdb.bem.cfb.fbs.request.GetPlayerRequest;
import com.mdb.bem.cfb.fbs.service.CfbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FbsController {

    @Autowired
    CfbService cfbService;

    @GetMapping("/players")
    public List<CfbPlayer> getPlayers() {
        return cfbService.getPlayers();
    }

    @GetMapping("/players/{schoolName}")
    public List<CfbPlayer> getPlayersBySchoolName(@PathVariable String schoolName) {
        return cfbService.getPlayersBySchoolName(schoolName);
    }

    @GetMapping("/player")
    public CfbPlayer getPlayer(@RequestBody GetPlayerRequest playerRequest){
        return cfbService.getPlayerByFullName(playerRequest);
    }

    @PostMapping("/createPlayer")
    public String createPlayer(@RequestBody CfbPlayer player) {
        CfbPlayer newPlayer = cfbService.savePlayer(player);
        return "Player creation successful.\nPlayer details: " + newPlayer;
    }

    @PostMapping("/createPlayer/withSchool/{schoolName}")
    public String createPlayerWithSchoolName(@RequestBody CfbPlayer player, @PathVariable String schoolName) {
        player.setCfbTeam(cfbService.getTeamBySchoolName(schoolName));
        CfbPlayer newPlayer = cfbService.savePlayer(player);
        return "Player creation successful.\nPlayer details: " + newPlayer;
    }



    @GetMapping("/team/{schoolName}")
    public CfbTeam getTeamBySchoolName(@PathVariable String schoolName){
        return cfbService.getTeamBySchoolName(schoolName);
    }

    @PostMapping("/createTeam")
    public String createTeam(@RequestBody CfbTeam team){
        CfbTeam newTeam = cfbService.saveTeam(team);
        return "Team creation successful.\nTeam details: " + newTeam;
    }

    @PutMapping("/player/updateTeam/{schoolName}")
    public String updatePlayerTeam(@RequestBody CfbPlayer player, @PathVariable String schoolName){
        player.setCfbTeam(cfbService.getTeamBySchoolName(schoolName));
        cfbService.savePlayer(player);
        return  String.format("Updated player (Name = %s ) to new team --> %s ", player.getPlayerFullName(), schoolName);
    }

    @PutMapping("/team/addRoster")
    public String updateTeamWithPlayersFromSchool(@RequestBody CfbTeam team) {
        CfbTeam actualTeam = cfbService.addPlayersToTeam(team, cfbService.getPlayersBySchoolName(team.getSchoolName()));
        return String.format("Updated team! Here it is --> %s", actualTeam);
    }


}
