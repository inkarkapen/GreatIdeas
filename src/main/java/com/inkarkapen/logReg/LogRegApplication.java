package com.inkarkapen.logReg;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LogRegApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogRegApplication.class, args);
	}
	 @Bean
	    public EmbeddedServletContainerFactory servletContainer() {
	        TomcatEmbeddedServletContainerFactorycopy tomcat = new TomcatEmbeddedServletContainerFactory();
	        Connector ajpConnector = new Connector("AJP/1.3");
	        ajpConnector.setPort(9090);
	        ajpConnector.setSecure(false);
	        ajpConnector.setAllowTrace(false);
	        ajpConnector.setScheme("http");
	        tomcat.addAdditionalTomcatConnectors(ajpConnector);
	        return tomcat;
	    }
}
