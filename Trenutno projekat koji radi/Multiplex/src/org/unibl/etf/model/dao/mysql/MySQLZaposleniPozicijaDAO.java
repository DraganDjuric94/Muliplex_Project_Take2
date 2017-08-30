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
import org.unibl.etf.model.dao.ZaposleniPozicijaDAO;
import org.unibl.etf.model.domain.ZaposleniPozicija;

/**
 *
 * @author Aleksandar
 */
public class MySQLZaposleniPozicijaDAO implements ZaposleniPozicijaDAO{
     private static final String SQL_SELECT = "SELECT * FROM zaposleni_pozicija";
    private static final String SQL_INSERT = "INSERT INTO zaposleni_pozicija VALUES (?, ?)";
    private static final String SQL_DELETE = "DELETE FROM zaposleni_pozicija WHERE ZaposleniId=? AND PozicijaId=?";
    

    @Override
    public List<ZaposleniPozicija> selectAll() {
       List<ZaposleniPozicija> retVal = new ArrayList<>();
       Connection connection = null;
       Statement statement = null;
       ResultSet resultSet = null;
       
       try{
           connection = MySQLDAOFactory.getConnection();
           statement = connection.createStatement();
           resultSet = statement.executeQuery(SQL_SELECT);
           while(resultSet.next()){
               retVal.add(
                       new ZaposleniPozicija(
                               resultSet.getInt("ZaposleniId"),
                               resultSet.getInt("PozicijaId")
                       )
               );
           }
       } catch (SQLException ex) {
            Logger.getLogger(MySQLSalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       finally{
           MySQLDAOFactory.close(resultSet, statement, connection);
       }
       return retVal;
    }

    @Override
    public List<ZaposleniPozicija> selectBy(ZaposleniPozicija zaposleniPozicija) {
        List<ZaposleniPozicija> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            connection = MySQLDAOFactory.getConnection();
            String query  = MySQLZaposleniPozicijaDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();
            
            if(null != zaposleniPozicija.getZaposleniId()){
                query += " AND ZaposleniId=?";
                pom.add(zaposleniPozicija.getZaposleniId());
            }
            
            if(null != zaposleniPozicija.getPozicijaId()){
                query += " AND PozicijaId=?";
                pom.add(zaposleniPozicija.getPozicijaId());
            }
            
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                retVal.add(
                        new ZaposleniPozicija(
                                resultSet.getInt("ZaposleniId"),
                                resultSet.getInt("PozicijaId")
                        )
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            MySQLDAOFactory.close(resultSet, preparedStatement, connection);
        }
        
        return retVal;
    }

    @Override
    public int insert(ZaposleniPozicija zaposleniPozicija){
       int retVal = 0;
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       
       try{
           connection = MySQLDAOFactory.getConnection();
           Object values[] = {
               zaposleniPozicija.getZaposleniId(),
               zaposleniPozicija.getPozicijaId()
           };
           preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_INSERT, true, values);
           retVal = preparedStatement.executeUpdate();
       } catch (SQLException ex) {
            Logger.getLogger(MySQLSalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           MySQLDAOFactory.close(resultSet, preparedStatement, connection);
       }
       return retVal;
    }

    @Override
    public int delete(Integer projekcijaId, Integer salaId) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
           connection = MySQLDAOFactory.getConnection();
           Object[] values = {
               projekcijaId,
               salaId
           };
           preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_DELETE, false, values);
           retVal = preparedStatement.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            MySQLDAOFactory.close(preparedStatement, connection);
        }
        return retVal;
    }
}
