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
import org.unibl.etf.model.dao.FilmDAO;
import org.unibl.etf.model.domain.Film;

/**
 *
 * @author Aleksandar
 */
public class MySQLFilmDAO implements FilmDAO {

    private static final String SQL_SELECT = "SELECT * FROM film";
    private static final String SQL_INSERT = "INSERT INTO film VALUES (null, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE film SET Naziv=?, Trajanje=?, Opis=?, Slika=? WHERE FilmId=?";
    private static final String SQL_DELETE = "DELETE FROM film WHERE FilmId=?";

    @Override
    public List<Film> selectAll() {
        List<Film> retVal = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()) {
                retVal.add(
                        new Film(
                                resultSet.getInt("FilmId"),
                                resultSet.getString("Naziv"),
                                resultSet.getInt("Trajanje"),
                                resultSet.getString("Opis"),
                                resultSet.getString("Slika")
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
    public List<Film> selectBy(Film film) {
        List<Film> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            String query = MySQLFilmDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();

            if (null != film.getFilmId()) {
                query += " AND FilmId=?";
                pom.add(film.getFilmId());
            }

            if (null != film.getNaziv()) {
                query += " AND Naziv=?";
                pom.add(film.getNaziv());
            }

            if (null != film.getTrajanje()) {
                query += " AND Trajanje=?";
                pom.add(film.getTrajanje());
            }

            if (null != film.getOpis()) {
                query += " AND Opis=?";
                pom.add(film.getOpis());
            }

            if (null != film.getSlika()) {
                query += " AND Slika=?";
                pom.add(film.getSlika());
            }

            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                retVal.add(
                        new Film(
                                resultSet.getInt("FilmId"),
                                resultSet.getString("Naziv"),
                                resultSet.getInt("Trajanje"),
                                resultSet.getString("Opis"),
                                resultSet.getString("Slika")
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
    public int insert(Film film) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                film.getNaziv(),
                film.getTrajanje(),
                film.getOpis(),
                film.getSlika()
            };
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_INSERT, true, values);
            retVal = preparedStatement.executeUpdate();
            if (0 != retVal) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    film.setFilmId(resultSet.getInt(1));
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
    public int update(Film film) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                film.getNaziv(),
                film.getTrajanje(),
                film.getOpis(),
                film.getSlika(),
                film.getFilmId()
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
    public int delete(Integer filmId) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object value = filmId;
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
