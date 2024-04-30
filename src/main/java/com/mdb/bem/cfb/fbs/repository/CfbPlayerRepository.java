package com.mdb.bem.cfb.fbs.repository;

import com.mdb.bem.cfb.fbs.entity.CfbPlayer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CfbPlayerRepository extends MongoRepository<CfbPlayer, String> {
    CfbPlayer findPlayerByFirstNameAndMiddleNameAndLastName(String firstName, String middleName, String lastName);
}
