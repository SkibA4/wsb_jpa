package com.jpacourse.service.impl;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.VisitMapper;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitDao visitDao;

    @Override
    public VisitTO findById(Long id) {
        var entity = visitDao.findOne(id);
        return VisitMapper.mapToTO(entity);
    }

    @Override
    public List<VisitTO> findVisitsByPatientId(Long id) {
        return visitDao.findVisitsByPatientId(id)
                .stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList());
    }
}
