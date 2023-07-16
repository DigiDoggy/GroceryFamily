package com.example.FinalProject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        assertEquals(expectedNames, actualNames);
    }
}
