package br.com.ribeiro.fernando.showcasebackend.ports.controllers.users;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ribeiro.fernando.showcasebackend.domain.entities.users.LoggedUser;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("user")
@Tag(name = "User", description = "Retrieve user profile.")
public class UserController {
	
	@GetMapping()
	public LoggedUser profile() {
		return (LoggedUser) SecurityContextHolder
			.getContext()
			.getAuthentication()
			.getPrincipal();
	}

}
