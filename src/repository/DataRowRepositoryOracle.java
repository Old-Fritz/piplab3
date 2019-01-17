package repository;


import beans.DataRow;
import lombok.extern.java.Log;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.management.Query;
import java.util.LinkedList;
import java.util.List;
import java.sql.*;

public class DataRowRepositoryOracle implements DataRowRepository {

    private  Connection connection;


    public DataRowRepositoryOracle()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orbis", "s244703", "czz328");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    public List<DataRow> getAllResults() {
        List<DataRow> results = new LinkedList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    "select * from checkdata");
            while (rs.next()) {
                int id = rs.getInt(1);
                double xParam = rs.getDouble(2);
                double yParam = rs.getDouble(3);
                double rParam = rs.getDouble(4);
                int result = rs.getInt(5);
                Date date = new Date(rs.getTimestamp(6).getTime());

                DataRow row = new DataRow(id, xParam,yParam,rParam,result,date);
                results.add(row);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    @Override
    public boolean save(DataRow check) {
        check.setDate(new java.util.Date());
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into checkdata values (NULL, ?, ?, ?, ?, ?)");
            statement.setDouble(1,check.getParamX());
            statement.setDouble(2,check.getParamY());
            statement.setDouble(3,check.getParamR());
            statement.setInt(4,check.getResult());
            statement.setTimestamp(5,new Timestamp(check.getDate().getTime()));
            statement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}