package br.com.marcos.business;

import br.com.marcos.service.CourseService;

import java.util.ArrayList;
import java.util.List;

// SUT - System Under Test
public class CourseBusiness {

    //CourseService is a Dependency
    private CourseService service;


    public CourseBusiness(CourseService service) {
        this.service = service;
    }

    public List<String> retrieveCoursesRelatedToSpring(String student) {

        var filterCourses = new ArrayList<String>();
        if ("Foo Bar".equals(student)) return filterCourses;
        var allCourses = service.retrieveCourses(student);

        for (String course : allCourses) {
            if (course.contains("Spring")) {
                filterCourses.add(course);
            }
        }

        return filterCourses;

    }
    public void deleteCoursesNotRelatedToSpring(String student) {

        var allCourses = service.retrieveCourses(student);

        for (String course : allCourses) {
            if (!course.contains("Spring")) {
                service.deleteCourse(course);
            }
        }

    }
}
