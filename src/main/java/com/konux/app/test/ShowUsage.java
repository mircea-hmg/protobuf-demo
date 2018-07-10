package com.konux.app.test;

import org.apache.log4j.Logger;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ShowUsage implements ApplicationListener {

    public static final Logger log = Logger.getLogger(ShowUsage.class);

    @Override
    public void onApplicationEvent(final ApplicationEvent applicationEvent) {
        if(applicationEvent instanceof EmbeddedServletContainerInitializedEvent)
            printSwaggerDocLink();
    }

    private void printSwaggerDocLink() {
        log.info("\n\nUsage:\n" +
                 "\thttp://localhost:8080/collector/swagger-ui.html \n");
    }
}
