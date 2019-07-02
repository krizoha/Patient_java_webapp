package cz.java_webapp.patient_database;

import java.sql.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class MainController {

    PatientRepository patientDatabase = new PatientRepository();

    @RequestMapping("/")
    public ModelAndView showList() {
        return new ModelAndView("redirect:/databaseOfPatients.html");
    }

    @RequestMapping("/databaseOfPatients.html")
    public ModelAndView showAllPatients() throws SQLException {
        ModelAndView dataHolder = new ModelAndView("databaseOfPatients");
        dataHolder.addObject("allPatients",patientDatabase.findAll() );
        return dataHolder;
    }

    @RequestMapping(value = "/{idPatient:[0-9]+}.html", method = RequestMethod.GET)
    public ModelAndView showPatientToEdit(@PathVariable Long idPatient){
        ModelAndView dataHolder = new ModelAndView("medicalRecord");
        Patient foundPatient = patientDatabase.findOnePatient(idPatient);
        MedicalRecordForm formData = new MedicalRecordForm();
        formData.setTitle(foundPatient.getTitle());
        formData.setFirstName(foundPatient.getFirstName());
        formData.setLastName(foundPatient.getLastName());
        formData.setDateOfBirth(foundPatient.getDateOfBirth());
        formData.setSex(foundPatient.getSex());
        formData.setMaritalStatus(foundPatient.getMaritalStatus());
        formData.setOccupation(foundPatient.getOccupation());
        formData.setDisease(foundPatient.getDisease());
        formData.setFamilyHistory(foundPatient.getFamilyHistory());
        formData.setDateOfLastMedicalCheck(foundPatient.getDateOfLastMedicalCheck());

        dataHolder.addObject("one", formData);
        return dataHolder;
    }

    @RequestMapping(value = "/{idPatient:[0-9]+}.html", method = RequestMethod.POST)
    public ModelAndView showPatientToEdit2(@PathVariable Long idPatient, MedicalRecordForm entryData, BindingResult userloginResult){
        Patient foundPatient = patientDatabase.findOnePatient(idPatient);
        if (foundPatient != null){
            foundPatient.setTitle(entryData.getTitle());
            foundPatient.setFirstName(entryData.getFirstName());
            foundPatient.setLastName(entryData.getLastName());
            foundPatient.setDateOfBirth(entryData.getDateOfBirth());
            foundPatient.setSex(entryData.getSex());
            foundPatient.setMaritalStatus(entryData.getMaritalStatus());
            foundPatient.setOccupation(entryData.getOccupation());
            foundPatient.setDisease(entryData.getDisease());
            foundPatient.setFamilyHistory(entryData.getFamilyHistory());
            foundPatient.setDateOfLastMedicalCheck(entryData.getDateOfLastMedicalCheck());
            patientDatabase.save(foundPatient);
        }

        return new ModelAndView("redirect:/databaseOfPatients.html");
    }

    @RequestMapping(value = "{idPatient:[0-9]+}/delete", method = RequestMethod.GET)
    public ModelAndView showDelete (@PathVariable Long idPatient)     {
        patientDatabase.delete(idPatient);
        return new ModelAndView("redirect:/databaseOfPatients.html");
    }

    @RequestMapping(value = "/new.html", method = RequestMethod.GET)
    public ModelAndView showNewDetail() {
        ModelAndView dataHolder = new ModelAndView("medicalRecord");
        MedicalRecordForm entryData = new MedicalRecordForm();
        dataHolder.addObject("one", entryData);
        return dataHolder;
    }

    @RequestMapping(value = "/new.html", method = RequestMethod.POST)
    public ModelAndView procedeNewDetail(MedicalRecordForm entryData, BindingResult userloginResult) {

        Patient newPatient = new Patient();
        newPatient.setTitle(entryData.getTitle());
        newPatient.setFirstName(entryData.getFirstName());
        newPatient.setLastName(entryData.getLastName());
        newPatient.setDateOfBirth(entryData.getDateOfBirth());
        newPatient.setSex(entryData.getSex());
        newPatient.setOccupation(entryData.getOccupation());
        newPatient.setMaritalStatus(entryData.getMaritalStatus());
        newPatient.setDisease(entryData.getDisease());
        newPatient.setFamilyHistory(entryData.getFamilyHistory());
        newPatient.setDateOfLastMedicalCheck(entryData.getDateOfLastMedicalCheck());
        patientDatabase.save(newPatient);
        return new ModelAndView("redirect:/databaseOfPatients.html");
    }
}
