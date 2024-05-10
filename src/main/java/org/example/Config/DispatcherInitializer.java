package org.example.Config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class}; // Provide the root configuration class
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0]; // We don't need to specify a separate servlet configuration class
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

