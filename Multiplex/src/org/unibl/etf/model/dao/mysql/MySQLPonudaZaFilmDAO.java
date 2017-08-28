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
import org.unibl.etf.model.dao.PonudaZaFilmDAO;
import org.unibl.etf.model.domain.PonudaZaFilm;

/**
 *
 * @author Aleksandar
 */
public class MySQLPonudaZaFilmDAO implements PonudaZaFilmDAO {

    private static final String SQL_SELECT = "SELECT * FROM ponuda_za_film";
    private static final String SQL_INSERT = "INSERT INTO ponuda_za_film VALUES (null, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE ponuda_za_film SET FilmId=?, Opis=?, Datum=? WHERE PonudaZaFilmId=?";
    private static final String SQL_DELETE = "DELETE FROM ponuda_za_film WHERE PonudaZaFilmId=?";

    @Override
    public List<PonudaZaFilm> selectAll() {
        List<PonudaZaFilm> retVal = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()) {
                retVal.add(
                        new PonudaZaFilm(
                                resultSet.getInt("PonudaZaFilmId"),
                                resultSet.getInt("FilmId"),
                                resultSet.getString("Opis"),
                                resultSet.getDate("Datum")
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
    public List<PonudaZaFilm> selectBy(PonudaZaFilm ponudaZaFilm) {
        List<PonudaZaFilm> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            String query = MySQLPonudaZaFilmDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();

            if (null != ponudaZaFilm.getPonudaZaFilmId()) {
                query += " AND PonudaZaFilmId=?";
                pom.add(ponudaZaFilm.getPonudaZaFilmId());
            }

            if (null != ponudaZaFilm.getFilmId()) {
                query += " AND FilmId=?";
                pom.add(ponudaZaFilm.getFilmId());
            }

            if (null != ponudaZaFilm.getOpis()) {
                query += " AND Opis=?";
                pom.add(ponudaZaFilm.getOpis());
            }

            if (null != ponudaZaFilm.getDatum()) {
                query += " AND Datum=?";
                pom.add(ponudaZaFilm.getDatum());
            }

            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                retVal.add(
                        new PonudaZaFilm(
                                resultSet.getInt("PonudaZaFilmId"),
                                resultSet.getInt("FilmId"),
                                resultSet.getString("Opis"),
                                resultSet.getDate("Datum")
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
    public int insert(PonudaZaFilm ponudaZaFilm) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                ponudaZaFilm.getFilmId(),
                ponudaZaFilm.getOpis(),
                ponudaZaFilm.getDatum()
            };
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_INSERT, true, values);
            retVal = preparedStatement.executeUpdate();
            if (0 != retVal) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    ponudaZaFilm.setPonudaZaFilmId(resultSet.getInt(1));
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
    public int update(PonudaZaFilm ponudaZaFilm) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                ponudaZaFilm.getFilmId(),
                ponudaZaFilm.getOpis(),
                ponudaZaFilm.getDatum(),
                ponudaZaFilm.getPonudaZaFilmId()
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
    public int delete(Integer ponudaZaFilmId) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object value = ponudaZaFilmId;
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
