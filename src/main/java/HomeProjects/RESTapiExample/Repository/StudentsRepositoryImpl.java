package HomeProjects.RESTapiExample.Repository;

import HomeProjects.RESTapiExample.Model.Student;
import HomeProjects.RESTapiExample.Utils.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class StudentsRepositoryImpl implements StudentsRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PageImpl<Student> studentsByPage(Pageable pageRequest) {

        Sort.Order order = pageRequest.getSort().toList().get(0);


        List<Student> students = jdbcTemplate.query("SELECT * FROM students ORDER BY " + order.getProperty() + " " +
                        order.getDirection() + " LIMIT " + pageRequest.getPageSize() + " OFFSET " + pageRequest.getOffset(),
                new StudentMapper());

        return new PageImpl<Student>(students, pageRequest, getTotal());
    }

    private long getTotal() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM students", Long.class);
    }

    @Override
    public Student getStudentById(int id) {
        return jdbcTemplate.query("SELECT * FROM students WHERE id=?",
                new StudentMapper(), new Object[]{id}).stream()
                .findAny().orElse(null);
    }

    @Override
    public void updateStudentById(int id, Student updatedStudent) {
        jdbcTemplate.update("UPDATE students SET firstName=?, lastName=?, age=?, email=? WHERE id=?",
                updatedStudent.getFirstName(),
                updatedStudent.getLastName(),
                updatedStudent.getAge(),
                updatedStudent.getEmail(),
                updatedStudent.getId());
    }

    @Override
    public void deleteStudentById(int id) {
        jdbcTemplate.update("DELETE FROM students WHERE id=?", id);
    }

    @Override
    public long createStudent(Student studentToBeAdded) {

        String insertSql = "INSERT INTO students" +
                " values (nextval('students_id_seq'),?,?,?,?,?)";
        KeyHolder idHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement st = connection.prepareStatement(insertSql, new String[]{"id"});
            st.setString(1, studentToBeAdded.getFirstName());
            st.setString(2, studentToBeAdded.getLastName());
            st.setInt(3, studentToBeAdded.getAge());
            st.setString(4, studentToBeAdded.getEmail());
            st.setObject(5, studentToBeAdded.getRegDate());
            return st;

        }, idHolder);
        return idHolder.getKey().longValue();
    }
}
