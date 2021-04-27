package it.prova.gestioneaulastudente;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestioneaulastudente.model.Aula;
import it.prova.gestioneaulastudente.model.Studente;
import it.prova.gestioneaulastudente.service.AulaService;
import it.prova.gestioneaulastudente.service.StudenteService;

@Service
public class BatteriaDiTestService {

	@Autowired
	private AulaService aulaService;
	@Autowired
	private StudenteService studenteService;

	// casi di test (usare valorizzando la variabile casoDaTestare nel main)
	public static final String INSERISCI_NUOVA_AULA = "INSERISCI_NUOVA_AULA";
	public static final String ELENCA_TUTTE_LE_AULE = "ELENCA_TUTTE_LE_AULE";
	public static final String CARICA_AULA_EAGER = "CARICA_AULA_EAGER";
	public static final String FIND_BY_EXAMPLE_MATERIA = "FIND_BY_EXAMPLE_MATERIA";
	public static final String RIMUOVI_AULA = "RIMUOVI_AULA";

	public static final String INSERISCI_STUDENTE = "INSERISCI_STUDENTE";
	public static final String ELENCA_STUDENTI = "ELENCA_STUDENTI";
	public static final String CERCA_STUDENTI_BY_COGNOME = "CERCA_STUDENTI_BY_COGNOME";
	public static final String RIMUOVI_STUDENTE = "RIMUOVI_STUDENTE";

	public void eseguiBatteriaDiTest(String casoDaTestare) {
		try {
			switch (casoDaTestare) {
			case INSERISCI_NUOVA_AULA:

				Aula nuovaAula = new Aula("Aula1", "matematica", 20);
				// salvo
				aulaService.inserisciNuovo(nuovaAula);
				System.out.println("Aula appena inserita: " + nuovaAula);
				break;

			case ELENCA_TUTTE_LE_AULE:

				System.out.println("Elenco delle aule:");
				for (Aula aulaItem : aulaService.listAllAule()) {
					System.out.println(aulaItem);
				}
				break;

			case CARICA_AULA_EAGER:
				Aula aulaesistente = aulaService.caricaSingolaAulaEagerStudenti(1L);
				if (aulaesistente != null) {
					for (Studente studenteItem : aulaesistente.getStudenti()) {
						System.out.println(studenteItem);
					}
				}
				break;

			case FIND_BY_EXAMPLE_MATERIA:
				Aula aulaExample = new Aula();
				aulaExample.setMateria("it");
				for (Aula aulaItem : aulaService.findByExample(aulaExample)) {
					System.out.println(aulaItem);
				}
				break;

			case INSERISCI_STUDENTE:

				Date datacurr = new Date();
				Studente nuovoStudente = new Studente("Pippo", "star", datacurr);
				nuovoStudente.setAula(aulaService.caricaSingolaAula(1L));
				// salvo
				studenteService.inserisciNuovo(nuovoStudente);
				System.out.println("Studente inserito. : " + nuovoStudente);
				break;

			case CERCA_STUDENTI_BY_COGNOME:
				Studente studentexample = new Studente();
				studentexample.setCognome("s");
				for (Studente studenteItem : studenteService.findByExample(studentexample)) {
					System.out.println(studenteItem);
				}
				break;

			case RIMUOVI_AULA:
				aulaService.rimuovi(aulaService.caricaSingolaAulaEagerStudenti(1L));
				break;

			case RIMUOVI_STUDENTE:
				studenteService.rimuovi(studenteService.caricaSingoloStudente(1L));
				break;

			case ELENCA_STUDENTI:

				System.out.println("Elenco degli studenti:");
				for (Studente studenteItem : studenteService.listAllStudenti()) {
					System.out.println(studenteItem);
				}
				break;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
