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
import org.unibl.etf.model.dao.FilmZanrDAO;
import org.unibl.etf.model.domain.FilmZanr;

/**
 *
 * @author Aleksandar
 */
public class MySQLFilmZanrDAO implements FilmZanrDAO{
    private static final String SQL_SELECT = "SELECT * FROM film_zanr";
    private static final String SQL_INSERT = "INSERT INTO film_zanr VALUES (?, ?)";
    private static final String SQL_DELETE = "DELETE FROM film_zanr WHERE FilmId=? AND ZanrId=?";
    

    @Override
    public List<FilmZanr> selectAll() {
       List<FilmZanr> retVal = new ArrayList<>();
       Connection connection = null;
       Statement statement = null;
       ResultSet resultSet = null;
       
       try{
           connection = MySQLDAOFactory.getConnection();
           statement = connection.createStatement();
           resultSet = statement.executeQuery(SQL_SELECT);
           while(resultSet.next()){
               retVal.add(
                       new FilmZanr(
                               resultSet.getInt("FilmId"),
                               resultSet.getInt("ZanrId")
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
    public List<FilmZanr> selectBy(FilmZanr filmZanr) {
        List<FilmZanr> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            connection = MySQLDAOFactory.getConnection();
            String query  = MySQLFilmZanrDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();
            
            if(null != filmZanr.getFilmId()){
                query += " AND FilmId=?";
                pom.add(filmZanr.getFilmId());
            }
            
            if(null != filmZanr.getZanrId()){
                query += " AND ZanrId=?";
                pom.add(filmZanr.getZanrId());
            }
            
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                retVal.add(
                        new FilmZanr(
                                resultSet.getInt("FilmId"),
                                resultSet.getInt("ZanrId")
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
    public int insert(FilmZanr filmZanr){
       int retVal = 0;
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       
       try{
           connection = MySQLDAOFactory.getConnection();
           Object values[] = {
               filmZanr.getFilmId(),
               filmZanr.getZanrId()
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
    public int delete(Integer filmId, Integer zanrId) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
           connection = MySQLDAOFactory.getConnection();
           Object[] values = {
               filmId,
               zanrId
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
