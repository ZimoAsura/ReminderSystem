package dp_project.DoctorAPI;

import dp_project.Exception.ResourceNotFoundException;
import dp_project.PatientAPI.Patient;
import dp_project.PatientAPI.PatientRepository;
import dp_project.authentication.models.User;
import dp_project.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.Doc;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    // get all doctors
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        List<Doctor> doctors = new ArrayList<Doctor>();
        doctorRepository.findAll().forEach(doctors::add);
        if (doctors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }



    // get doctor by id
    public ResponseEntity<Doctor> getDoctorById(Integer id){
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found doctor with id" + id));
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }


    public ResponseEntity<List<Patient>> getAllPatientsByDoctorId(Long userId) {
        Doctor doctor = doctorRepository.findByUserId(userId);
        List<Patient> patients = new ArrayList<>();
        patientRepository.findByDoctorId(doctor.getId()).forEach(patients::add);

        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    public ResponseEntity<Patient> addNewPatient (Long userId, Integer patientId){
        Doctor doctor = doctorRepository.findByUserId(userId);
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found patient with id " + patientId));
        patient.setDoctor(doctor);
        patientRepository.save(patient);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    // add new doctor
//    public ResponseEntity<Doctor> addNewDoctor (String name){
//        Doctor doctor = new Doctor();
//        doctor.setName(name);
//        doctorRepository.save(doctor);
//        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
//    }

//    public String addNewPatient (Integer doctor_id, String name){
//        Optional <Doctor> doctor = doctorRepository.findById(doctor_id);
//        Patient patient = new Patient();
//        patient.setName(name);
//        doctor.getPatients().add(patient);
//        doctorRepository.save(doctor);
//        return "New Patient added";
//    }

}
