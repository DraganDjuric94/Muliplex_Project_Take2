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
import org.unibl.etf.model.dao.ZaposleniDAO;
import org.unibl.etf.model.domain.Zaposleni;

/**
 *
 * @author Aleksandar
 */
public class MySQLZaposleniDAO implements ZaposleniDAO {

    private static final String SQL_SELECT = "SELECT * FROM zaposleni";
    private static final String SQL_INSERT = "INSERT INTO zaposleni VALUES (null, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE zaposleni SET Ime=?, Prezime=?, JMBG=?, Aktivan=?, Plata=?, KorisnickoIme=?, Lozinka=? WHERE ZaposleniId=?";
    private static final String SQL_DELETE = "DELETE FROM zaposleni WHERE ZaposleniId=?";

    @Override
    public List<Zaposleni> selectAll() {
        List<Zaposleni> retVal = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()) {
                retVal.add(
                        new Zaposleni(
                                resultSet.getInt("Zaposleniid"),
                                resultSet.getString("Ime"),
                                resultSet.getString("Prezime"),
                                resultSet.getString("JMBG"),
                                resultSet.getBoolean("Aktivan"),
                                resultSet.getDouble("Plata"),
                                resultSet.getString("KorisnickoIme"),
                                resultSet.getString("Lozinka")
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
    public List<Zaposleni> selectBy(Zaposleni zaposleni) {
        List<Zaposleni> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            String query = MySQLZaposleniDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();

            if (null != zaposleni.getZaposleniId()) {
                query += " AND ZaposleniId=?";
                pom.add(zaposleni.getZaposleniId());
            }

            if (null != zaposleni.getIme()) {
                query += " Ime=?";
                pom.add(zaposleni.getIme());
            }

            if (null != zaposleni.getPrezime()) {
                query += " Prezime=?";
                pom.add(zaposleni.getPrezime());
            }

            if (null != zaposleni.getJmbg()) {
                query += " JMBG=?";
                pom.add(zaposleni.getJmbg());
            }

            if (null != zaposleni.getAktivan()) {
                query += " Aktivan=?";
                pom.add(zaposleni.getAktivan());
            }

            if (null != zaposleni.getPlata()) {
                query += " Plata=?";
                pom.add(zaposleni.getPlata());
            }
            
            if (null != zaposleni.getKorisnickoIme()){
                query += " KorisnickoIme=?";
                pom.add(zaposleni.getKorisnickoIme());
            }
            
            if (null != zaposleni.getLozinka()){
                query += " Lozinka=?";
                pom.add(zaposleni.getLozinka());
            }

            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                retVal.add(
                        new Zaposleni(
                                resultSet.getInt("Zaposleniid"),
                                resultSet.getString("Ime"),
                                resultSet.getString("Prezime"),
                                resultSet.getString("JMBG"),
                                resultSet.getBoolean("Aktivan"),
                                resultSet.getDouble("Plata"),
                                resultSet.getString("KorisnickoIme"),
                                resultSet.getString("Lozinka")
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
    public int insert(Zaposleni zaposleni) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                zaposleni.getIme(),
                zaposleni.getPrezime(),
                zaposleni.getJmbg(),
                zaposleni.getAktivan(),
                zaposleni.getPlata(),
                zaposleni.getKorisnickoIme(),
                zaposleni.getLozinka()
            };
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_INSERT, true, values);
            retVal = preparedStatement.executeUpdate();
            if (0 != retVal) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    zaposleni.setZaposleniId(resultSet.getInt(1));
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
    public int update(Zaposleni zaposleni) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                zaposleni.getIme(),
                zaposleni.getPrezime(),
                zaposleni.getJmbg(),
                zaposleni.getAktivan(),
                zaposleni.getPlata(),
                zaposleni.getKorisnickoIme(),
                zaposleni.getLozinka(),
                zaposleni.getZaposleniId()
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
    public int delete(Integer zaposleniId) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object value = zaposleniId;
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
