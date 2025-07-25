package accounts.database;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AccountDBSpringBootApplicationTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void dataSourceTest()throws SQLException{
		assertThat(dataSource.getClass().getName()).isEqualTo("com.zaxxer.hikari.HikariDataSource");
		assertThat(dataSource.getConnection().getMetaData().getDatabaseProductName()).isEqualTo("H2");
	}

}
