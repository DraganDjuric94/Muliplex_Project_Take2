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
import org.unibl.etf.model.dao.ProjekcijaSalaDAO;
import org.unibl.etf.model.domain.ProjekcijaSala;

/**
 *
 * @author Aleksandar
 */
public class MySQLProjekcijaSalaDAO implements ProjekcijaSalaDAO{
    
    private static final String SQL_SELECT = "SELECT * FROM projekcija_sala";
    private static final String SQL_INSERT = "INSERT INTO projekcija_sala VALUES (?, ?)";
    private static final String SQL_DELETE = "DELETE FROM projekcija_sala WHERE ProjekcijaId=? AND SalaId=?";
    

    @Override
    public List<ProjekcijaSala> selectAll() {
       List<ProjekcijaSala> retVal = new ArrayList<>();
       Connection connection = null;
       Statement statement = null;
       ResultSet resultSet = null;
       
       try{
           connection = MySQLDAOFactory.getConnection();
           statement = connection.createStatement();
           resultSet = statement.executeQuery(SQL_SELECT);
           while(resultSet.next()){
               retVal.add(
                       new ProjekcijaSala(
                               resultSet.getInt("ProjekcijaId"),
                               resultSet.getInt("SalaId")
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
    public List<ProjekcijaSala> selectBy(ProjekcijaSala projekcijaSala) {
        List<ProjekcijaSala> retVal = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            connection = MySQLDAOFactory.getConnection();
            String query  = MySQLProjekcijaSalaDAO.SQL_SELECT + " WHERE true";
            List<Object> pom = new ArrayList<>();
            
            if(null != projekcijaSala.getProjekcijaId()){
                query += " AND ProjekcijaId=?";
                pom.add(projekcijaSala.getProjekcijaId());
            }
            
            if(null != projekcijaSala.getSalaId()){
                query += " AND SalaId=?";
                pom.add(projekcijaSala.getSalaId());
            }
            
            preparedStatement = MySQLDAOFactory.prepareStatement(connection, query, false, pom.toArray());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                retVal.add(
                        new ProjekcijaSala(
                                resultSet.getInt("ProjekcijaId"),
                                resultSet.getInt("SalaId")
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
    public int insert(ProjekcijaSala projekcijaSala){
       int retVal = 0;
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       
       try{
           connection = MySQLDAOFactory.getConnection();
           Object values[] = {
               projekcijaSala.getProjekcijaId(),
               projekcijaSala.getSalaId()
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
