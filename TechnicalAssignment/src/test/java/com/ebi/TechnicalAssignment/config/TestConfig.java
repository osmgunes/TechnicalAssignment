package com.ebi.TechnicalAssignment.config;



import com.ebi.TechnicalAssignment.repository.MouseGeneSynonymRelationRepository;
import com.ebi.TechnicalAssignment.repository.OrthologRepository;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.io.File;
import java.net.URL;


@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = { MouseGeneSynonymRelationRepository.class,
        OrthologRepository.class })
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application-test.properties")
public class TestConfig {

    @Autowired
    Environment env;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    @Value("${h2.schema.path}")
    private String schemaFilePath;

    @Value("${h2.data.path}")
    private String dataPath;

    @Bean
    public DataSource dataSource() {

        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder().setType(
                EmbeddedDatabaseType.H2).addScript(String.format("classpath:%s", schemaFilePath));

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(dataPath);
        File directory = new File(resource.getFile());

        for (File file : directory.listFiles()) {
            embeddedDatabaseBuilder.addScript(String.format("classpath:%s%s", dataPath, file.getName()));
        }

        return embeddedDatabaseBuilder.build();

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan("com.ebi.TechnicalAssignment.model", "com.ebi.TechnicalAssignment.repository"
        ,"com.ebi.TechnicalAssignment.service","com.ebi.TechnicalAssignment.dto");
        entityManager.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        return entityManager;
    }

}
