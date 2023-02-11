package Find;

import Util.HibernateFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public abstract class MapperAbstract<T> {

    public void save(T entity) {
        EntityManager entityManager = HibernateFactory.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    public void update(T entity) {
        EntityManager entityManager = HibernateFactory.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    public void delete(T entity) {
        EntityManager entityManager = HibernateFactory.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    public List<T> findAll() {
        EntityManager entityManager = HibernateFactory.getEntityManagerFactory().createEntityManager();
        TypedQuery <T> typedQuery = entityManager.createNamedQuery(getTableName()+".all", getType());
        List<T> list = typedQuery.getResultList();
        entityManager.close();
        return list;
    }

    protected List<T> findByParameter(String filter, Object field) {
        EntityManager entityManager = HibernateFactory.getEntityManagerFactory().createEntityManager();
        TypedQuery <T> typedQuery = entityManager.createNamedQuery(getTableName()+filter, getType());
        typedQuery.setParameter(1, field);
        List<T> list = typedQuery.getResultList();
        entityManager.close();
        return list;
    }

    public List<T> findById(Object id) {
        return findByParameter(".byId", id);
    }

    public List<T> findBySurname(Object surname) {return findByParameter(".bySurname", "%" + surname + "%");}

    public List<T> findByName(Object name) {
        return findByParameter(".byName", "%" + name + "%");
    }

    public List<T> findByPatronymic(Object patronymic) {return findByParameter(".byPatronymic", "%" + patronymic + "%");}


    protected abstract Class<T> getType();

    protected abstract String getTableName();
}
