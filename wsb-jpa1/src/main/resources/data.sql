INSERT INTO address (id, city, address_line1, address_line2, postal_code)
VALUES
(1, 'Warsaw', 'Main Street 10', 'Apt 101', '00-001'),
(2, 'Krakow', 'Central Square 5', 'Building B', '30-002'),
(3, 'Gdansk', 'Beach Road 20', 'Floor 3', '80-003');

INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES
(1, 'John', 'Doe', '123456789', 'john.doe@example.com', 'DOC001', 'SURGEON', 1),
(2, 'Mary', 'Smith', '987654321', 'mary.smith@example.com', 'DOC002', 'OCULIST', 2);

INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, gender, height, version)
VALUES
(1, 'Jane', 'Doe', '456789123', 'jane.doe@example.com', 'PAT001', '1985-06-15', 1, 'FEMALE', 169, 1),
(2, 'Michael', 'Johnson', '654321987', 'michael.johnson@example.com', 'PAT002', '1990-08-20', 2, 'MALE', 186, 1);

INSERT INTO visit (id, description, time, doctor_id, patient_id)
VALUES
(1, 'Checkup appointment', '2024-12-05 09:30:00', 1, 1),
(2, 'Follow-up consultation', '2024-12-06 10:00:00', 2, 2),
(3, 'Routine examination', '2024-12-07 11:00:00', 1, 1),
(4, 'Annual checkup', '2024-12-08 12:30:00', 1, 1),
(5, 'Dental cleaning', '2024-12-09 14:00:00', 2, 1);

INSERT INTO medical_treatment (id, description, type, visit_id)
VALUES
(1, 'Blood pressure check', 'RTG', 1),
(2, 'MRI scan', 'USG', 2);