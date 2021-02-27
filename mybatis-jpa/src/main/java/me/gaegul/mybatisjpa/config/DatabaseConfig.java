package me.gaegul.mybatisjpa.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "me.gaegul.mybatisjpa.user.repository")
@MapperScan(basePackages = "me.gaegul.mybatisjpa.user.mapper")
public class DatabaseConfig {

    private static final String DEFAULT_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy";

    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.h2")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource, ResourcePatternResolver resourcePatternResolver) throws Exception {
        SqlSessionFactoryBean session = new SqlSessionFactoryBean();
        session.setDataSource(dataSource());
        session.setTypeAliasesPackage("me.gaegul.mybatisjpa.user.model");
        session.setMapperLocations(resourcePatternResolver.getResources("classpath:mapper/**/*.xml"));
        return session.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(@Qualifier("dataSource") DataSource dataSource) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.physical_naming_strategy", DEFAULT_NAMING_STRATEGY);
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.format_sql","true");
		properties.put("hibernate.hbm2ddl.auto","create");

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setDataSource(dataSource);
        entityManager.setPackagesToScan("me.gaegul.mybatisjpa.user.model");
        entityManager.setJpaPropertyMap(properties);
        entityManager.afterPropertiesSet();
        return entityManager.getObject();
    }

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
