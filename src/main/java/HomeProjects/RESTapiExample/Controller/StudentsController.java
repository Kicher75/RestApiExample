package HomeProjects.RESTapiExample.Controller;

import HomeProjects.RESTapiExample.Model.Student;
import HomeProjects.RESTapiExample.Service.StudentServiceImpl;
import HomeProjects.RESTapiExample.Service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private final StudentsService service;

    @Autowired
    public StudentsController(StudentServiceImpl service){
        this.service = service;
    }


    @GetMapping
    public PageImpl<Student> getPageOfStudents(@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
                                               @RequestParam(name = "elementsByPage", defaultValue = "5") int elementsByPage,
                                               @RequestParam(name = "sortBy", defaultValue = "regTime") String sortBy,
                                               @RequestParam(name = "sortDirection", defaultValue = "ASC") String sortDirection){
        return service.studentsByPage(pageNum,elementsByPage,sortBy,sortDirection);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") int id){
        return service.studentById(id);
    }

    @PostMapping
    public long createNewStudent(@RequestBody Student student){
        return service.createStudent(student);
    }

    @PostMapping("/{id}")
    public void updateStudentById(@PathVariable("id") int id,
                                  @RequestBody Student student){
        service.updateStudentById(id, student);
    }

}
