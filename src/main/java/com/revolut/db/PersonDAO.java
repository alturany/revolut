package com.revolut.db;

import com.revolut.core.Person;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PersonDAO extends AbstractDAO<Person> {

    public PersonDAO(SessionFactory factory) {
        super(factory);
    }

    public List<Person> getAll() {
        CriteriaBuilder cb = currentSession().getCriteriaBuilder();
        CriteriaQuery<Person> cq = cb.createQuery(Person.class);
        Root<Person> rootEntry = cq.from(Person.class);
        CriteriaQuery<Person> all = cq.select(rootEntry);
        TypedQuery<Person> allQuery = currentSession().createQuery(all);
        return  allQuery.getResultList();
    }

    public Person findById(int id) {
        return (Person) currentSession().get(Person.class, id);
    }

    public void delete(Person person) {
        currentSession().delete(person);
    }

    public void update(Person person) {
        currentSession().saveOrUpdate(person);
    }

    public Person insert(Person person) {
        return persist(person);
    }
}