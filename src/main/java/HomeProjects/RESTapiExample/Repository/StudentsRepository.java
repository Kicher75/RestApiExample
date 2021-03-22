package HomeProjects.RESTapiExample.Repository;

import HomeProjects.RESTapiExample.Model.Student;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository {

    PageImpl<Student> studentsByPage(Pageable sort);
    Student getStudentById(int id);
    void updateStudentById(int id, Student updatedStudent);
    void deleteStudentById(int id);
    long createStudent(Student studentToBeAdded);

}
