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
import org.unibl.etf.model.dao.StavkaDAO;
import org.unibl.etf.model.domain.Stavka;

/**
 *
 * @author Aleksandar
 */
public class MySQLStavkaDAO implements StavkaDAO {
    private static final String SQL_SELECT = "SELECT * FROM stavka";
    private static final String SQL_INSERT = "INSERT INTO stavka VALUES (null, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE stavka SET ArtikalId=?, RacunId=?, Kolicina=?, UkupnaCijena=? WHERE StavkaId=?";
    private static final String SQL_DELETE = "DELETE FROM stavka WHERE StavkaId=?";
    

    @Override
    public List<Stavka> selectAll() {
       List<Stavka> retVal = new ArrayList<>();
       Connection connection = null;
       Statement statement = null;
       ResultSet resultSet = null;
       
       try{
           connection = MySQLDAOFactory.getConnection();
           statement = connection.createStatement();
           resultSet = statement.executeQuery(SQL_SELECT);
           while(resultSet.next()){
               retVal.add(
                       new Stavka(
                               resultSet.getInt("StavkaId"),
                               resultSet.getInt("ArtikalId"),
                               resultSet.getInt("RacunId"),
                               resultSet.getInt("Kolicina"),
                               resultSet.getDouble("UkupnaCijena")
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
    public List<Stavka> selectBy(Stavka stavka) {
        List<Stavka> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            connection = MySQLDAOFactory.getConnection();
            String query  = MySQLStavkaDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();
            
            if(null != stavka.getStavkaId()){
                query += " AND StavkaId=?";
                pom.add(stavka.getStavkaId());
            }
            
            if(null != stavka.getArtikalId()){
                query += " AND ArtikalId=?";
                pom.add(stavka.getArtikalId());
            }
            
            if(null != stavka.getRacunId()){
                query += " AND RacunId=?";
                pom.add(stavka.getRacunId());
            }
            
            if(null != stavka.getKolicina()){
                query += " AND Kolicina=?";
                pom.add(stavka.getKolicina());
            }
            
            if(null != stavka.getUkupnaCijena()){
                query += " AND UkupnaCijena=?";
                pom.add(stavka.getUkupnaCijena());
            }
            
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                retVal.add(
                         new Stavka(
                               resultSet.getInt("StavkaId"),
                               resultSet.getInt("ArtikalId"),
                               resultSet.getInt("RacunId"),
                               resultSet.getInt("Kolicina"),
                               resultSet.getDouble("UkupnaCijena")
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
    public int insert(Stavka stavka){
       int retVal = 0;
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       
       try{
           connection = MySQLDAOFactory.getConnection();
           Object values[] = {
               stavka.getArtikalId(),
               stavka.getRacunId(),
               stavka.getKolicina(),
               stavka.getUkupnaCijena()
           };
           preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_INSERT, true, values);
           retVal = preparedStatement.executeUpdate();
           if(0 != retVal){
               resultSet = preparedStatement.getGeneratedKeys();
               if(resultSet.next()){
                   stavka.setStavkaId(resultSet.getInt(1));
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
    public int update(Stavka stavka) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
                stavka.getArtikalId(),
                stavka.getRacunId(),
                stavka.getKolicina(),
                stavka.getUkupnaCijena(),
                stavka.getStavkaId()
                
            };
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
    public int delete(Integer stavkaId) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
           connection = MySQLDAOFactory.getConnection();
           Object value = stavkaId;
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
