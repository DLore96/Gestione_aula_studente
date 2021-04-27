package it.prova.gestioneaulastudente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneaulastudente.dao.AulaDAO;
import it.prova.gestioneaulastudente.dao.StudenteDAO;
import it.prova.gestioneaulastudente.model.Studente;

@Service
public class StudenteServiceImpl implements StudenteService {

	@Autowired
	private AulaDAO aulaDAO;
	@Autowired
	private StudenteDAO studenteDAO;

	@Override
	@Transactional
	public List<Studente> listAllStudenti() {
		return studenteDAO.list();
	}

	@Override
	@Transactional
	public Studente caricaSingoloStudente(Long id) {
		return studenteDAO.get(id);
	}

	@Override
	@Transactional
	public void aggiorna(Studente studenteInstance) {

		studenteDAO.update(studenteInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Studente studenteInstance) {

		if (aulaDAO.getActuallyCapient(studenteInstance.getAula()) > 1) {
			studenteDAO.insert(studenteInstance);
			aulaDAO.welcomeInAula(studenteInstance.getAula());
		}

	}

	@Override
	@Transactional
	public void rimuovi(Studente studenteInstance) {

		studenteDAO.delete(studenteInstance);
	}

	@Override
	@Transactional
	public List<Studente> findByExample(Studente example) {
		return studenteDAO.findByExample(example);
	}

}
