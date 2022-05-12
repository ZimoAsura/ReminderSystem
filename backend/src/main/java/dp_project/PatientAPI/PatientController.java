package dp_project.PatientAPI;

import dp_project.DoctorAPI.Doctor;
import dp_project.ReminderAPI.Reminder;
import dp_project.authentication.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/patients/{id}")
    public @ResponseBody ResponseEntity<Patient> getPatientById(@PathVariable("id") Integer id) {
        return patientService.getPatientById(id);
    }

}

