package com.hackaton.bad.practices.demo.controller;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.bad.practices.demo.dtos.PaymentDTO;
import com.hackaton.bad.practices.demo.service.SortingService;
import com.hackaton.bad.practices.demo.service.SortingServiceNew;

@RestController
public class SortController {

    private static final Logger logger = LogManager.getLogger(SortController.class);

    @Autowired
    private SortingService sortingService;

    @Autowired
    private SortingServiceNew sortingServiceNew;

    @PostMapping("/sort/bubble")
    public List<Integer> bubleSort(@RequestBody List<Integer> numbers) {
        logger.info("Info log message");
        logger.info("bubble sort");
        return this.sortingService.bubleSort(numbers);
    }

    @PostMapping("/sort/quick")
    public List<Integer> quickSort(@RequestBody List<Integer> numbers) {
        logger.info("Info log message");
        logger.info("quick sort");
        return this.sortingService.quickSort(numbers);
    }

        @PostMapping("/sort/insertion")
    public List<Integer> insertionSort(@RequestBody List<Integer> numbers) {
        logger.info("Info log message");
        logger.info("insertion sort");
        if(sortingServiceNew.validateList(numbers)) {
            return this.sortingService.quickSort(numbers);
        } else if(sortingServiceNew.validateList(numbers)) {
            return this.sortingService.quickSort(numbers);
        } else {
            return this.sortingService.quickSort(numbers);
        }
    }

}
