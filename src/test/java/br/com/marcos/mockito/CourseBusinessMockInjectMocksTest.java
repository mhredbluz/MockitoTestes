package br.com.marcos.mockito;

import br.com.marcos.business.CourseBusiness;
import br.com.marcos.service.CourseService;
import br.com.marcos.service.stubs.CourseServiceStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
public class CourseBusinessMockInjectMocksTest {

    @Mock
    CourseService mockService;
    @InjectMocks
    CourseBusiness business;
    @Captor
    ArgumentCaptor<String> argumentCaptor;
    List<String> courses;

    @BeforeEach
    void setUp(){
        //Given / Arrange
        //business = new CourseBusiness(mockService);
        courses = Arrays.asList(
                "REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Spotify Engineering Culture Desmistificado",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
                "Docker do Zero à Maestria - Contêinerização Desmistificada",
                "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
                "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
                "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
                "Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
        );
    }

     @DisplayName("Display Name")
     @Test
     void testCoursesRelatedToSpring_When_UsingAMock() {

         given(mockService.retrieveCourses("Marcos")).willReturn(courses);

         //When / Act
         var filteredCourses = business.retrieveCoursesRelatedToSpring("Marcos");

         //Then / Assert
         assertThat(filteredCourses.size(), is(4));
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

     // test[System Under Test]_[Condition or State Change]_[Expected Result]
         @DisplayName("Delete Courses not Related to Spring Using Mokito sould call Method")
         @Test
         void testDeleteCoursesNotRelatedToSpring_Using_MokitoVerify() {
             //Given / Arrange
             given(mockService.retrieveCourses("Marcos")).willReturn(courses);
             //When / Act
             business.deleteCoursesNotRelatedToSpring("Marcos");
             //Then / Assert
             verify(mockService, atLeastOnce()).deleteCourse("REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker");
             verify(mockService).deleteCourse("Spotify Engineering Culture Desmistificado");
             verify(mockService, never()).deleteCourse("REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker");
         }
         @DisplayName("Delete Courses not Related to Spring Using Mokito sould call Method V2")
         @Test
         void testDeleteCoursesNotRelatedToSpring_Using_MokitoVerifyV2() {
             //Given / Arrange
             given(mockService.retrieveCourses("Marcos")).willReturn(courses);
             //When / Act
             business.deleteCoursesNotRelatedToSpring("Marcos");
             //Then / Assert
             then(mockService)
                     .should()
                     .deleteCourse("REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker");
             then(mockService)
                     .should()
                     .deleteCourse("Spotify Engineering Culture Desmistificado");
             then(mockService)
                     .should(never())
                     .deleteCourse("REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker");
         }
         @DisplayName("Delete Courses not Related to Spring Capturing Arguments")
         @Test
         void testDeleteCoursesNotRelatedToSpring_CapturingArguments() {
             //Given / Arrange

             given(mockService.retrieveCourses("Marcos")).willReturn(courses);
             String restSpringCourse = "REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker";
             //ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

             //When / Act
             business.deleteCoursesNotRelatedToSpring("Marcos");

             //Then / Assert
             then(mockService)
                     .should(times(7))
                     .deleteCourse(argumentCaptor.capture());
             assertThat(argumentCaptor.getAllValues().size(),  is(7));
         }
}
