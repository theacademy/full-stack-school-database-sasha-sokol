package mthree.com.fullstackschool.dao;

import mthree.com.fullstackschool.dao.mappers.TeacherMapper;
import mthree.com.fullstackschool.model.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TeacherDaoImpl implements TeacherDao {

    private final JdbcTemplate jdbcTemplate;

    public TeacherDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Teacher createNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE

        var result = jdbcTemplate.update("INSERT INTO teacher VALUES (?, ?, ?, ?)",
            teacher.getTeacherId(),
            teacher.getTeacherFName(),
            teacher.getTeacherLName(),
            teacher.getDept());

        if (result == 1) return teacher;
        else return null;

        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE

        return jdbcTemplate.query("SELECT * FROM teacher", new TeacherMapper());

        //YOUR CODE ENDS HERE
    }

    @Override
    public Teacher findTeacherById(int id) {
        //YOUR CODE STARTS HERE

        return jdbcTemplate.queryForObject("SELECT * FROM teacher WHERE tid=?", new TeacherMapper(), id);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateTeacher(Teacher t) {
        //YOUR CODE STARTS HERE

        jdbcTemplate.update("UPDATE teacher SET tFName=?, tLName=?, dept=? WHERE tid=?",
            t.getTeacherFName(),
            t.getTeacherLName(),
            t.getDept(),
            t.getTeacherId());

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteTeacher(int id) {
        //YOUR CODE STARTS HERE

        jdbcTemplate.update("DELETE FROM teacher WHERE tid=?", id);

        //YOUR CODE ENDS HERE
    }
}
