package bcp.spring;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.yaml.SpringProfileDocumentMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(basePackages = { "bcp.spring" })
@Slf4j
public class EntityManagerConfigurationH2 {

	@Autowired
	DataSource datasource;

	@Autowired
	Environment env;
	
	@Bean
	public Properties properties() {
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();

		SpringProfileDocumentMatcher spdm = new SpringProfileDocumentMatcher();
		spdm.addActiveProfiles(env.getActiveProfiles());
		yaml.setDocumentMatchers(spdm);

		yaml.setResources(new ClassPathResource("resources.yaml"));
		Properties properties = yaml.getObject();

		log.info("Properties loaded from {}", "resources.yaml");
		return properties;
	}
	
	public static HibernateJpaVendorAdapter makeVendorAdaptor(Properties properties) {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabasePlatform(properties.getProperty("dialect"));
		jpaVendorAdapter.setGenerateDdl((Boolean) properties.get("generateDdl"));
		jpaVendorAdapter.setShowSql(true);

		return jpaVendorAdapter;
	}
	
	public static Map<String, Object> makeJpaProperties(Properties properties, List<String> keys) {
		Map<String, Object> jpaProperties = new HashMap<String, Object>();

		for (String key : keys) {
			jpaProperties.put(key, properties.get(key));
		}

		return jpaProperties;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(datasource);
		emf.setPackagesToScan("bcp.spring");

		HibernateJpaVendorAdapter jpaVendorAdapter = makeVendorAdaptor(properties());
		emf.setJpaVendorAdapter(jpaVendorAdapter);

		List<String> keys = Arrays.asList("hibernate.connection.charSet", "hibernate.format_sql",
				"hibernate.hbm2ddl.auto", "hibernate.default_schema");
		Map<String, Object> jpaProperties = makeJpaProperties(properties(), keys);

		emf.setJpaPropertyMap(jpaProperties);
		emf.afterPropertiesSet();

		return emf;
	}

}
