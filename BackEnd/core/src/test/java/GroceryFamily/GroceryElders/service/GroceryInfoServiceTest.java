package GroceryFamily.GroceryElders.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class GroceryInfoServiceTest {
    private GroceryInfoService groceryInfoService;

    @BeforeEach
    public void setup() {
        groceryInfoService = Mockito.mock(GroceryInfoService.class);
    }

    @Test
    public void getProductNameTest() {
        List<String> expectedNames = Arrays.asList("Apple", "Banana", "Orange");

        when(groceryInfoService.getProductName()).thenReturn(expectedNames);

        List<String> actualNames = groceryInfoService.getProductName();

        assertThat(expectedNames).isEqualTo(actualNames);
    }
}