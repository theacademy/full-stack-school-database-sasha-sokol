package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {

    //YOUR CODE STARTS HERE

    @Autowired
    private CourseDao courseDao;

    //YOUR CODE ENDS HERE

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        return courseDao.getAllCourses();

        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE

        Course result;

        try {
            result = courseDao.findCourseById(id);
        } 
        catch (DataAccessException e) {
            result = new Course();
            result.setCourseName("Course Not Found");
            result.setCourseDesc("Course Not Found");
        }

        return result;

        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE

        boolean courseOk = true;

        if (course.getCourseName().isBlank()) {
            course.setCourseName("Name blank, course NOT added");
            courseOk = false;
        }
        if (course.getCourseDesc().isBlank()) {
            course.setCourseDesc("Description blank, course NOT added");
            courseOk = false;
        }

        if (courseOk) return courseDao.createNewCourse(course);
        else return course;

        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE

        if (id == course.getCourseId())
        {
            courseDao.updateCourse(course);
        }
        else {
            course.setCourseName("IDs do not match, course not updated");
            course.setCourseDesc("IDs do not match, course not updated");
        }
        return course;

        //YOUR CODE ENDS HERE
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE

        courseDao.deleteCourse(id);
        System.out.println("Course ID: " + id + " deleted");

        //YOUR CODE ENDS HERE
    }
}
