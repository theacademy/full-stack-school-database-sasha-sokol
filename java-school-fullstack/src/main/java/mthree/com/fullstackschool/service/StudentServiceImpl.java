package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE

    @Autowired
    private StudentDao studentDao;

    //YOUR CODE ENDS HERE

    public StudentServiceImpl(StudentDao studentDao) {
        
        this.studentDao = studentDao;

    }

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        return studentDao.getAllStudents();

        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE

        Student result;

        try {
            result = studentDao.findStudentById(id);
        } 
        catch (DataAccessException e) {
            result = new Student();
            result.setStudentFirstName("Student Not Found");
            result.setStudentLastName("Student Not Found");
        }

        return result;

        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE

        boolean studentOk = true;

        if (student.getStudentFirstName().isBlank()) {
            student.setStudentFirstName("First Name blank, student NOT added");
            studentOk = false;
        }
        if (student.getStudentLastName().isBlank()) {
            student.setStudentLastName("Last Name blank, student NOT added");
            studentOk = false;
        }

        if (studentOk) return studentDao.createNewStudent(student);
        else return student;

        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE

        if (id == student.getStudentId())
        {
            studentDao.updateStudent(student);
        }
        else {
            student.setStudentFirstName("IDs do not match, student not updated");
            student.setStudentLastName("IDs do not match, student not updated");
        }
        return student;

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE

        studentDao.deleteStudent(id);

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE



        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE


        //YOUR CODE ENDS HERE
    }
}
