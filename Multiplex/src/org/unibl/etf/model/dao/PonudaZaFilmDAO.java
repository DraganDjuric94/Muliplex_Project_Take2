/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.PonudaZaFilm;

/**
 *
 * @author Aleksandar
 */
public interface PonudaZaFilmDAO {

    public List<PonudaZaFilm> selectAll();

    public List<PonudaZaFilm> selectBy(PonudaZaFilm ponudaZaFilm);

    public int insert(PonudaZaFilm ponudaZaFilm);

    public int update(PonudaZaFilm ponudaZaFilm);

    public int delete(Integer PonudaZaFilmId);
}
