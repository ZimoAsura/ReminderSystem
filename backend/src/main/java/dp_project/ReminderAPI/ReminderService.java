package dp_project.ReminderAPI;

import dp_project.DoctorAPI.Doctor;
import dp_project.DoctorAPI.DoctorRepository;
import dp_project.Exception.ResourceNotFoundException;
import dp_project.PatientAPI.Patient;
import dp_project.PatientAPI.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    // get all reminders
    public ResponseEntity<List<Reminder>> getAllReminders() {
        List<Reminder> reminders = new ArrayList<Reminder>();
        reminderRepository.findAll().forEach(reminders::add);
        if (reminders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reminders, HttpStatus.OK);
    }

    // get all reminders of a doctor
    public ResponseEntity<?> getAllRemindersByDoctor(Doctor doctor) {
        System.out.println(doctor.getId());
        List<Object[]> agg = reminderRepository.countReminderByPatient(doctor.getId());
        return ResponseEntity.ok(agg);
    }

    // get reminder by id
    public ResponseEntity<Reminder> getReminderById(Integer reminder_id) {
        System.out.println(reminder_id);
        Reminder reminder = reminderRepository.findById(reminder_id).get();
        return new ResponseEntity<>(reminder, HttpStatus.OK);
    }

    // update the reminder by id
    public ResponseEntity<Reminder> finishReminderById(Integer reminder_id) {
        Reminder reminder = reminderRepository.findById(reminder_id).get();
        System.out.println(reminder.getId());

        Status s = Status.Finished;
        reminder.setStatus(s);
        reminderRepository.save(reminder);
        System.out.println("Changed");
        return new ResponseEntity<>(reminder, HttpStatus.OK);
    }

    // get all reminders of a patient
    public ResponseEntity<List<Reminder>> getAllRemindersByPatientId(Long userId) {
        Patient patient = patientRepository.findByUserId(userId);
        System.out.println(patient.getId());
        List<Reminder> reminders = new ArrayList<Reminder>();
        reminderRepository.findByPatientId(patient.getId()).forEach(reminders::add);
        return new ResponseEntity<>(reminders, HttpStatus.OK);
    }

    public ResponseEntity<?> getPatientRecentHistory(Integer patientId) {
        LocalDate today = LocalDate.now();
        LocalDate weekAgo = today.minusDays(6);
        // query result
        List<Object[]> agg = reminderRepository.countRecentReminderByPatient(patientId, weekAgo.toString());
        TreeMap<String, Integer> dateCount = new TreeMap<>();
        // populate query result into TreeMap
        for (Object[] obj: agg){
            String tmpDate = obj[0].toString();
            Integer tmpCount = ((BigInteger) obj[1]).intValue();
            dateCount.put(tmpDate, tmpCount);
        }

        // full list of dates
        for (LocalDate day = weekAgo; day.compareTo(today) <= 0; day = day.plusDays(1)) {
            if (dateCount.containsKey(day.toString())){
                continue;
            } else {
                dateCount.put(day.toString(), 0);
            }
        }

        Map<String, ArrayList<Object>> visFormat = new TreeMap<>();
        visFormat.put("x",new ArrayList<>(dateCount.keySet()));
        visFormat.put("y", new ArrayList<>(dateCount.values()));
        return ResponseEntity.ok(visFormat);
    }

    // add new reminder by doctor to a patient
    public ResponseEntity<Reminder> addNewReminder (Integer doctor_id, Integer patient_id, Reminder reminderrequest){
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctor_id);
        if (!doctorOptional.isPresent()) {
            throw new ResourceNotFoundException("Not found doctor with id = " + doctor_id);
        }
        Optional<Patient> patientOptional = patientRepository.findById(patient_id);
        if (!patientOptional.isPresent()) {
            throw new ResourceNotFoundException("Not found patient with id = " + patient_id);
        }

        Doctor doctor = doctorOptional.get();
        Patient patient = patientOptional.get();
        reminderrequest.setPatient(patient);
        reminderRepository.save(reminderrequest);
        return new ResponseEntity<>(reminderrequest, HttpStatus.CREATED);
    }

}
