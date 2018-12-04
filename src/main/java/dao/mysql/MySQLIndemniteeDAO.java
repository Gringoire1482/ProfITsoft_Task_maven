package dao.mysql;

import dao.ConnectionFactory;
import dao.Constants;
import dao.DatabaseException;
import dao.GenericDAO;
import dict.ClientType;
import service.clients.Client;
import service.clients.Indemnitee;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;

public class MySQLIndemniteeDAO implements GenericDAO<Indemnitee> {
    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    @Override
    public long create(Indemnitee entity) throws DatabaseException {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.INSERT_INDEMNITEE_QUERY,Statement.RETURN_GENERATED_KEYS)) {


            ps.setString(1, entity.getFullName());
            ps.setDate(2, Date.valueOf(entity.getDateOfBirth()));
            ps.setDouble(3, entity.getCost());
            int n = ps.executeUpdate();
            if (n != 1) {
                throw new DatabaseException("ROWS INSERTED" + n);
            }
            try(ResultSet resultSet = ps.getGeneratedKeys()){
                resultSet.next();
                return resultSet.getLong(1);
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Indemnitee read(long id) throws DatabaseException {
        Indemnitee result;

        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SELECT_INDEMNITEE_BY_ID);) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (!resultSet.next()) {
                    result = null;
                } else {
                    result = new Indemnitee();
                    result.setId(resultSet.getLong("ID"));
                    result.setFullName(resultSet.getString("NAME"));
                    result.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH")
                            .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    result.setCost(resultSet.getDouble("COST"));


                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return result;

    }

    @Override
    public void update(Indemnitee entity) throws DatabaseException {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_INDEMNITEE_QUERY)) {
            statement.setString(1, entity.getFullName());
            statement.setDate(2, Date.valueOf(entity.getDateOfBirth()));
            statement.setDouble(3, entity.getCost());
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
             PreparedStatement statement = connection.prepareStatement(Constants.DELETE_INDEMNITEE_QUERY)) {
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

