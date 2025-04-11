import com.university.model.Class;
import com.university.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public List<Class> findAll() {
        return classRepository.findAll();
    }

    public Optional<Class> findById(Long id) {
        return classRepository.findById(id);
    }

    public Class save(Class classEntity) {
        return classRepository.save(classEntity);
    }

    public void deleteById(Long id) {
        classRepository.deleteById(id);
    }

    public List<Class> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return findAll();
        }
        return classRepository.findByNameContaining(keyword);
    }
}
