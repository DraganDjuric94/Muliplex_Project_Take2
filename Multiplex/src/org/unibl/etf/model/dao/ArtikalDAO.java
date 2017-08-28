/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.Artikal;

/**
 *
 * @author Aleksandar
 */
public interface ArtikalDAO {

    public List<Artikal> selectAll();

    public List<Artikal> selectBy(Artikal artikal);

    public int insert(Artikal sala);

    public int update(Artikal sala);

    public int delete(Integer artikalId);
}
