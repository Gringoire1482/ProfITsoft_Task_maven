package dao;

public class Constants {
    private Constants(){}
    public static final String UPDATE_CLIENT_QUERY = "UPDATE clients SET  NAME=?,CLIENT_TYPE=? ADDRESS=? WHERE ID=?";
    public static final String UPDATE_INDEMNITEE_QUERY = "UPDATE indemnities SET  NAME=?,DATE_OF_BIRTH=? COST=? WHERE ID=?";
    public static final String UPDATE_CONTRACT_QUERY = "UPDATE contracts SET  SIGN_DATE=?,START_DATE=? EXPIRE_DATE=? CLIENT_ID=? WHERE ID=?";
    public static final String DELETE_CLIENT_QUERY = "DELETE FROM clients WHERE ID = ?";
    public static final String DELETE_INDEMNITEE_QUERY = "DELETE FROM indemnities WHERE ID = ?";
    public static final String DELETE_CONTRACT_QUERY = "DELETE FROM contracts WHERE ID = ?";
    public static final String SELECT_CLIENT_BY_ID = "SELECT * FROM clients WHERE ID = ?";
    public static final String SELECT_INDEMNITEE_BY_ID = "SELECT * FROM indemnities WHERE ID = ?";
    public static final String SELECT_CONTRACT_BY_ID = "SELECT * FROM contracts WHERE ID = ?";
    public static final String INSERT_CLIENT_QUERY = "INSERT INTO clients (ID,NAME,CLIENT_TYPE,ADDRESS) VALUES (DEFAULT,?,?,?)";
    public static final String INSERT_INDEMNITEE_QUERY = "INSERT INTO indemnities (ID,NAME,DATE_OF_BIRTH,COST) VALUES (DEFAULT,?,?,?)";
    public static final String INSERT_CONTRACT_QUERY = "INSERT INTO contracts (ID,SIGN_DATE,START_DATE,EXPIRE_DATE,CLIENT_ID) VALUES (DEFAULT,?,?,?,?)";
    public static final String INSERT_CONTRACTS_HAS_INDEMNITIES = "INSERT into contracts_has_indemnities VALUES (?,?)";
}
