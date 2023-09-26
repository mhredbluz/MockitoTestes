package br.com.marcos.business;

import br.com.marcos.service.CourseService;
import br.com.marcos.service.stubs.CourseServiceStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseBusinessStubTest {

     @DisplayName("Display Name")
     @Test
     void testCoursesRelatedToSpring_When_UsingAStub() {

         //Given / Arrange
         CourseService stubService = new CourseServiceStub();
         CourseBusiness business = new CourseBusiness(stubService);

         //When / Act
         var filteredCourses = business.retrieveCoursesRelatedToSpring("Marcos");

         //Then / Assert
         assertEquals(4, filteredCourses.size());
     }
    @DisplayName("Display Name")
    @Test
    void testCoursesRelatedToSpring_When_UsingAFooBarStudente() {

        //Given / Arrange
        CourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);

        //When / Act
        var filteredCourses = business.retrieveCoursesRelatedToSpring("Marcos");

        //Then / Assert
        assertEquals(4, filteredCourses.size());
    }
}
