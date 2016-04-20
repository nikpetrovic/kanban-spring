package com.nikpetrovic.kanban;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.planetj.servlet.filter.compression.CompressingFilter;

@SpringBootApplication
public class KanbanApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanbanApplication.class, args);
    }

    @Bean
    public Filter compressingFilter() {
        CompressingFilter compressingFilter = new CompressingFilter();
        return compressingFilter;
    }
}
