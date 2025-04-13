import com.university.model.Student;
import com.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements ApplicationRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Only add if no admin exists
        if (studentRepository.findByEmail("temp@admin.com") == null) {
            Student tempAdmin = new Student();
            tempAdmin.setEmail("temp@admin.com");
            tempAdmin.setFirstName("Temp");
            tempAdmin.setLastName("Admin");
            tempAdmin.setPassword(passwordEncoder.encode("admin"));
            tempAdmin.setRole("ADMIN");
            studentRepository.save(tempAdmin);

            System.out.println("Created temporary admin user with email: temp@admin.com and password: admin");
        }
    }
}