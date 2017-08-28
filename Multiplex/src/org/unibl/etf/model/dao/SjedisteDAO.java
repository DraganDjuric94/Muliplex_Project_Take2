/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.Sjediste;

/**
 *
 * @author Aleksandar
 */
public interface SjedisteDAO {

    public List<Sjediste> selectAll();

    public List<Sjediste> selectBy(Sjediste sjediste);

    public int insert(Sjediste sjediste);

    public int update(Sjediste sjediste);

    public int delete(Integer sjedisteId);
}
