package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.TeacherDao;
import mthree.com.fullstackschool.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherServiceInterface {

    //YOUR CODE STARTS HERE

    @Autowired
    private TeacherDao teacherDao;

    //YOUR CODE ENDS HERE

    public TeacherServiceImpl(TeacherDao teacherDao) {
        
        this.teacherDao = teacherDao;

    }

    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE

        return teacherDao.getAllTeachers();

        //YOUR CODE ENDS HERE
    }

    public Teacher getTeacherById(int id) {
        //YOUR CODE STARTS HERE

        Teacher result;

        try {
            result = teacherDao.findTeacherById(id);
        } 
        catch (DataAccessException e) {
            result = new Teacher();
            result.setTeacherFName("Teacher Not Found");
            result.setTeacherLName("Teacher Not Found");
        }

        return result;

        //YOUR CODE ENDS HERE
    }

    public Teacher addNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE

        boolean teacherOk = true;

        if (teacher.getTeacherFName().isBlank()) {
            teacher.setTeacherFName("First Name blank, teacher NOT added");
            teacherOk = false;
        }
        if (teacher.getTeacherLName().isBlank()) {
            teacher.setTeacherLName("Last Name blank, teacher NOT added");
            teacherOk = false;
        }

        if (teacherOk) return teacherDao.createNewTeacher(teacher);
        else return teacher;

        //YOUR CODE ENDS HERE
    }

    public Teacher updateTeacherData(int id, Teacher teacher) {
        //YOUR CODE STARTS HERE

        if (id == teacher.getTeacherId())
        {
            teacherDao.updateTeacher(teacher);
        }
        else {
            teacher.setTeacherFName("IDs do not match, teacher not updated");
            teacher.setTeacherLName("IDs do not match, teacher not updated");
        }
        return teacher;

        //YOUR CODE ENDS HERE
    }

    public void deleteTeacherById(int id) {
        //YOUR CODE STARTS HERE

        teacherDao.deleteTeacher(id);

        //YOUR CODE ENDS HERE
    }
}
