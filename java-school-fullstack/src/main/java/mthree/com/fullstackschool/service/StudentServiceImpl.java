package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.model.Student;
import java.text.MessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CourseServiceImpl courseService;

    //YOUR CODE ENDS HERE

    public StudentServiceImpl(StudentDao studentDao, CourseServiceImpl courseService) {
        
        this.studentDao = studentDao;
        this.courseService = courseService;

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

        var student = studentDao.findStudentById(studentId);
        var course = courseService.getCourseById(courseId);

        if (student.getStudentFirstName().equals("Student Not Found")) {
            System.out.println("Student not found");
        }
        else if (course.getCourseName().equals("Course Not Found")) {
            System.out.println("Course not found");
        }
        else {
            studentDao.deleteStudentFromCourse(studentId, courseId);
            System.out.println(MessageFormat.format("Student: {0} deleted from course: {1}", studentId, courseId));
        }

        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        var student = studentDao.findStudentById(studentId);
        var course = courseService.getCourseById(courseId);

        if (student.getStudentFirstName().equals("Student Not Found")) {
            System.out.println("Student not found");
        }
        else if (course.getCourseName().equals("Course Not Found")) {
            System.out.println("Course not found");
        }
        else {
            try {
                studentDao.addStudentToCourse(studentId, courseId);
                System.out.println(MessageFormat.format("Student: {0} added to course: {1}", studentId, courseId));
            }
            catch (DataAccessException e) {
                System.out.println(MessageFormat.format("Student: {0} already enrolled in course: {1}", studentId, courseId));
            }
            
        }

        //YOUR CODE ENDS HERE
    }
}
