package spring.boot.example.wicket;

import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.spring.SpringWebApplicationFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * Global Spring-Configuration.
 */
@Configuration
@ComponentScan(basePackages = "spring.boot.example.wicket")
public class SpringConfiguration implements ServletContextInitializer {

    private static final String PARAM_APP_BEAN = "applicationBean";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration filter = servletContext.addFilter("wicket-filter", WicketFilter.class);
        filter.setInitParameter(WicketFilter.APP_FACT_PARAM, SpringWebApplicationFactory.class.getName());
        filter.setInitParameter(PARAM_APP_BEAN, "wicketWebApplication");
        filter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR), false, "/*");
    }
}
