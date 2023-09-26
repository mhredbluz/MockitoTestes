package br.com.marcos.mockito.staticwithparams;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;
import static org.junit.jupiter.api.Assertions.*;

public class MyUtilsTest {

     @DisplayName("Display Name")
     @Test
     void shouldMockStaticMethodWithParams() {
         try(MockedStatic<MyUtils> mockedStatic = mockStatic(MyUtils.class)){
             mockedStatic.when(
                 () -> MyUtils.getWelcomeMessage(eq("Marcos"),
                         anyBoolean())).thenReturn("Howdy Marcos!");

             String result = MyUtils.getWelcomeMessage("Marcos", false);

             assertEquals("Howdy Marcos!", result);
         }
     }
}
