package com.example.timesheet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/health", produces = "application/json")
public class HealthController {

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity pong() {
        return new ResponseEntity(HttpStatus.OK);
    }
}