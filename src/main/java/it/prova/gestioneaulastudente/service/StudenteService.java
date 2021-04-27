package it.prova.gestioneaulastudente.service;

import java.util.List;

import it.prova.gestioneaulastudente.model.Studente;

public interface StudenteService {

	public List<Studente> listAllStudenti();

	public Studente caricaSingoloStudente(Long id);

	public void aggiorna(Studente studenteInstance);

	public void inserisciNuovo(Studente studenteInstance);

	public void rimuovi(Studente studenteInstance);

	public List<Studente> findByExample(Studente example);

}
