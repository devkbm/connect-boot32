package com.like.system.core.session;


import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

// maxInactiveIntervalInSeconds : default 1800sec(30 minute)
@Configuration
@EnableJdbcHttpSession(maxInactiveIntervalInSeconds = 60)
public class HttpSessionConfig {

}