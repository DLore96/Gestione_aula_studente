package it.prova.gestioneaulastudente.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import it.prova.gestioneaulastudente.model.Aula;

@Component
public class AulaDAOImpl implements AulaDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Aula findEagerFetch(long idAula) {

		Query query = entityManager.createQuery("SELECT a FROM Aula a LEFT JOIN FETCH a.studenti s WHERE a.id = :id");
		query.setParameter("id", idAula);
		return (Aula) query.getSingleResult();
	}

	@Override
	public List<Aula> list() {
		return entityManager.createQuery("from Aula", Aula.class).getResultList();
	}

	@Override
	public Aula get(Long id) {
		return entityManager.find(Aula.class, id);
	}

	@Override
	public void update(Aula instanceAula) {

		instanceAula = entityManager.merge(instanceAula);
	}

	@Override
	public void insert(Aula instanceAula) {

		entityManager.persist(instanceAula);
	}

	@Override
	public void delete(Aula instanceAula) {

		entityManager.remove(entityManager.merge(instanceAula));
	}

	@Override
	public List<Aula> findByExample(Aula aulaInstance) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select a from Aula a  where a.id = a.id ");

		if (StringUtils.isNotEmpty(aulaInstance.getCodice())) {
			whereClauses.add(" a.codice  like :codice ");
			paramaterMap.put("codice", "%" + aulaInstance.getCodice() + "%");
		}
		if (StringUtils.isNotEmpty(aulaInstance.getMateria())) {
			whereClauses.add(" a.materia like :materia ");
			paramaterMap.put("materia", "%" + aulaInstance.getMateria() + "%");
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Aula> typedQuery = entityManager.createQuery(queryBuilder.toString(), Aula.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	public int getActuallyCapient(Aula aulaInstance) {
		TypedQuery<Integer> query = entityManager.createQuery(" select a.capienza from Aula a where a.id=?1",
				Integer.class);
		int capienza = query.setParameter(1, aulaInstance.getId()).getSingleResult();
		return capienza;
	}

	@Override
	public int welcomeInAula(Aula aulaInstance) {

		TypedQuery<String> query = entityManager.createQuery("update Aula set capienza= :capienza where id= :aula",
				String.class);
		query.setParameter("aula", aulaInstance.getId());
		query.setParameter("capienza", aulaInstance.getCapienza() - 1);
		return query.executeUpdate();
	}

}
