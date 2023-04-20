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
@EnableJpaRepositories(entityManagerFactoryRef = "municipalesEntityManagerFactory",
        transactionManagerRef = "municipalesTransactionManager", basePackages = {
        "com.mggcode.gestion_bd_elecciones.repository.municipales"
})
public class MunicipalesDB {
    @Value("${municipales.jpa.database-platform}")
    private String dialect;

    @Bean(name = "municipalesDatasource")
    @ConfigurationProperties(prefix = "municipales.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "municipalesEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(@Qualifier("municipalesDatasource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("com.mggcode.gestion_bd_elecciones.model.municipales");
        HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendor);
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", dialect);
        factoryBean.setJpaPropertyMap(properties);
        return factoryBean;
    }

    @Bean(name = "municipalesTransactionManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("municipalesEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix="municipales.datasource")
    public DataSource municipalesPrimaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix="municipales.seconddatasource")
    public DataSource municipalesSecondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
