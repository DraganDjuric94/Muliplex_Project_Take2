/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.Film;

/**
 *
 * @author Aleksandar
 */
public interface FilmDAO {

    public List<Film> selectAll();

    public List<Film> selectBy(Film film);

    public int insert(Film film);

    public int update(Film film);

    public int delete(Integer filmId);
}
