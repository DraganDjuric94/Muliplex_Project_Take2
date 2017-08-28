/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.Racun;

/**
 *
 * @author Aleksandar
 */
public interface RacunDAO {

    public List<Racun> selectAll();

    public List<Racun> selectBy(Racun racun);

    public int insert(Racun racun);

    public int update(Racun racun);

    public int delete(Integer racunId);
}
