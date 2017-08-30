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
import org.unibl.etf.model.dao.OpremaDAO;
import org.unibl.etf.model.domain.Oprema;

/**
 *
 * @author Aleksandar
 */
public class MySQLOpremaDAO implements OpremaDAO {

    private static final String SQL_SELECT = "SELECT * FROM oprema";
    private static final String SQL_INSERT = "INSERT INTO oprema VALUES (null, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE oprema SET BrojInventara=?, Naziv=?, Ispravnost=? WHERE OpremaId=?";
    private static final String SQL_DELETE = "DELETE FROM oprema WHERE OpremaId=?";

    @Override
    public List<Oprema> selectAll() {
        List<Oprema> retVal = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()) {
                retVal.add(
                        new Oprema(
                                resultSet.getInt("OpremaId"),
                                resultSet.getString("BrojInventara"),
                                resultSet.getString("Naziv"),
                                resultSet.getBoolean("Ispravnost")
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
    public List<Oprema> selectBy(Oprema oprema) {
        List<Oprema> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            String query = MySQLOpremaDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();

            if (null != oprema.getOpremaId()) {
                query += " AND OpremaId=?";
                pom.add(oprema.getOpremaId());
            }

            if (null != oprema.getBrojInventara()) {
                query += " AND BrojInventara=?";
                pom.add(oprema.getBrojInventara());
            }

            if (null != oprema.getNaziv()) {
                query += " AND Naziv=?";
                pom.add(oprema.getNaziv());
            }

            if (null != oprema.getIspravnost()) {
                query += " AND Ispravnost=?";
                pom.add(oprema.getIspravnost());
            }

            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                retVal.add(
                        new Oprema(
                                resultSet.getInt("OpremaId"),
                                resultSet.getString("BrojInventara"),
                                resultSet.getString("Naziv"),
                                resultSet.getBoolean("Ispravnost")
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
    public int insert(Oprema oprema) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                oprema.getBrojInventara(),
                oprema.getNaziv(),
                oprema.getIspravnost()
            };
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_INSERT, true, values);
            retVal = preparedStatement.executeUpdate();
            if (0 != retVal) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    oprema.setOpremaId(resultSet.getInt(1));
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
    public int update(Oprema oprema) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                oprema.getBrojInventara(),
                oprema.getNaziv(),
                oprema.getIspravnost(),
                oprema.getOpremaId()
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
    public int delete(Integer OpremaId) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object value = OpremaId;
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
