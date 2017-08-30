/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.FilmZanr;

/**
 *
 * @author Aleksandar
 */
public interface FilmZanrDAO {

    public List<FilmZanr> selectAll();

    public List<FilmZanr> selectBy(FilmZanr filmZanr);

    public int insert(FilmZanr filmZanr);

    public int delete(Integer filmId, Integer zanrId);
}
