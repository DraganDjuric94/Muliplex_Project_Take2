/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.Projekcija;

/**
 *
 * @author Aleksandar
 */
public interface ProjekcijaDAO {

    public List<Projekcija> selectAll();

    public List<Projekcija> selectBy(Projekcija projekcija);

    public int insert(Projekcija projekcija);

    public int update(Projekcija projekcija);

    public int delete(Integer projekcijaId);
}
