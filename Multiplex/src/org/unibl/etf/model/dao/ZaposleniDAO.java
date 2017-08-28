/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.Zaposleni;

/**
 *
 * @author Aleksandar
 */
public interface ZaposleniDAO {

    public List<Zaposleni> selectAll();

    public List<Zaposleni> selectBy(Zaposleni zaposleni);

    public int insert(Zaposleni zaposleni);

    public int update(Zaposleni zaposleni);

    public int delete(Integer zaposleniId);
}
