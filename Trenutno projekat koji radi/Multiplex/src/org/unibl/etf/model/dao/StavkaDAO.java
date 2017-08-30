/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.Stavka;

/**
 *
 * @author Aleksandar
 */
public interface StavkaDAO {

    public List<Stavka> selectAll();

    public List<Stavka> selectBy(Stavka stavka);

    public int insert(Stavka stavka);

    public int update(Stavka stavka);

    public int delete(Integer stavkaId);
}
