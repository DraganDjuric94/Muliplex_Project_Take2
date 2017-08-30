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
import org.unibl.etf.model.dao.ArtikalDAO;
import org.unibl.etf.model.domain.Artikal;

/**
 *
 * @author Aleksandar
 */
public class MySQLArtikalDAO implements ArtikalDAO {

    private static final String SQL_SELECT = "SELECT * FROM artikal";
    private static final String SQL_INSERT = "INSERT INTO artikal VALUES (null, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE artikal SET Naziv=?, KolicinaNaStanju=?, Barkod=?, Tip=?, Cijena=? WHERE ArtikalId=?";
    private static final String SQL_DELETE = "DELETE FROM artikal WHERE ArtikalId=?";

    @Override
    public List<Artikal> selectAll() {
        List<Artikal> retVal = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()) {
                retVal.add(
                        new Artikal(
                                resultSet.getInt("ArtikalId"),
                                resultSet.getString("Naziv"),
                                resultSet.getInt("KolicinaNaStanju"),
                                resultSet.getString("Barkod"),
                                resultSet.getString("Tip"),
                                resultSet.getDouble("Cijena")
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
    public List<Artikal> selectBy(Artikal artikal) {
        List<Artikal> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            String query = MySQLArtikalDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();

            if (null != artikal.getArtikalId()) {
                query += " AND ArtikalId=?";
                pom.add(artikal.getArtikalId());
            }

            if (null != artikal.getNaziv()) {
                query += " AND Naziv=?";
                pom.add(artikal.getNaziv());
            }

            if (null != artikal.getKolicinaNaStanju()) {
                query += " AND KolicinaNaStanju=?";
                pom.add(artikal.getKolicinaNaStanju());
            }

            if (null != artikal.getBarkod()) {
                query += " AND Barkod=?";
                pom.add(artikal.getBarkod());
            }

            if (null != artikal.getTip()) {
                query += " AND Tip=?";
                pom.add(artikal.getTip());
            }

            if (null != artikal.getCijena()) {
                query += " AND Cijena=?";
                pom.add(artikal.getCijena());
            }

            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                retVal.add(
                        new Artikal(
                                resultSet.getInt("ArtikalId"),
                                resultSet.getString("Naziv"),
                                resultSet.getInt("KolicinaNaStanju"),
                                resultSet.getString("Barkod"),
                                resultSet.getString("Tip"),
                                resultSet.getDouble("Cijena")
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
    public int insert(Artikal artikal) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                artikal.getNaziv(),
                artikal.getKolicinaNaStanju(),
                artikal.getBarkod(),
                artikal.getTip(),
                artikal.getCijena()
            };
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_INSERT, true, values);
            retVal = preparedStatement.executeUpdate();
            if (0 != retVal) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    artikal.setArtikalId(resultSet.getInt(1));
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
    public int update(Artikal artikal) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                artikal.getNaziv(),
                artikal.getKolicinaNaStanju(),
                artikal.getBarkod(),
                artikal.getTip(),
                artikal.getCijena(),
                artikal.getArtikalId()
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
    public int delete(Integer ArtikalId) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object value = ArtikalId;
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
