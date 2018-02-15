package ca.mcgill.ecse321.treePLE;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import ca.mcgill.ecse321.treePLE.TreePLESpringApplication;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TreePLESpringApplication.class);
    }
}
