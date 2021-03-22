package HomeProjects.RESTapiExample.Utils;

import HomeProjects.RESTapiExample.Model.Student;
import org.springframework.beans.BeanWrapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setFirstName(resultSet.getString("firstName"));
        student.setLastName(resultSet.getString("lastName"));
        student.setAge(resultSet.getInt("age"));
        student.setEmail(resultSet.getString("email"));
        Timestamp regTime = Timestamp.valueOf(resultSet.getString("regTime"));
        student.setRegDate(regTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        return student;
    }
}
