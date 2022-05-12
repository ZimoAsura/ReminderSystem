package dp_project.DoctorAPI;

import dp_project.PatientAPI.Patient;
import dp_project.authentication.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This means that this class is a Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/doctors") // This means URL's start with /demo (after Application path)
@PreAuthorize("hasRole('DOCTOR')")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping(path="/patients")
    @PreAuthorize("hasRole('DOCTOR')")
    public @ResponseBody ResponseEntity<List<Patient>> getAllPatients(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();
        return doctorService.getAllPatientsByDoctorId(userId);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Doctor> getDoctorById(@PathVariable("id") Integer id) {
        return doctorService.getDoctorById(id);
    }

    @PostMapping("/addpatient/{id}")
    public @ResponseBody ResponseEntity<Patient> addPatient(Authentication authentication,
                                                            @PathVariable("id") Integer patientId){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();
        return doctorService.addNewPatient(userId, patientId);
    }
}
