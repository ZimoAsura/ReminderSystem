package dp_project.PatientAPI;

import dp_project.DoctorAPI.Doctor;
import dp_project.DoctorAPI.DoctorRepository;
import dp_project.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    EntityManager entityManager;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    // get all patients
    public ResponseEntity<List<Patient>> getAllPatients(){
        List<Patient> patients = new ArrayList<Patient>();
        patientRepository.findAll().forEach(patients::add);
        if (patients.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // get doctor by id
    public ResponseEntity<Patient> getPatientById(Integer id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found patient with id" + id));
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    // get all patients of a doctor with id
//    public ResponseEntity<List<Patient>> getAllPatientsByDoctorId(Integer doctor_id) {
//        if (!patientRepository.existsById(doctor_id)) {
//            throw new ResourceNotFoundException("Not found doctor with id " + doctor_id);
//        }
//        List<Patient> patients = new ArrayList<Patient>();
//        patientRepository.findByDoctorId(doctor_id).forEach(patients::add);
//        return new ResponseEntity<>(patients, HttpStatus.OK);
//    }

//    public ResponseEntity<Patient> addNewPatient (Integer doctor_id, Patient patientrequest){
//        Patient patient = doctorRepository.findById(doctor_id).map(doctor -> {
//            patientrequest.setDoctor(doctor);
//            return patientRepository.save(patientrequest);
//        }).orElseThrow(() -> new ResourceNotFoundException("Not found doctor with id = " + doctor_id));
//        return new ResponseEntity<>(patient, HttpStatus.CREATED);
//    }

}
