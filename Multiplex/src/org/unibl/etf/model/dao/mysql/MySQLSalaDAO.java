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
import org.unibl.etf.model.dao.SalaDAO;
import org.unibl.etf.model.domain.Sala;

/**
 *
 * @author Aleksandar
 */
public class MySQLSalaDAO implements SalaDAO {
    
    private static final String SQL_SELECT = "SELECT * FROM sala";
    private static final String SQL_INSERT = "INSERT INTO sala VALUES (null, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE sala SET BrojRedova=?, BrojKolona=? WHERE SalaId=?";
    private static final String SQL_DELETE = "DELETE FROM sala WHERE SalaId=?";
    

    @Override
    public List<Sala> selectAll() {
       List<Sala> retVal = new ArrayList<>();
       Connection connection = null;
       Statement statement = null;
       ResultSet resultSet = null;
       
       try{
           connection = MySQLDAOFactory.getConnection();
           statement = connection.createStatement();
           resultSet = statement.executeQuery(SQL_SELECT);
           while(resultSet.next()){
               retVal.add(
                       new Sala(resultSet.getInt("SalaId"), 
                       resultSet.getInt("BrojRedova"),
                       resultSet.getInt("BrojKolona"))
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
    public List<Sala> selectBy(Sala sala) {
        List<Sala> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            connection = MySQLDAOFactory.getConnection();
            String query  = MySQLSalaDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();
            
            if(null != sala.getSalaId()){
                query += " AND SalaId=?";
                pom.add(sala.getSalaId());
            }
            
            if(null != sala.getBrojRedova()){
                query += " AND BrojRedova=?";
                pom.add(sala.getBrojRedova());
            }
            
            if(null != sala.getBrojKolona()){
                query += " AND BrojKolona=?";
                pom.add(sala.getBrojKolona());
            }
            
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                retVal.add(
                        new Sala(resultSet.getInt("SalaId"), 
                                resultSet.getInt("BrojRedova"),
                                resultSet.getInt("BrojKolona"))
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
    public int insert(Sala sala){
       int retVal = 0;
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       
       try{
           connection = MySQLDAOFactory.getConnection();
           Object values[] = {sala.getBrojRedova(), sala.getBrojKolona()};
           preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_INSERT, true, values);
           retVal = preparedStatement.executeUpdate();
           if(0 != retVal){
               resultSet = preparedStatement.getGeneratedKeys();
               if(resultSet.next()){
                   sala.setSalaId(resultSet.getInt(1));
               }
           }
           
       } catch (SQLException ex) {
            Logger.getLogger(MySQLSalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           MySQLDAOFactory.close(resultSet, preparedStatement, connection);
       }
       return retVal;
    }

    @Override
    public int update(Sala sala) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {sala.getBrojRedova(), sala.getBrojKolona(), sala.getSalaId()};
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_UPDATE, false, values);
            retVal = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            MySQLDAOFactory.close(resultSet, preparedStatement, connection);
        }
        return retVal;
    }

    @Override
    public int delete(Integer salaId) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
           connection = MySQLDAOFactory.getConnection();
           Object value = salaId;
           preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_DELETE, false, value);
           retVal = preparedStatement.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            MySQLDAOFactory.close(preparedStatement, connection);
        }
        return retVal;
    }
    
    
    
    
    
}
