package com.smartmeetings.server.data;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class GroupsRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Integer> getAvailableGroupNumbers() {
        return (List<Integer>) entityManager.createQuery("SELECT groupNumber FROM groups").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Group> getAvailableGroups() {
        return (List<Group>) entityManager.createQuery("SELECT groupNumber FROM groups").getResultList()
                .stream().map(number -> {
                    Group g = new Group();
                    g.setGroupNumber((Integer) number);

                    return g;
                })
                .collect(Collectors.toList());
    }

    public void addGroup(int groupNumber) {
        entityManager.createNativeQuery("INSERT INTO groups values (?) ON CONFLICT DO NOTHING")
                .setParameter(1, groupNumber);

    }
}