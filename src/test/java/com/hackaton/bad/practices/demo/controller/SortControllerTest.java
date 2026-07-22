package com.hackaton.bad.practices.demo.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hackaton.bad.practices.demo.service.SortingService;
import com.hackaton.bad.practices.demo.service.SortingServiceNew;

import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class SortControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /sort/bubble - Should use real SortingService to sort the list")
    void testBubbleSort() throws Exception {
        // Given
        List<Integer> unsorted = List.of(5, 3, 1, 4, 2);
        List<Integer> expected = List.of(1, 2, 3, 4, 5);

        // When & Then
        mockMvc.perform(post("/sort/bubble")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unsorted)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    @DisplayName("POST /sort/quick - Should use real SortingService to sort the list")
    void testQuickSort() throws Exception {
        // Given
        List<Integer> unsorted = List.of(9, 2, 7, 1);
        List<Integer> expected = List.of(1, 2, 7, 9);

        // When & Then
        mockMvc.perform(post("/sort/quick")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unsorted)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    @DisplayName("POST /sort/insertion - Should use real SortingService & SortingServiceNew")
    void testInsertionSort() throws Exception {
        // Given
        List<Integer> unsorted = List.of(4, 1, 3);
        List<Integer> expected = List.of(1, 3, 4);

        // When & Then
        mockMvc.perform(post("/sort/insertion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unsorted)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }
}
