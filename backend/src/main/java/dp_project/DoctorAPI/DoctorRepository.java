package dp_project.DoctorAPI;

import dp_project.PatientAPI.Patient;
import org.springframework.data.repository.CrudRepository;


public interface DoctorRepository extends CrudRepository<Doctor, Integer> {
    Doctor findByUserId(Long user_id);
}
