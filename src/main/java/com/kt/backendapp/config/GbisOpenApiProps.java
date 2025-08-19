// config/GbisOpenApiProps.java
package com.kt.backendapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gbis-openapi")
public record GbisOpenApiProps(String base, String key) {}
