package yammer.metrics

import com.codahale.metrics.servlets.AdminServlet
import grails.plugins.Plugin
import groovy.util.logging.Log4j
import org.grails.plugins.metrics.groovy.HealthCheckServletContextInitializer
import org.grails.plugins.metrics.groovy.MetricsServletContextInitializer
import org.springframework.boot.context.embedded.ServletRegistrationBean

import javax.servlet.ServletContextEvent

@Log4j
class YammerMetricsGrailsPlugin extends Plugin {

	// the plugin version
    def version = "3.0.1-2"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.0.3 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views",
            "web-app/**"
    ]

    // TODO Fill in these fields
    def title = "Yammer Metrics" // Headline display name of the plugin
    def author = "Your name"
    def authorEmail = ""
    def description = '''\
This plugin provides an easy way for grails apps to create application health checks and
metrics (timers, meters, counters, histograms, etc) from Coda Hale's metrics package

Pulls in the following metrics jars :
   * metrics-core
   * metrics-healthchecks
   * metrics-servlets (wired to the /metrics end point for the app).

See the source code documentation on Github for more details.
'''
    def profiles = ['web']

    // URL to the plugin's documentation
    def documentation = "http://github.com/jeffellis/grails-yammer-metrics"

    Closure doWithSpring() { {->
        // TODO Implement runtime spring config (optional)
        metricsAdminServlet(ServletRegistrationBean, new AdminServlet(), "/metrics/admin/*") {
            loadOnStartup = 2
        }
    } }

    void doWithDynamicMethods() {
        // TODO Implement registering dynamic methods to classes (optional)
    }

    @Override
    void doWithApplicationContext() {
        // Create registries for HealthChecks and Metrics here, and stuff them into the servlet context.  Don't
        // wait for the regular listener lifecycle because that happens after application BootStrap.groovy. Since
        // we're doing this here, there is no need to wire them as real listeners.

        ServletContextEvent event = new ServletContextEvent( applicationContext.servletContext )
        HealthCheckServletContextInitializer healthCheckServletContextInitializer = new HealthCheckServletContextInitializer()
        healthCheckServletContextInitializer.contextInitialized(event)

        MetricsServletContextInitializer metricsServletContextInitializer = new MetricsServletContextInitializer()
        metricsServletContextInitializer.contextInitialized(event)
    }

    void onChange(Map<String, Object> event) {
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    void onConfigChange(Map<String, Object> event) {
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    void onShutdown(Map<String, Object> event) {
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
