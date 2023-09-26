package br.com.marcos.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.marcos.mockito.services.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class OrderServiceTest {

    private final OrderService cut = new OrderService();
    private final UUID defaultUuid = UUID.fromString("8d8b30e3-de52-4f1c-a71c-9905a8043dac");
    private final LocalDateTime defaultLocalDateTime = LocalDateTime.of(2023, 9, 26, 8, 35);

    @DisplayName("should Include Randen OrderId When No OrederID Exists")
    @Test
    void shouldIncludeRandomOrderIdWhenNoParentOrderExists() {

        try (MockedStatic<UUID> mockedUuid = Mockito.mockStatic(UUID.class)) {
            mockedUuid.when(UUID::randomUUID).thenReturn(defaultUuid);

            Order result = cut.createOrder("MacBook Pro", 2L, null);

            assertEquals("8d8b30e3-de52-4f1c-a71c-9905a8043dac", result.getId());
        }
    }

    @DisplayName("should Include Randen OrderId When No OrederID Exists")
    @Test
    void testShouldIncludeCurrentTime_When_CreateANewOrder() {
        try (MockedStatic<LocalDateTime> mockedLocalDateTime = Mockito.mockStatic(LocalDateTime.class)) {
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);

            Order result = cut.createOrder("MacBook Pro", 2L, "42");
            assertEquals(defaultLocalDateTime, result.getCreationDate());
        }
    }
}
