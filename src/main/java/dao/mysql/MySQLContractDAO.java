package dao.mysql;

import dao.ConnectionFactory;
import dao.Constants;
import dao.DatabaseException;
import dao.GenericDAO;
import service.Contract;
import service.clients.Indemnitee;

import java.sql.*;
import java.time.ZoneId;
import java.util.List;

public class MySQLContractDAO implements GenericDAO<Contract> {
    MySQLClientDAO clientDAO;
    MySQLIndemniteeDAO indemniteeDAO;
    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    @Override
    public long create(Contract entity) throws DatabaseException {
        long clientID=entity.getClient().getId();
        long result;
      if(clientDAO.read(entity.getClient().getId())==null){
            clientID=clientDAO.create(entity.getClient());
      }
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.INSERT_CONTRACT_QUERY,Statement.RETURN_GENERATED_KEYS)) {


            ps.setDate(1, Date.valueOf(entity.getSigned()));
            ps.setDate(2, Date.valueOf(entity.getStartDate()));
            ps.setDate(3, Date.valueOf(entity.getExpireDate()));
            ps.setLong(4, clientID);
            int n = ps.executeUpdate();
            if (n != 1) {
                throw new DatabaseException("ROWS INSERTED" + n);
            }

            try(ResultSet resultSet = ps.getGeneratedKeys()){
                resultSet.next();
              result = resultSet.getLong(1);
            }


        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        List<Indemnitee> indemniteeList = entity.getIndemnitees();
      long[] indemnitiesId= new long[indemniteeList.size()];
      for (int i=0;i<indemniteeList.size();i++){

          indemnitiesId[i]=indemniteeDAO.create(indemniteeList.get(i));
      }
      try(Connection connection= connectionFactory.getConnection();
          PreparedStatement ps =  connection.prepareStatement(Constants.INSERT_CONTRACTS_HAS_INDEMNITIES)) {
          for(int i=0;i<indemnitiesId.length;i++){
              ps.setLong(1,result);
              ps.setLong(2,indemnitiesId[i]);
              ps.addBatch();
          }
          ps.executeBatch();
      } catch (SQLException e) {
          throw new DatabaseException(e);
      }
        return result;
    }

    @Override
    public Contract read(long id) throws DatabaseException {
        Contract result;

        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(Constants.SELECT_CONTRACT_BY_ID)) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (!resultSet.next()) {
                    result = null;
                } else {
                    result = new Contract();
                    result.setId(resultSet.getLong("ID"));
                    result.setSigned(resultSet.getDate("SIGN_DATE")
                            .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    result.setSigned(resultSet.getDate("START_DATE")
                            .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    result.setSigned(resultSet.getDate("EXPIRE_DATE")
                            .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    result.setClient(clientDAO.read(resultSet.getLong("CLIENT_ID")));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return result;


    }

    @Override
    public void update(Contract entity) throws DatabaseException {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_CONTRACT_QUERY)) {

            statement.setDate(1, Date.valueOf(entity.getSigned()));
            statement.setDate(2, Date.valueOf(entity.getStartDate()));
            statement.setDate(3, Date.valueOf(entity.getExpireDate()));
            statement.setLong(4, entity.getClient().getId());
            statement.setLong(5, entity.getId());
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
             PreparedStatement statement = connection.prepareStatement(Constants.DELETE_CONTRACT_QUERY)) {
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
