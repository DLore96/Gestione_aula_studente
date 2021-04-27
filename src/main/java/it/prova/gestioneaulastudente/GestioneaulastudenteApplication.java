package it.prova.gestioneaulastudente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestioneaulastudenteApplication implements CommandLineRunner {

	@Autowired
	private BatteriaDiTestService batteriaTest;

	public static void main(String[] args) {
		SpringApplication.run(GestioneaulastudenteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String casoTest = BatteriaDiTestService.RIMUOVI_AULA;

		System.out.println("TEST-------");
		batteriaTest.eseguiBatteriaDiTest(casoTest);
	}

}
