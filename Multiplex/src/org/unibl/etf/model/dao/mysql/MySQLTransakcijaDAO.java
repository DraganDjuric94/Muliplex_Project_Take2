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
import org.unibl.etf.model.dao.TransakcijaDAO;
import org.unibl.etf.model.domain.Transakcija;

/**
 *
 * @author Aleksandar
 */
public class MySQLTransakcijaDAO  implements TransakcijaDAO {

    private static final String SQL_SELECT = "SELECT * FROM transakcija";
    private static final String SQL_INSERT = "INSERT INTO transakcija VALUES (null, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE transakcija SET VrstaTransakcije=?, Primalac=?, Posaljilac=?, DatumTransakcije=?, Iznos=? WHERE TransakcijaId=?";
    private static final String SQL_DELETE = "DELETE FROM transakcija WHERE TransakcijaId=?";
    

    @Override
    public List<Transakcija> selectAll() {
       List<Transakcija> retVal = new ArrayList<>();
       Connection connection = null;
       Statement statement = null;
       ResultSet resultSet = null;
       
       try{
           connection = MySQLDAOFactory.getConnection();
           statement = connection.createStatement();
           resultSet = statement.executeQuery(SQL_SELECT);
           while(resultSet.next()){
               retVal.add(
                       new Transakcija(
                               resultSet.getInt("TransakcijaId"), 
                               resultSet.getString("VrstaTransakcije"),
                               resultSet.getString("Primalac"),
                               resultSet.getString("Posaljilac"),
                               resultSet.getDate("DatumTransakcije"),
                               resultSet.getDouble("Iznos")
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
    public List<Transakcija> selectBy(Transakcija transakcija) {
        List<Transakcija> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            connection = MySQLDAOFactory.getConnection();
            String query  = MySQLTransakcijaDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();
            
            if(null != transakcija.getTransakcijaId()){
                query += " AND TransakcijaId=?";
                pom.add(transakcija.getTransakcijaId());
            }
            
            if(null != transakcija.getVrstaTransakcije()){
                query += " AND VrstaTransakcije=?";
                pom.add(transakcija.getVrstaTransakcije());
            }
            
            if(null != transakcija.getPrimalac()){
                query += " AND Primalac=?";
                pom.add(transakcija.getPrimalac());
            }
            
            if(null != transakcija.getPosaljilac()){
                query += " AND Posaljilac=?";
                pom.add(transakcija.getPosaljilac());
            }
            
            if(null != transakcija.getDatumTransakcije()){
                query += " AND DatumTransakcije=?";
                pom.add(transakcija.getDatumTransakcije());
            }
            
            if(null != transakcija.getIznos()){
                query += " AND Iznos=?";
                pom.add(transakcija.getIznos());
            }
            
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                retVal.add(
                                               new Transakcija(
                               resultSet.getInt("TransakcijaId"), 
                               resultSet.getString("VrstaTransakcije"),
                               resultSet.getString("Primalac"),
                               resultSet.getString("Posaljilac"),
                               resultSet.getDate("DatumTransakcije"),
                               resultSet.getDouble("Iznos")
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
    public int insert(Transakcija transakcija){
       int retVal = 0;
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       
       try{
           connection = MySQLDAOFactory.getConnection();
           Object values[] = {
               transakcija.getVrstaTransakcije(),
               transakcija.getPrimalac(),
               transakcija.getPosaljilac(),
               transakcija.getDatumTransakcije(),
               transakcija.getIznos()
           };
           preparedStatement = MySQLDAOFactory.prepareStatement(connection, SQL_INSERT, true, values);
           retVal = preparedStatement.executeUpdate();
           if(0 != retVal){
               resultSet = preparedStatement.getGeneratedKeys();
               if(resultSet.next()){
                   transakcija.setTransakcijaId(resultSet.getInt(1));
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
    public int update(Transakcija transakcija) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            connection = MySQLDAOFactory.getConnection();
            Object values[] = {
               transakcija.getVrstaTransakcije(),
               transakcija.getPrimalac(),
               transakcija.getPosaljilac(),
               transakcija.getDatumTransakcije(),
               transakcija.getIznos(),
               transakcija.getTransakcijaId()
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
    public int delete(Integer transakcijaId) {
        int retVal = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
           connection = MySQLDAOFactory.getConnection();
           Object value = transakcijaId;
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
