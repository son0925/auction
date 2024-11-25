package org.son.webapplicationserver.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.son.db.user")
@EnableJpaRepositories(basePackages = "org.son.db.user")
public class JpaConfig {
}
