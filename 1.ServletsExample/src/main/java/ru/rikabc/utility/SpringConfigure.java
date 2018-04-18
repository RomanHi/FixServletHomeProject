package ru.rikabc.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.rikabc.repositories.ProductJdbcTemplateRepository;
import ru.rikabc.repositories.ProductRepository;
import ru.rikabc.repositories.UserJdbcTemplateRepository;
import ru.rikabc.repositories.UserRepository;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author Roman Khayrullin on 17.04.2018
 * @Version 1.0
 */
@Configuration
@ComponentScan(basePackages = "ru.rikabc")
public class SpringConfigure {

    @Bean
    @Autowired
    public UserRepository userRepository(@Qualifier("user") DataSource dataSourceUser) {
//        return new UserHibernateRepository();
//        return new UserRepositoryImplementation();
        return new UserJdbcTemplateRepository(dataSourceUser);
    }


    @Bean
    @Autowired
    public ProductRepository productRepository(@Qualifier("product") DataSource dataSourceProduct) {
//        return new ProductHibernateRepository();
//        return new ProductRepositoryImplementation();
        return new ProductJdbcTemplateRepository(dataSourceProduct);
    }

    @Bean(name = "user")
    public DataSource dataSourceUser() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties properties = new Properties();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("application.properties");

        try {
            properties.load(stream);
            dataSource.setUrl(properties.getProperty("db.user.url"));
            dataSource.setUsername(properties.getProperty("db.username"));
            dataSource.setPassword(properties.getProperty("db.password"));
            dataSource.setDriverClassName(properties.getProperty("db.driver"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean(name = "product")
    public DataSource dataSourceProduct() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties properties = new Properties();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("application.properties");

        try {
            properties.load(stream);
            dataSource.setUrl(properties.getProperty("db.product.url"));
            dataSource.setUsername(properties.getProperty("db.username"));
            dataSource.setPassword(properties.getProperty("db.password"));
            dataSource.setDriverClassName(properties.getProperty("db.driver"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

}
