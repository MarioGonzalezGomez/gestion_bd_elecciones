package com.mggcode.gestion_bd_elecciones.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "autonomicasEntityManagerFactory",
        transactionManagerRef = "autonomicasTransactionManager", basePackages = {
        "com.mggcode.gestion_bd_elecciones.repository.autonomicas"
})
public class AutonomicasDB {
    @Value("${autonomicas.jpa.database-platform}")
    private String dialect;

    @Bean(name = "autonomicasDatasource")
    @ConfigurationProperties(prefix = "autonomicas.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "autonomicasEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(@Qualifier("autonomicasDatasource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("com.mggcode.gestion_bd_elecciones.model.autonomicas");
        HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendor);
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", dialect);
        factoryBean.setJpaPropertyMap(properties);
        return factoryBean;
    }

    @Bean(name = "autonomicasTransactionManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("autonomicasEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


    @Bean
    @Primary
    @ConfigurationProperties(prefix="autonomicas.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix="autonomicas.seconddatasource")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
