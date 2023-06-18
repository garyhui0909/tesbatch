package com.gary.testbatch.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gary.testbatch.TesbatchApplication;
import org.springframework.boot.SpringApplication;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TestConvertObject {

    public static void main(String[] args) {
        new TestConvertObject().testConvert();
    }

    public void testConvert() {
        Employee employee = new Employee(1,
                "Alex",
                "Kwun Tong",
                LocalDate.of(1995, 1, 2),
                List.of("Delhi", "Nevada"),
                List.of(new Role(11, "Finance"), new Role(12, "HR")));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        Map<String, String> map = objectMapper.convertValue(employee, Map.class);

        System.out.println(map);


    }

//    private Map<String, String> convertObjectToMapUsingObjectMapper(Employee employee) {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
//
//        return objectMapper.convertValue(employee, Map.class);
//    }

}
