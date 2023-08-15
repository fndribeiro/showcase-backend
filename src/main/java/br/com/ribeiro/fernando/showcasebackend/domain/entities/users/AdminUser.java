package br.com.ribeiro.fernando.showcasebackend.domain.entities.users;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("admin-users")
public class AdminUser {

	@Id
	private ObjectId id;
	private String email;
	
	public ObjectId getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	
}
