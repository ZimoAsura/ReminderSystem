package dp_project.DoctorAPI;

import dp_project.authentication.models.User;

import javax.persistence.*;


@Entity
@Table(name = "Doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "doctor_generator")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Doctor() {
        // do nothing
    }

    public Doctor(User user){
        this.user = user;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Set<Patient> getPatients() {
//        return patients;
//    }
//
//    public void setPatients(Set<Patient> patients) {
//        this.patients = patients;
//    }

//    public Set<Reminder> getReminders() {
//        return reminders;
//    }
//
//    public void setReminders(Set<Reminder> reminders) {
//        this.reminders = reminders;
//    }
}
