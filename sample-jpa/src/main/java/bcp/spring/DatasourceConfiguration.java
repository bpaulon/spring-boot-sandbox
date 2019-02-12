package bcp.spring;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DatasourceConfiguration {

//	@Bean
//	@Qualifier("test")
//	public DataSource dataSourceTest() {
//	  log.debug(">>>>> mem h2 created");
//		return new EmbeddedDatabaseBuilder()
//			.setType(EmbeddedDatabaseType.H2)
//			.addScript("classpath:schema.sql")
//			.build();
//	}

  @Bean
  @Qualifier("h2File")
  public DataSource fileDataSource() {
    JdbcDataSource ds = new JdbcDataSource();
    ds.setURL("jdbc:h2:file:./testDb");
    ds.setUser("sa");
    ds.setPassword("");

    // schema init
    Resource initSchema = new ClassPathResource("schema.sql");
    DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
    DatabasePopulatorUtils.execute(databasePopulator, ds);
    return ds;
  }
}
