package it.prova.gestioneaulastudente.dao;

import java.util.List;

import it.prova.gestioneaulastudente.model.Aula;
import it.prova.gestioneaulastudente.model.Studente;

public interface StudenteDAO extends IBaseDAO<Studente> {

	public List<Studente> findByAula(Aula aulaInstance);

}
