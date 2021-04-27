package it.prova.gestioneaulastudente.dao;

import it.prova.gestioneaulastudente.model.Aula;

public interface AulaDAO extends IBaseDAO<Aula> {

	public Aula findEagerFetch(long idAula);

	public int getActuallyCapient(Aula aulaInstance);

	public int welcomeInAula(Aula aulaInstance);
}
