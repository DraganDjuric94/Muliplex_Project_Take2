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
import org.unibl.etf.model.dao.RacunDAO;
import org.unibl.etf.model.domain.Racun;

/**
 *
 * @author Aleksandar
 */
public class MySQLRacunDAO implements RacunDAO {

    private static final String SQL_SELECT = "SELECT * FROM racun";
    private static final String SQL_INSERT = "INSERT INTO racun VALUES (null, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE racun SET DatumVrijeme=?, UkupnaCijena=? WHERE RacunId=?";
    private static final String SQL_DELETE = "DELETE FROM racun WHERE RacunId=?";

    @Override
    public List<Racun> selectAll() {
        List<Racun> retVal = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()) {
                retVal.add(
                        new Racun(
                                resultSet.getInt("RacunId"),
                                resultSet.getTimestamp("DatumVrijeme"),
                                resultSet.getDouble("UkupnaCijena")
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
    public List<Racun> selectBy(Racun racun) {
        List<Racun> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            String query = MySQLRacunDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();

            if (null != racun.getRacunId()) {
                query += " AND RacunId=?";
                pom.add(racun.getRacunId());
            }

            if (null != racun.getDatumVrijeme()) {
                query += " AND DatumVrijeme=?";
                pom.add(racun.getDatumVrijeme());
            }

            if (null != racun.getUkupnaCijena()) {
                query += " AND UkupnaCijena";
                pom.add(racun.getUkupnaCijena());
            }

            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                retVal.add(
                        new Racun(
                                resultSet.getInt("RacunId"),
                                resultSet.getTimestamp("DatumVrijeme"),
                                resultSet.getDouble("UkupnaCijena")
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
    public int insert(Racun racun) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                racun.getDatumVrijeme(),
                racun.getUkupnaCijena()
            };
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_INSERT, true, values);
            retVal = preparedStatement.executeUpdate();
            if (0 != retVal) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    racun.setRacunId(resultSet.getInt(1));
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
    public int update(Racun racun) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                racun.getDatumVrijeme(),
                racun.getUkupnaCijena(),
                racun.getRacunId()
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
    public int delete(Integer racunId) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object value = racunId;
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
