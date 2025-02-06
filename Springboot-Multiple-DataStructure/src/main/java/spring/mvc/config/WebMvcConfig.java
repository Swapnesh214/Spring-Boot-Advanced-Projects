package spring.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	//

	@Bean
	public OpenEntityManagerInViewFilter securityOpenEntityManagerInViewFilter() {
		OpenEntityManagerInViewFilter osivFilter = new OpenEntityManagerInViewFilter();
		osivFilter.setEntityManagerFactoryBeanName("securityEntityManagerFactory");
		return osivFilter;
	}

	@Bean
	public OpenEntityManagerInViewFilter ordersOpenEntityManagerInViewFilter() {
		OpenEntityManagerInViewFilter osivFilter = new OpenEntityManagerInViewFilter();
		osivFilter.setEntityManagerFactoryBeanName("ordersEntityManagerFactory");
		return osivFilter;
	}
}
