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
import org.unibl.etf.model.dao.KartaDAO;
import org.unibl.etf.model.domain.Karta;

/**
 *
 * @author Aleksandar
 */
public class MySQLKartaDAO implements KartaDAO {

    private static final String SQL_SELECT = "SELECT * FROM karta";
    private static final String SQL_INSERT = "INSERT INTO karta VALUES (null, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE karta SET ProjekcijaId=?, SjedisteId=?, DatumVrijeme=?, Cijena=?, Rezervisana=? WHERE KartaId=?";
    private static final String SQL_DELETE = "DELETE FROM karta WHERE KartaId=?";

    @Override
    public List<Karta> selectAll() {
        List<Karta> retVal = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()) {
                retVal.add(
                        new Karta(
                                resultSet.getInt("KartaId"),
                                resultSet.getInt("ProjekcijaId"),
                                resultSet.getInt("SjedisteId"),
                                resultSet.getTimestamp("DatumVrijeme"),
                                resultSet.getDouble("Cijena"),
                                resultSet.getBoolean("Rezervisana")
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
    public List<Karta> selectBy(Karta karta) {
        List<Karta> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            String query = MySQLKartaDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();

            if (null != karta.getKartaId()) {
                query += " AND KartaId=?";
                pom.add(karta.getKartaId());
            }

            if (null != karta.getProjekcijaId()) {
                query += " AND ProjekcijaId=?";
                pom.add(karta.getProjekcijaId());
            }

            if (null != karta.getSjedisteId()) {
                query += " AND SjedisteId=?";
                pom.add(karta.getSjedisteId());
            }

            if (null != karta.getDatumVrijeme()) {
                query += " AND DatumVrijeme=?";
                pom.add(karta.getDatumVrijeme());
            }

            if (null != karta.getCijena()) {
                query += " AND Cijena=?";
                pom.add(karta.getCijena());
            }

            if (null != karta.getRezervisana()) {
                query += " AND Rezervisana=?";
                pom.add(karta.getRezervisana());
            }

            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                retVal.add(
                        new Karta(
                                resultSet.getInt("KartaId"),
                                resultSet.getInt("ProjekcijaId"),
                                resultSet.getInt("SjedisteId"),
                                resultSet.getTimestamp("DatumVrijeme"),
                                resultSet.getDouble("Cijena"),
                                resultSet.getBoolean("Rezervisana")
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
    public int insert(Karta karta) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                karta.getProjekcijaId(),
                karta.getSjedisteId(),
                karta.getDatumVrijeme(),
                karta.getCijena(),
                karta.getRezervisana()
            };
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_INSERT, true, values);
            retVal = preparedStatement.executeUpdate();
            if (0 != retVal) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    karta.setKartaId(resultSet.getInt(1));
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
    public int update(Karta karta) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                karta.getProjekcijaId(),
                karta.getSjedisteId(),
                karta.getDatumVrijeme(),
                karta.getCijena(),
                karta.getRezervisana(),
                karta.getKartaId()
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
    public int delete(Integer kartaId) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.getConnection();
            Object value = kartaId;
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
