package HomeProjects.RESTapiExample.Service;

import HomeProjects.RESTapiExample.Model.Student;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public interface StudentsService {

    PageImpl<Student> studentsByPage(int pageNum, int elementsByPage,
                                     String sortBy, String sortDirection);
    Student studentById(int id);
    void updateStudentById(int id, Student updatedStudent);
    void deleteStudentById(int id);
    long createStudent(Student studentToBeAdded);
}
