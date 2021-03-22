package HomeProjects.RESTapiExample.Service;

import HomeProjects.RESTapiExample.Model.Student;
import HomeProjects.RESTapiExample.Repository.StudentsRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class StudentServiceImpl implements StudentsService{

    private final StudentsRepositoryImpl repository;

    @Autowired
    public StudentServiceImpl(StudentsRepositoryImpl repository) {
        this.repository = repository;
    }

    private static final String[] SORTING_PARAMS = {
            "lastName",
            "age",
            "email",
            "regTime"
    };

    @Override
    public PageImpl<Student> studentsByPage(int pageNum, int elementsByPage,
                                            String sortBy, String sortDirection) {

        PageRequest pageRequest = Arrays.asList(SORTING_PARAMS).contains(sortBy) ?
                PageRequest.of(pageNum, elementsByPage, Sort.by(Sort.Direction.fromString(sortDirection), sortBy)) :
                PageRequest.of(pageNum, elementsByPage, Sort.by(Sort.Direction.fromString(sortDirection), "regTime"));

        return repository.studentsByPage(pageRequest);
    }

    @Override
    public Student studentById(int id) {
        return repository.getStudentById(id);
    }

    @Override
    public void updateStudentById(int id, Student updatedStudent) {
        repository.updateStudentById(id, updatedStudent);
    }

    @Override
    public void deleteStudentById(int id) {
        repository.deleteStudentById(id);
    }

    @Override
    public long createStudent(Student studentToBeAdded) {
        studentToBeAdded.setRegDate(LocalDateTime.now());
        return repository.createStudent(studentToBeAdded);
    }
}
