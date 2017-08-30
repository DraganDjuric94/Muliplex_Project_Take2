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
import org.unibl.etf.model.dao.PozicijaDAO;
import org.unibl.etf.model.domain.Pozicija;

/**
 *
 * @author Aleksandar
 */
public class MySQLPozicijaDAO implements PozicijaDAO {

    private static final String SQL_SELECT = "SELECT * FROM pozicija";
    private static final String SQL_INSERT = "INSERT INTO pozicija VALUES (null, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE pozicija SET Naziv=?, DatumOd=?, DatumDo=? WHERE PozicijaId=?";
    private static final String SQL_DELETE = "DELETE FROM pozicija WHERE PozicijaId=?";

    @Override
    public List<Pozicija> selectAll() {
        List<Pozicija> retVal = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()) {
                retVal.add(
                        new Pozicija(
                                resultSet.getInt("PozicijaId"),
                                resultSet.getString("Naziv"),
                                resultSet.getDate("DatumOd"),
                                resultSet.getDate("DatumDo")
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
    public List<Pozicija> selectBy(Pozicija pozicija) {
        List<Pozicija> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            String query = MySQLPozicijaDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();

            if (null != pozicija.getPozicijaId()) {
                query += " AND PozicijaId=?";
                pom.add(pozicija.getPozicijaId());
            }

            if (null != pozicija.getNaziv()) {
                query += " AND Naziv=?";
                pom.add(pozicija.getNaziv());
            }

            if (null != pozicija.getDatumOd()) {
                query += " AND DatumOd=?";
                pom.add(pozicija.getDatumOd());
            }
            
            if (null != pozicija.getDatumDo()) {
                query += " AND DatumDo=?";
                pom.add(pozicija.getDatumDo());
            }

            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                retVal.add(
                        new Pozicija(
                                resultSet.getInt("PozicijaId"),
                                resultSet.getString("Naziv"),
                                resultSet.getDate("DatumOd"),
                                resultSet.getDate("DatumDo")
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
    public int insert(Pozicija pozicija) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                pozicija.getNaziv(),
                pozicija.getDatumOd(),
                pozicija.getDatumDo()
            };
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_INSERT, true, values);
            retVal = preparedStatement.executeUpdate();
            if (0 != retVal) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    pozicija.setPozicijaId(resultSet.getInt(1));
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
    public int update(Pozicija pozicija) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                pozicija.getNaziv(),
                pozicija.getDatumOd(),
                pozicija.getDatumDo(),
                pozicija.getPozicijaId()
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
    public int delete(Integer pozicijaId) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object value = pozicijaId;
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
