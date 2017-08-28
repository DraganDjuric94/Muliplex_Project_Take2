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
import org.unibl.etf.model.dao.FakturaDAO;
import org.unibl.etf.model.domain.Faktura;

/**
 *
 * @author Aleksandar
 */
public class MySQLFakturaDAO implements FakturaDAO {

    private static final String SQL_SELECT = "SELECT * FROM faktura";
    private static final String SQL_INSERT = "INSERT INTO faktura VALUES (null, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE faktura SET BrojRacuna=?, NazivRobe=?, JedinicaMjere=?, Kolicina=?, Cijena=?, Datum=?, RacunIzdao=? WHERE FakturaId=?";
    private static final String SQL_DELETE = "DELETE FROM faktura WHERE FakturaId=?";

    @Override
    public List<Faktura> selectAll() {
        List<Faktura> retVal = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()) {
                retVal.add(
                        new Faktura(
                                resultSet.getInt("FakturaId"),
                                resultSet.getString("BrojRacuna"),
                                resultSet.getString("NazivRobe"),
                                resultSet.getString("JedinicaMjere"),
                                resultSet.getInt("Kolicina"),
                                resultSet.getDouble("Cijena"),
                                resultSet.getDate("Datum"),
                                resultSet.getString("RacunIzdao")
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
    public List<Faktura> selectBy(Faktura faktura) {
        List<Faktura> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            String query = MySQLFakturaDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();

            if (null != faktura.getFakturaId()) {
                query += " AND FakturaId=?";
                pom.add(faktura.getFakturaId());
            }

            if (null != faktura.getBrojRacuna()) {
                query += " AND BrojRacuna=?";
                pom.add(faktura.getBrojRacuna());
            }

            if (null != faktura.getNazivRobe()) {
                query += " AND NazivRobe=?";
                pom.add(faktura.getNazivRobe());
            }

            if (null != faktura.getJedinicaMjere()) {
                query += " AND JedinicaMjere=?";
                pom.add(faktura.getJedinicaMjere());
            }

            if (null != faktura.getKolicina()) {
                query += " AND Kolicina=?";
                pom.add(faktura.getKolicina());
            }

            if (null != faktura.getCijena()) {
                query += " AND Cijena=?";
                pom.add(faktura.getCijena());
            }

            if (null != faktura.getDatum()) {
                query += " AND Datum=?";
                pom.add(faktura.getDatum());
            }

            if (null != faktura.getRacunIzdao()) {
                query += " AND RacunIzdao=?";
                pom.add(faktura.getRacunIzdao());
            }

            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                retVal.add(
                        new Faktura(
                                resultSet.getInt("FakturaId"),
                                resultSet.getString("BrojRacuna"),
                                resultSet.getString("NazivRobe"),
                                resultSet.getString("JedinicaMjere"),
                                resultSet.getInt("Kolicina"),
                                resultSet.getDouble("Cijena"),
                                resultSet.getDate("Datum"),
                                resultSet.getString("RacunIzdao")
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
    public int insert(Faktura faktura) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                faktura.getBrojRacuna(),
                faktura.getNazivRobe(),
                faktura.getJedinicaMjere(),
                faktura.getKolicina(),
                faktura.getCijena(),
                faktura.getDatum(),
                faktura.getRacunIzdao()
            };
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_INSERT, true, values);
            retVal = preparedStatement.executeUpdate();
            if (0 != retVal) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    faktura.setFakturaId(resultSet.getInt(1));
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
    public int update(Faktura faktura) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                faktura.getBrojRacuna(),
                faktura.getNazivRobe(),
                faktura.getJedinicaMjere(),
                faktura.getKolicina(),
                faktura.getCijena(),
                faktura.getDatum(),
                faktura.getRacunIzdao(),
                faktura.getFakturaId()
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
    public int delete(Integer fakturaId) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object value = fakturaId;
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
