package br.com.marcos.business;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ListTest {

     @Test
     void testMockingList_When_SizeIsCalled_ShouldReturn10() {
         //Given / Arrange
         List<?> list = mock(List.class);
         given(list.size()).willReturn(10);
         //When / Act
         //Then / Assert
         assertThat(list.size() ,is(10));

     }
     @Test
     void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues() {
         //Given / Arrange
         List<?> list = mock(List.class);
         given(list.size()).willReturn(10).willReturn(20);
         //When / Act
         //Then / Assert
         assertThat(list.size(), is(10));
         assertThat(list.size(), is(20));
         assertThat(list.size(), is(20));
     }
     @Test
     void testMockingList_When_SizeIsCalled_ShouldReturnErudio() {
         //Given / Arrange
         var list = mock(List.class);
         given(list.get(0)).willReturn("Erudio");
         //When / Act
         //Then / Assert
         assertThat(list.get(0), is("Erudio"));
         assertThat(list.get(1), is(nullValue()));
     }
     @Test
     void testMockingList_When_GetIsArgumentMatcher_ShouldReturnErudio() {
         //Given / Arrange
         var list = mock(List.class);
         //if you are using argument matchers, all arguments have to be provided by matchers.
         given(list.get(anyInt())).willReturn("Erudio");
         //When / Act
         //Then / Assert
         assertThat(list.get(anyInt()), is("Erudio"));
         assertThat(list.get(anyInt()), is("Erudio"));
     }


    @Test
    void testMockingList_When_ThrowsAnException() {
        // Given / Arrange
        var list = mock(List.class);
        // Se você estiver usando argument matchers, todos os argumentos devem ser fornecidos por matchers.
        given(list.get(anyInt())).willThrow(new RuntimeException("Foo Bar!!"));

        // When / Act & Then / Assert
        assertThrows(RuntimeException.class, () -> {
            // Chamada do método get que deve lançar uma exceção
            list.get(anyInt());
        }, "Should have thrown a RuntimeException");
    }
}
