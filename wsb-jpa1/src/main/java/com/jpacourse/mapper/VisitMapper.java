package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.stream.Collectors;

public final class VisitMapper {

    public static VisitTO mapToTO(final VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }

        VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());

        if (visitEntity.getDoctor() != null) {
            visitTO.setDoctorFirstName(visitEntity.getDoctor().getFirstName());
            visitTO.setDoctorLastName(visitEntity.getDoctor().getLastName());
        }

        if (visitEntity.getMedicalTreatment() != null) {
            visitTO.setTreatmentTypes(
                    visitEntity.getMedicalTreatment()
                            .stream()
                            .map(MedicalTreatmentEntity::getType)
                            .collect(Collectors.toList())
            );
        }

        return visitTO;
    }

    public static VisitEntity mapToEntity(final VisitTO visitTO) {
        if (visitTO == null) {
            return null;
        }

        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visitTO.getId());
        visitEntity.setDescription(visitTO.getDescription());
        visitEntity.setTime(visitTO.getTime());


        return visitEntity;
    }
}
