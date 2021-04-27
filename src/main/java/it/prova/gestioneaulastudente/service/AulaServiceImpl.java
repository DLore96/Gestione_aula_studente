package it.prova.gestioneaulastudente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneaulastudente.dao.AulaDAO;
import it.prova.gestioneaulastudente.dao.StudenteDAO;
import it.prova.gestioneaulastudente.model.Aula;

@Service
public class AulaServiceImpl implements AulaService {

	@Autowired
	private AulaDAO aulaDAO;
	@Autowired
	private StudenteDAO studenteDAO;

	@Override
	@Transactional
	public List<Aula> listAllAule() {
		return aulaDAO.list();
	}

	@Override
	@Transactional
	public Aula caricaSingolaAula(Long id) {
		return aulaDAO.get(id);
	}

	@Override
	@Transactional
	public Aula caricaSingolaAulaEagerStudenti(Long idAula) {
		return aulaDAO.findEagerFetch(idAula);
	}

	@Override
	@Transactional
	public void aggiorna(Aula aulaInstance) {

		aulaDAO.update(aulaInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Aula aulaInstance) {

		aulaDAO.insert(aulaInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Aula aulaInstance) {

		if (!studenteDAO.findByAula(aulaInstance).isEmpty()) {
			throw new RuntimeException("Eccezione di prova cancellazione");
		}
		aulaDAO.delete(aulaInstance);
	}

	@Override
	@Transactional
	public List<Aula> findByExample(Aula example) {
		return aulaDAO.findByExample(example);

	}

}
