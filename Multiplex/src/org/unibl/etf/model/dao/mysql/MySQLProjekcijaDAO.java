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
import org.unibl.etf.model.dao.ProjekcijaDAO;
import org.unibl.etf.model.domain.Projekcija;

/**
 *
 * @author Aleksandar
 */
public class MySQLProjekcijaDAO implements ProjekcijaDAO {

    private static final String SQL_SELECT = "SELECT * FROM projekcija";
    private static final String SQL_INSERT = "INSERT INTO projekcija VALUES (null, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE projekcija SET FilmId=?, DatumVrijeme=? WHERE ProjekcijaId=?";
    private static final String SQL_DELETE = "DELETE FROM projekcija WHERE ProjekcijaId=?";

    @Override
    public List<Projekcija> selectAll() {
        List<Projekcija> retVal = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()) {
                retVal.add(
                        new Projekcija(
                                resultSet.getInt("ProjekcijaId"),
                                resultSet.getInt("FilmId"),
                                resultSet.getTimestamp("DatumVrijeme")
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
    public List<Projekcija> selectBy(Projekcija projekcija) {
        List<Projekcija> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            String query = MySQLProjekcijaDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();

            if (null != projekcija.getProjekcijaId()) {
                query += " AND ProjekcijaId=?";
                pom.add(projekcija.getProjekcijaId());
            }

            if (null != projekcija.getFilmId()) {
                query += " AND FilmId=?";
                pom.add(projekcija.getFilmId());
            }

            if (null != projekcija.getDatumVrijeme()) {
                query += " AND DatumVrijeme=?";
                pom.add(projekcija.getDatumVrijeme());
            }

            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                retVal.add(
                        new Projekcija(
                                resultSet.getInt("ProjekcijaId"),
                                resultSet.getInt("FilmId"),
                                resultSet.getTimestamp("DatumVrijeme")
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
    public int insert(Projekcija projekcija) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                projekcija.getFilmId(),
                projekcija.getDatumVrijeme(),
            };
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_INSERT, true, values);
            retVal = preparedStatement.executeUpdate();
            if (0 != retVal) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    projekcija.setProjekcijaId(resultSet.getInt(1));
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
    public int update(Projekcija projekcija) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                projekcija.getFilmId(),
                projekcija.getDatumVrijeme(),
                projekcija.getProjekcijaId()
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
    public int delete(Integer projekcijaId) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object value = projekcijaId;
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
