/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package org.unibl.etf.model.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.unibl.etf.model.dao.ArtikalDAO;
import org.unibl.etf.model.dao.DAOAbstractFactory;
import org.unibl.etf.model.dao.FakturaDAO;
import org.unibl.etf.model.dao.FilmDAO;
import org.unibl.etf.model.dao.FilmZanrDAO;
import org.unibl.etf.model.dao.KartaDAO;
import org.unibl.etf.model.dao.OpremaDAO;
import org.unibl.etf.model.dao.PonudaZaFilmDAO;
import org.unibl.etf.model.dao.PozicijaDAO;
import org.unibl.etf.model.dao.ProjekcijaDAO;
import org.unibl.etf.model.dao.ProjekcijaSalaDAO;
import org.unibl.etf.model.dao.RacunDAO;
import org.unibl.etf.model.dao.SalaDAO;
import org.unibl.etf.model.dao.SjedisteDAO;
import org.unibl.etf.model.dao.StavkaDAO;
import org.unibl.etf.model.dao.TransakcijaDAO;
import org.unibl.etf.model.dao.ZanrDAO;
import org.unibl.etf.model.dao.ZaposleniDAO;
import org.unibl.etf.model.dao.ZaposleniPozicijaDAO;

/**
 *
 * @author Aleksandar
 */
public class MySQLDAOFactory implements DAOAbstractFactory {

    private static MySQLDAOFactory obj = new MySQLDAOFactory();
    
    private static String jdbcURL;
    private static String username;
    private static String password;

    private MySQLDAOFactory() {
        
    }

    static {
        ResourceBundle bundle = PropertyResourceBundle
                .getBundle("org.unibl.etf.multiplex.util.db");

        MySQLDAOFactory.jdbcURL = bundle.getString("jdbcURL");
        MySQLDAOFactory.username = bundle.getString("username");
        MySQLDAOFactory.password = bundle.getString("password");

    }

    public static MySQLDAOFactory getInstance() {
        return obj;
    }

    public static PreparedStatement prepareStatement(Connection c, String sql,
            boolean retGenKeys, Object... values) throws SQLException {
        PreparedStatement ps = c.prepareStatement(sql,
                retGenKeys ? Statement.RETURN_GENERATED_KEYS
                        : Statement.NO_GENERATED_KEYS);

        for (int i = 0; i < values.length; i++) {
            ps.setObject(i + 1, values[i]);
        }

        return ps;
    }

    public static Connection getConnection() {
        Connection c = null;

        try {
            c = DriverManager.getConnection(jdbcURL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    public static void close(Connection c) {
        if (c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement s) {
        if (s != null) {
            try {
                s.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs, Statement s, Connection c) {
        close(rs);
        close(s);
        close(c);
    }

    public static void close(Statement s, Connection c) {
        close(s);
        close(c);
    }

    @Override
    public SalaDAO getSalaDAO() {
        return new MySQLSalaDAO();
    }

    @Override
    public ArtikalDAO getArtikalDAO() {
        return new MySQLArtikalDAO();
    }

    @Override
    public OpremaDAO getOpremaDAO() {
        return new MySQLOpremaDAO();
    }

    @Override
    public RacunDAO getRacunDAO() {
        return new MySQLRacunDAO();
    }

    @Override
    public ProjekcijaSalaDAO getProjekcijaSalaDAO() {
        return new MySQLProjekcijaSalaDAO();
    }

    @Override
    public ZaposleniPozicijaDAO getZaposleniPozicijaDAO() {
        return new MySQLZaposleniPozicijaDAO();
    }

    @Override
    public FakturaDAO getFakturaDAO() {
        return new MySQLFakturaDAO();
    }

    @Override
    public FilmDAO getFilmDAO() {
        return new MySQLFilmDAO();
    }

    @Override
    public FilmZanrDAO getFilmZanrDAO() {
        return new MySQLFilmZanrDAO();
    }

    @Override
    public KartaDAO getKartaDAO() {
        return new MySQLKartaDAO();
    }

    @Override
    public PonudaZaFilmDAO getPonudaZaFilmDAO() {
        return new MySQLPonudaZaFilmDAO();
    }

    @Override
    public PozicijaDAO getPozicijaDAO() {
        return new MySQLPozicijaDAO();
    }

    @Override
    public ProjekcijaDAO getProjekcijaDAO() {
        return new MySQLProjekcijaDAO();
    }

    @Override
    public SjedisteDAO getSjedisteDAO() {
        return new MySQLSjedisteDAO();
    }

    @Override
    public StavkaDAO getStavkaDAO() {
        return new MySQLStavkaDAO();
    }

    @Override
    public TransakcijaDAO getTransakcijaDAO() {
        return new MySQLTransakcijaDAO();
    }

    @Override
    public ZanrDAO getZanrDAO() {
        return new MySQLZanrDAO();
    }

    @Override
    public ZaposleniDAO getZaposleniDAO() {
        return new MySQLZaposleniDAO();
    }

}
