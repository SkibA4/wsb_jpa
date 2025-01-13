package com.jpacourse.persistence.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(nullable = false)
	private LocalDateTime time;

	// Relacja jednostronna z encją Doctor od strony rodzica (wizyta przechowuje klucz obcy)
	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private DoctorEntity doctor;

	// Relacja dwustronna z encją Patient
	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private PatientEntity patient;

	// Relacja dwustronna z encją MedicalTreatment
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "visit")
	private List<MedicalTreatmentEntity> medicalTreatment;

	// Gettery i Settery
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

	public List<MedicalTreatmentEntity> getMedicalTreatment() {
		return medicalTreatment;
	}

	public void setMedicalTreatment(List<MedicalTreatmentEntity> medicalTreatment) {
		this.medicalTreatment = medicalTreatment;
	}
}
