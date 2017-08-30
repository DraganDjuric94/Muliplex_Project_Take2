/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.Transakcija;

/**
 *
 * @author Aleksandar
 */
public interface TransakcijaDAO {

    public List<Transakcija> selectAll();

    public List<Transakcija> selectBy(Transakcija sala);

    public int insert(Transakcija transakcija);

    public int update(Transakcija transakcija);

    public int delete(Integer transakcijaId);
}
