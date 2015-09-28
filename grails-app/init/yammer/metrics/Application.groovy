package yammer.metrics

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.boot.context.embedded.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import com.codahale.metrics.servlets.AdminServlet

class Application extends GrailsAutoConfiguration {

    /*@Bean
    public ServletRegistrationBean metricsAdminServletBean(){
        return new ServletRegistrationBean(new AdminServlet(),"/metrics/admin/*");
    }*/

    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }
}