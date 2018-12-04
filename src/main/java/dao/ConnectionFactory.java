package dao;


import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private  DataSource dataSource;
    private  Properties p = new Properties();
    private static final ConnectionFactory instance= new ConnectionFactory();

    public static  ConnectionFactory getInstance() {
        return instance;
    }
  ConnectionFactory() {

        try {
            p.load(getClass().getClassLoader().getResourceAsStream(
                    "db.properties"));
            dataSource =  BasicDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Connection  getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}

















