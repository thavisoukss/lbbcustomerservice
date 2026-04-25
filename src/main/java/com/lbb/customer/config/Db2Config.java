package com.lbb.customer.config;

import com.lbb.customer.buygold.db.repository.ProductRepository;
import com.lbb.customer.createaccount.db.repository.TdRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = {
                TdRepository.class,
                ProductRepository.class
        },
        entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "db2TransactionManager"
)
public class Db2Config {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSourceProperties db2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource db2DataSource() {
        return db2DataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(db2DataSource())
                .packages("com.lbb.customer.buygold.db.entity","com.lbb.customer.createaccount.db.entity")
                .persistenceUnit("db2")
                .properties(Map.of(
                        "hibernate.dialect", "org.hibernate.dialect.OracleDialect"
                ))
                .build();
    }

    @Bean
    public PlatformTransactionManager db2TransactionManager(
            @Qualifier("db2EntityManagerFactory") EntityManagerFactory emf) {

        return new JpaTransactionManager(emf);
    }
}