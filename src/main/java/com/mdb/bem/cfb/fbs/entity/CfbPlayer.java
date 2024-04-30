package com.mdb.bem.cfb.fbs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("CfbPlayer")
public class CfbPlayer {

    @Id
    private String _id;

    @JsonIgnore
    @DBRef(lazy = true)
    private CfbTeam cfbTeam;

    private int age;
    private int heightCm;
    private double weightLb;
    private String position;
    private String firstName;
    private String middleName;
    private String lastName;

    public String getPlayerFullName() {
        if(!middleName.isBlank()) {
            return firstName + " " + middleName + " " + lastName;
        } else {
           return firstName + " " + lastName;
        }
    }

    public HashMap<String,String> getCfbTeamInfo(){
        HashMap<String,String> cfbTeamInfo = new HashMap<>();
        if(!Objects.isNull(cfbTeam)) {
            cfbTeamInfo.put("schoolName", cfbTeam.getSchoolName());
            cfbTeamInfo.put("schoolMascot", cfbTeam.getSchoolMascot());
           return cfbTeamInfo;
        }
        return null;
    }


}
