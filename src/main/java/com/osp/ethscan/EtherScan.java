package com.osp.ethscan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.osp.ethscan.filter.CrossDomainFilter;

/**
 * 
 * @author zhangmingcheng
 * @date 2018年4月3日
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
public class EtherScan {

	public static void main(String[] args) {
		SpringApplication.run(EtherScan.class, args);
	}
	
	/**
	 * 跨域处理Filter
	 * 
	 * @return
	 */
	@Bean
    @Order(Integer.MAX_VALUE)
	public FilterRegistrationBean crossDomainFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new CrossDomainFilter());
		registration.addUrlPatterns("/*");
		registration.setName("CrossDomainFilter");
		return registration;
	}


}
