package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class VisitDaoImpl extends AbstractDao<VisitEntity, Long> implements VisitDao {
    @Override
    public List<VisitEntity> findVisitsByPatientId(Long id) {
        String queryContent = "SELECT visit FROM VisitEntity visit WHERE visit.patient.id = :id";
        Query query = entityManager.createQuery(queryContent, VisitEntity.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
