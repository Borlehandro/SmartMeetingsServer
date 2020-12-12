package com.smartmeetings.server.data;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class GroupsRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Integer> getAvailableGroups() {
        return (List<Integer>) entityManager.createQuery("SELECT groupNumber FROM groups").getResultList();
    }

    public void addGroup(int groupNumber) {
        entityManager.createNativeQuery("INSERT INTO groups values (?) ON CONFLICT DO NOTHING")
                .setParameter(1, groupNumber);

    }
}