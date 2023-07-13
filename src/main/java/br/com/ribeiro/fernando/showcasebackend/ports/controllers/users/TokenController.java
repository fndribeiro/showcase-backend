package br.com.ribeiro.fernando.showcasebackend.ports.controllers.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("token")
public class TokenController {

	@GetMapping()
	public void token() {}
	
}
