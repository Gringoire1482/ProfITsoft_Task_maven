package dao.mysql;

import dao.ConnectionFactory;
import dao.Constants;
import dao.DatabaseException;
import dao.GenericDAO;
import dict.ClientType;
import service.clients.Client;

import java.sql.*;

public class MySQLClientDAO implements GenericDAO<Client> {
    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    @Override
    public long create(Client entity) throws DatabaseException {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.INSERT_CLIENT_QUERY,Statement.RETURN_GENERATED_KEYS) ){


            ps.setString(1, entity.getFullName());
            ps.setString(2, entity.getType().toString());
            ps.setString(3, entity.getAddress());
            int n = ps.executeUpdate();
            if (n != 1) {
                throw new DatabaseException("Number of the inserted rows" + n);
            }
            try(ResultSet resultSet = ps.getGeneratedKeys()){
                resultSet.next();
                return resultSet.getLong(1);
            }
        } catch (DatabaseException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Client read(long id) throws DatabaseException {
        Client result;

        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SELECT_CLIENT_BY_ID);) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (!resultSet.next()) {
                    result = null;
                } else {
                    result = new Client();
                    result.setId(resultSet.getLong("ID"));
                    result.setFullName(resultSet.getString("NAME"));
                    result.setType(ClientType.valueOf(resultSet.getString("CLIENT_TYPE")));
                    result.setAddress(resultSet.getString("ADDRESS"));


                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return result;
    }

    @Override
    public void update(Client entity) throws DatabaseException {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_CLIENT_QUERY)) {
            statement.setString(1, entity.getFullName());
            statement.setString(2, entity.getType().toString());
            statement.setString(3, entity.getAddress());
            statement.setLong(4, entity.getId());
            int n = statement.executeUpdate();
            if (n != 1) {
                throw new DatabaseException("ROWS UPDATED " + n);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

    }

    @Override
    public void delete(long id) throws DatabaseException {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.DELETE_CLIENT_QUERY)) {
            statement.setLong(1, id);
            int n = statement.executeUpdate();
            if (n != 1) {
                throw new DatabaseException("ROWS DELETED " + n);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }


    }
}
