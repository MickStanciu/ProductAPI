package com.example.timesheet.configuration;


import com.example.timesheet.rest.HealthResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class RestEasyConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(HealthResource.class);
        return classes;
    }
}
