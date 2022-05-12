package dp_project.ReminderAPI;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReminderRepository extends CrudRepository<Reminder, Integer>{
    Iterable<Reminder> findByPatientId(Integer patient_id);

    @Query(value = "select\n" +
            "    patient_id,\n" +
            "    name,\n" +
            "    IFNULL(sum(high_count), 0) AS high_count,\n" +
            "    IFNULL(sum(middle_count), 0) AS middle_count,\n" +
            "    IFNULL(sum(low_count), 0) AS low_count\n" +
            "from \n" +
            "(\n" +
            "    select patient_id, name, \n" +
            "    case when priority=\"High\" then unfinished_count end as high_count,\n" +
            "    case when priority=\"Middle\" then unfinished_count end as middle_count,\n" +
            "    case when priority=\"Low\" then unfinished_count end as low_count\n" +
            "    from\n" +
            "    (\n" +
            "        select patient_id, users.username as name, priority, count(1) as unfinished_count \n" +
            "        from reminder join patient join users\n" +
            "        on reminder.patient_id = patient.id and patient.user_id = users.id\n" +
            "        where patient.doctor_id=:#{#doctor_id} and status=\"Unfinished\" group by patient_id, users.username, priority\n" +
            "    ) result\n" +
            ") expand\n" +
            "group by patient_id, name\n" +
            "order by high_count DESC, middle_count DESC, low_count DESC;",
            nativeQuery = true)
    List<Object[]> countReminderByPatient(Integer doctor_id);

    @Query(value = "select Date(assign_time) as date,count(1) as unfinished from reminder where patient_id=:#{#patient_id} " +
            "and duration <= CURRENT_TIME() and assign_time >= :date and status=\"Unfinished\" " +
            "group by Date(assign_time)\n" +
            "order by Date(assign_time);", nativeQuery = true)
    List<Object[]> countRecentReminderByPatient(Integer patient_id, String date);
}
