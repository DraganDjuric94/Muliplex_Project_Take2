/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unibl.etf.model.dao.SjedisteDAO;
import org.unibl.etf.model.domain.Sjediste;

/**
 *
 * @author Aleksandar
 */
public class MySQLSjedisteDAO implements SjedisteDAO {

    private static final String SQL_SELECT = "SELECT * FROM sjediste";
    private static final String SQL_INSERT = "INSERT INTO sjediste VALUES (null, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE sjediste SET SalaId=?, Kolona=?, Vrsta=? WHERE SjedisteId=?";
    private static final String SQL_DELETE = "DELETE FROM sjediste WHERE SjedisteId=?";

    @Override
    public List<Sjediste> selectAll() {
        List<Sjediste> retVal = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()) {
                retVal.add(
                        new Sjediste(
                                resultSet.getInt("SjedisteId"),
                                resultSet.getInt("SalaId"),
                                resultSet.getInt("Kolona"),
                                resultSet.getInt("Vrsta")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLDAOFactory.close(resultSet, statement, connection);
        }
        return retVal;
    }

    @Override
    public List<Sjediste> selectBy(Sjediste sjediste) {
        List<Sjediste> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            String query = MySQLSjedisteDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();

            if (null != sjediste.getSjedisteId()) {
                query += " AND SjedisteId=?";
                pom.add(sjediste.getSjedisteId());
            }

            if (null != sjediste.getSalaId()) {
                query += " AND SalaId=?";
                pom.add(sjediste.getSalaId());
            }

            if (null != sjediste.getKolona()) {
                query += " AND Kolona=?";
                pom.add(sjediste.getKolona());
            }

            if (null != sjediste.getVrsta()) {
                query += " AND Vrsta=?";
                pom.add(sjediste.getVrsta());
            }

            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                retVal.add(
                        new Sjediste(
                                resultSet.getInt("SjedisteId"),
                                resultSet.getInt("SalaId"),
                                resultSet.getInt("Kolona"),
                                resultSet.getInt("Vrsta")
                        )
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySQLSalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLDAOFactory.close(resultSet, preparedStatement, connection);
        }

        return retVal;
    }

    @Override
    public int insert(Sjediste sjediste) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                sjediste.getSalaId(),
                sjediste.getKolona(),
                sjediste.getVrsta()
            };
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_INSERT, true, values);
            retVal = preparedStatement.executeUpdate();
            if (0 != retVal) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    sjediste.setSjedisteId(resultSet.getInt(1));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySQLSalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLDAOFactory.close(resultSet, preparedStatement, connection);
        }
        return retVal;
    }

    @Override
    public int update(Sjediste sjediste) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                sjediste.getSalaId(),
                sjediste.getKolona(),
                sjediste.getVrsta(),
                sjediste.getSjedisteId()
            };
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_UPDATE, false, values);
            retVal = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MySQLSalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLDAOFactory.close(resultSet, preparedStatement, connection);
        }
        return retVal;
    }

    @Override
    public int delete(Integer sjedisteId) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object value = sjedisteId;
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_DELETE, false, value);
            retVal = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MySQLSalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLDAOFactory.close(preparedStatement, connection);
        }
        return retVal;
    }

}
