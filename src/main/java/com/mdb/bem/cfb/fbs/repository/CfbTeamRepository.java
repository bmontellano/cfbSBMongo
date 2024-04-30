package com.mdb.bem.cfb.fbs.repository;

import com.mdb.bem.cfb.fbs.entity.CfbPlayer;
import com.mdb.bem.cfb.fbs.entity.CfbTeam;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CfbTeamRepository extends MongoRepository<CfbTeam, String> {
    CfbTeam findCfbTeamBySchoolName(String schoolName);
}
