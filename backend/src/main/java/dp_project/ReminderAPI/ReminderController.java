package dp_project.ReminderAPI;

import dp_project.DoctorAPI.Doctor;
import dp_project.DoctorAPI.DoctorRepository;
import dp_project.PatientAPI.Patient;
import dp_project.authentication.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8202","http://localhost:8101"})

public class ReminderController {
    @Autowired
    private ReminderService reminderService;
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping(path = "/reminders")
    @PreAuthorize("hasRole('DOCTOR')")
    public @ResponseBody ResponseEntity<?> getAllRemindersByDoctorId(Authentication authentication) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();
        Doctor doctor = doctorRepository.findByUserId(userId);
        return reminderService.getAllRemindersByDoctor(doctor);
    }

    @GetMapping(path = "/reminder/history/{patient_id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public @ResponseBody ResponseEntity<?> getPatientReminderHistory(Authentication authentication,
                                                                     @PathVariable("patient_id") Integer patient_id) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();
        // TODO: patient is doctor's patient
        Doctor doctor = doctorRepository.findByUserId(userId);
        return reminderService.getPatientRecentHistory(patient_id);
    }

    @GetMapping(path = "/patientreminders")
    @PreAuthorize("hasRole('PATIENT')")
    public @ResponseBody ResponseEntity<List<Reminder>> getAllRemindersByPatient(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();
        return reminderService.getAllRemindersByPatientId(userId);
    }

    @GetMapping(path = "/reminders/{reminder_id}")
    @PreAuthorize("hasRole('PATIENT')")
    public @ResponseBody ResponseEntity<Reminder> getReminderById(Authentication authentication,
                                                                        @PathVariable("reminder_id") Integer reminder_id) {

        return reminderService.getReminderById(reminder_id);
    }

    @PostMapping(path="/addreminder/{patient_id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public @ResponseBody ResponseEntity<Reminder> addNewPatient(Authentication authentication,
                                                                @PathVariable("patient_id") Integer patient_id,
                                                                @RequestBody Reminder reminderrequest) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();

        Doctor doctor = doctorRepository.findByUserId(userId);
        return reminderService.addNewReminder(doctor.getId(), patient_id, reminderrequest);
    }

    @GetMapping(path="/finishreminder/{reminder_id}")
    @PreAuthorize("hasRole('PATIENT')")
    public @ResponseBody ResponseEntity<Reminder> finishReminderById(Authentication authentication,
                                                                     @PathVariable("reminder_id") Integer reminder_id) {
        System.out.println("Function used");
        return reminderService.finishReminderById(reminder_id);
    }



}
