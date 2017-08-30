/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.Zanr;

/**
 *
 * @author Aleksandar
 */
public interface ZanrDAO {

    public List<Zanr> selectAll();

    public List<Zanr> selectBy(Zanr zanr);

    public int insert(Zanr zanr);

    public int update(Zanr zanr);

    public int delete(Integer zanrId);
}
