package com.example.wardeploydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class EntityManageConfig {

    @Autowired
    private DataSource myDataSource;

    @Bean
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManageFactory(builder).getObject().createEntityManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManageFactory(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = builder.dataSource(myDataSource)
                .packages("com.example.wardeploydemo").build();
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        jpaProperties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        jpaProperties.put("hibernate.connection.charSet", "utf-8");
        jpaProperties.put("hibernate.show_sql", "true");
        entityManagerFactory.setJpaProperties(jpaProperties);
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManageFactory(builder).getObject());
    }
}