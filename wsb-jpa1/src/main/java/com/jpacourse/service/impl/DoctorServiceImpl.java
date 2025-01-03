package com.jpacourse.service.impl;

import com.jpacourse.dto.DoctorTO;
import com.jpacourse.mapper.DoctorMapper;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    @Override
    public DoctorTO findById(Long id) {
        final DoctorEntity entity = doctorDao.findOne(id);
        return DoctorMapper.mapToTO(entity);
    }

}
