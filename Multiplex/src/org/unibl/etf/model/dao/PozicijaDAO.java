/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.Pozicija;

/**
 *
 * @author Aleksandar
 */
public interface PozicijaDAO {

    public List<Pozicija> selectAll();

    public List<Pozicija> selectBy(Pozicija pozicija);

    public int insert(Pozicija pozicija);

    public int update(Pozicija pozicija);

    public int delete(Integer pozicijaId);
}
