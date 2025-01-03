package com.jpacourse.service.impl;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.VisitMapper;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitDao visitDao;

    @Override
    public VisitTO findById(Long id) {
        var entity = visitDao.findOne(id);
        return VisitMapper.mapToTO(entity);
    }
}
