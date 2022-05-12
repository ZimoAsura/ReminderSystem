package dp_project.PatientAPI;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer>{
    Iterable<Patient> findByDoctorId(Integer doctor_id);
    Patient findByUserId (Long user_id);
}
