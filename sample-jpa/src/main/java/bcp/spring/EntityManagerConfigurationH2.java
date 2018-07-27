package bcp.spring;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableJpaRepositories(basePackages = { "bcp.spring" })
@Slf4j
public class EntityManagerConfigurationH2 {

	@Autowired
	DataSource datasource;

	@Autowired
	Environment env;
	
	@Bean
	@SneakyThrows(IOException.class)
	public Properties properties()  {
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
		Resource res = new ClassPathResource("resources.yaml");
		yaml.setResources(res);
		Properties properties = yaml.getObject();

		log.info("Properties loaded from {}", res.getURI());
		return properties;
	}
	
	public static HibernateJpaVendorAdapter makeVendorAdaptor(Properties properties) {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabasePlatform(properties.getProperty("dialect"));
		jpaVendorAdapter.setGenerateDdl((Boolean) properties.get("generateDdl"));
		jpaVendorAdapter.setShowSql(true);

		return jpaVendorAdapter;
	}
 
  /**
   * A bean named entityManagerFactory is needed by the JPA repositories
   * 
   * @return
   */
	@Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
    emf.setDataSource(datasource);
    emf.setPackagesToScan("bcp.spring");

    HibernateJpaVendorAdapter jpaVendorAdapter = makeVendorAdaptor(properties());
    emf.setJpaVendorAdapter(jpaVendorAdapter);

    List<String> keys = Arrays.asList("hibernate.connection.charSet", 
        "hibernate.format_sql", 
        "hibernate.hbm2ddl.auto",
        "hibernate.default_schema");

    Map<String, Object> jpaProperties = keys.stream()
        .collect(Collectors.toMap(Function.identity(), k -> properties().get(k)));

    emf.setJpaPropertyMap(jpaProperties);
    emf.afterPropertiesSet();

    return emf;
  }

}
