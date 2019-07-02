package cz.java_webapp.patient_database;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import org.mariadb.jdbc.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.lookup.*;
import org.springframework.jdbc.support.*;

public class PatientRepository {

    MariaDbDataSource configuration;
    JdbcTemplate jdbcQuery;
    RowMapper<Patient> converter;
    List<Patient> listOfPatients;

    public PatientRepository() {
        try {
            configuration = new MariaDbDataSource();
            configuration.setUserName("student");
            configuration.setPassword("password");
            configuration.setUrl("jdbc:mysql://localhost:3306/medical_record");

            jdbcQuery = new JdbcTemplate(configuration);
            converter = BeanPropertyRowMapper.newInstance(Patient.class);
        } catch (SQLException e) {
            throw new DataSourceLookupFailureException("DataSource failed", e);
        }
    }

    public synchronized List<Patient> findAll() {
        listOfPatients = jdbcQuery.query("select id, title, first_name as firstName, last_name as lastName, date_of_birth as dateOfBirth, sex, occupation, marital_status as maritalStatus, disease, family_history as familyHistory, date_of_last_medical_check as dateOfLastMedicalCheck" + " from patient", converter);
        return listOfPatients;
    }

    public synchronized Patient findOnePatient(Long id) {
        Patient onePatient = jdbcQuery.queryForObject("select  id, title, first_name as firstName, last_name as lastName, date_of_birth as dateOfBirth, sex, occupation, marital_status as maritalStatus, disease, family_history as familyHistory, date_of_last_medical_check as dateOfLastMedicalCheck" + " from patient where id=?", converter, id);
        return onePatient;
    }

    public synchronized Patient save(Patient patientToBeSaved) {
        if (patientToBeSaved.getId() == null) {
            return addNewOne(patientToBeSaved);
        }
        return updatePatient(patientToBeSaved);

    }

    public synchronized void delete(Long idToBeDeleted) {
        jdbcQuery.update("delete from patient where id=?", idToBeDeleted);
    }

    public Patient addNewOne(Patient patientToBeAdded) {
        Patient newPatient = clone(patientToBeAdded);
        GeneratedKeyHolder newKey = new GeneratedKeyHolder();
        String sql = "INSERT INTO patient (title, first_name, last_name, date_of_birth,  sex, occupation, marital_status, disease, family_history, date_of_last_medical_check) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcQuery.update((Connection con) -> {
                    PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, newPatient.getTitle());
                    statement.setString(2, newPatient.getFirstName());
                    statement.setString(3, newPatient.getLastName());
                    statement.setDate(4, (Date) newPatient.getDateOfBirth());
                    statement.setString(5, newPatient.getSex());
                    statement.setString(6, newPatient.getOccupation());
                    statement.setString(7, newPatient.getMaritalStatus());
                    statement.setString(8, newPatient.getDisease());
                    statement.setString(9, newPatient.getFamilyHistory());
                    statement.setDate(10, (Date) newPatient.getDateOfLastMedicalCheck());
                    return statement;
                },
                newKey);

        newPatient.setId(newKey.getKey().longValue());
        return newPatient;
    }




    private Patient updatePatient(Patient patientToBeUpdated) {
        patientToBeUpdated = clone(patientToBeUpdated);
        jdbcQuery.update("UPDATE patient SET title = ? , first_name = ?, last_name = ?, date_of_birth = ?,  sex = ?, marital_status = ?, occupation = ?,  disease = ?, family_history = ?, date_of_last_medical_check  = ? WHERE id = ?",
                patientToBeUpdated.getTitle(),
                patientToBeUpdated.getFirstName(),
                patientToBeUpdated.getLastName(),
                patientToBeUpdated.getDateOfBirth(),
                patientToBeUpdated.getSex(),
                patientToBeUpdated.getMaritalStatus(),
                patientToBeUpdated.getOccupation(),
                patientToBeUpdated.getDisease(),
                patientToBeUpdated.getFamilyHistory(),
                patientToBeUpdated.getDateOfLastMedicalCheck(),
                patientToBeUpdated.getId());
        return patientToBeUpdated;
    }



    private Patient clone(Patient original) {
        return new Patient(original.getId(), original.getTitle(), original.getFirstName(), original.getLastName(), original.getDateOfBirth(), original.getSex(), original.getOccupation(), original.getMaritalStatus(), original.getDisease(), original.getFamilyHistory(), original.getDateOfLastMedicalCheck());
    }

}
