/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.Faktura;

/**
 *
 * @author Aleksandar
 */
public interface FakturaDAO {

    public List<Faktura> selectAll();

    public List<Faktura> selectBy(Faktura faktura);

    public int insert(Faktura faktura);

    public int update(Faktura faktura);

    public int delete(Integer fakturaId);
}
