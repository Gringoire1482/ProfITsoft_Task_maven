package dao;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConnectionFactoryTest {

    @Before
    public void setUp() throws Exception {
        ConnectionFactory connectionFactory= ConnectionFactory.getInstance();
    }

    @Test
    public void getInstance() throws SQLException {
        ConnectionFactory connectionFactory= ConnectionFactory.getInstance();
        assertNotNull(connectionFactory);
        assertNotNull(connectionFactory.getConnection());
    }

    @Test
    public void getConnection() {

    }
}