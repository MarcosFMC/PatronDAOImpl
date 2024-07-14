package service;

import dao.IDao;
import model.Dentist;
import model.Patient;

import java.util.List;

public class PatientService {

    private IDao<Patient> patientIDao;
    public PatientService(IDao<Patient> patientIDao) {
        this.patientIDao = patientIDao;
    }
    public void save(Patient patient){
        patientIDao.save(patient);
    }
    public Patient findById(Integer id){
        return patientIDao.findById(id);
    }
    public void update (Patient patient){
        patientIDao.update(patient);
    }
    public void delete(Integer id){
        patientIDao.delete(id);
    }
    public List<Patient> findAll(){
        return patientIDao.findAll();
    }
}
