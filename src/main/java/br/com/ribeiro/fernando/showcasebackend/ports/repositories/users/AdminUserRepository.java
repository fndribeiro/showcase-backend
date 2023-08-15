package br.com.ribeiro.fernando.showcasebackend.ports.repositories.users;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.ribeiro.fernando.showcasebackend.domain.entities.users.AdminUser;

@Repository
public interface AdminUserRepository extends MongoRepository<AdminUser, ObjectId> {
	
	Optional<AdminUser> findByEmail(String email);

}
