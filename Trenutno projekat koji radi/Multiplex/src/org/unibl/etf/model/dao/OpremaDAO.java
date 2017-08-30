/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.Oprema;

/**
 *
 * @author Aleksandar
 */
public interface OpremaDAO {

    public List<Oprema> selectAll();

    public List<Oprema> selectBy(Oprema oprema);

    public int insert(Oprema oprema);

    public int update(Oprema oprema);

    public int delete(Integer opremaId);
}
