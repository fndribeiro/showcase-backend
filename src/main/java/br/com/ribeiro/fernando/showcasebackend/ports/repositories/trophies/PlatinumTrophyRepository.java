package br.com.ribeiro.fernando.showcasebackend.ports.repositories.trophies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.ribeiro.fernando.showcasebackend.domain.entities.trophies.PlatinumTrophy;

@Repository
public interface PlatinumTrophyRepository extends MongoRepository<PlatinumTrophy, ObjectId> {

}
