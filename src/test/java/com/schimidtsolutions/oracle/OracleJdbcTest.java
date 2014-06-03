package com.schimidtsolutions.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OracleJdbcTest {
	private static final String INSERT = "INSERT INTO person(person_id, person_name, age ) VALUES(?, ?, ?)";
	private static final String SELECT = "SELECT person_id, person_name, age FROM person WHERE person_id=?";
	private static final String UPDATE = "UPDATE person SET age=? WHERE person_id=?";
	private static final String DELETE = "DELETE FROM person WHERE person_id=?";
	private Connection connection;
	
	@BeforeClass
	private void init() throws SQLException{
		connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:estudo", "C##SYSDEV", "segfor47");
		connection.setAutoCommit( true );
	}

	@Test
	public void testInsertPerson() throws SQLException { 
		PreparedStatement statement = connection.prepareStatement( INSERT );
		statement.setInt( 1, -1 );
		statement.setString( 2, "Teste" );
		statement.setShort( 3, Short.parseShort( "666" ) );
		
		Assert.assertTrue( statement.executeUpdate() > 0 );
	}
	
	@Test(dependsOnMethods={"testInsertPerson"})
	public void testRetryPerson() throws SQLException { 		
		PreparedStatement statement = connection.prepareStatement( SELECT );
		statement.setInt( 1, -1 );
		
		ResultSet resultSet = statement.executeQuery();
		
		Assert.assertNotNull( resultSet );
		Assert.assertTrue( resultSet.next() );
		
		String nome = resultSet.getString( "person_name" );
		Assert.assertTrue( nome.equals( "Teste" ) );
	}
	
	@Test(dependsOnMethods={"testRetryPerson"})
	public void testUpdatePerson() throws SQLException { 		
		PreparedStatement statement = connection.prepareStatement( UPDATE );
		statement.setShort(1, (short) 35);
		statement.setInt(2, -1);
		
		Assert.assertTrue( statement.executeUpdate() > 0 );
	}
	
	@Test(dependsOnMethods={"testUpdatePerson"})
	public void testDeletePerson() throws SQLException { 		
		PreparedStatement statement = connection.prepareStatement( DELETE );
		statement.setInt(1, -1);
		
		Assert.assertTrue( statement.executeUpdate() > 0 );
	}
}
