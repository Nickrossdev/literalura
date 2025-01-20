package com.challenge.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.challenge.literalura.principal.App;
import com.challenge.literalura.repository.AutorRepository;
import com.challenge.literalura.repository.LibroRepository;


@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner{

	@Autowired
	AutorRepository autorRepository;
	@Autowired
	LibroRepository libroRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		App app = new App();
		app.iniciar(autorRepository,libroRepository);
	}
}
