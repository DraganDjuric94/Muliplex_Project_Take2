/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.Sala;

/**
 *
 * @author Aleksandar
 */
public interface SalaDAO {

    public List<Sala> selectAll();

    public List<Sala> selectBy(Sala sala);

    public int insert(Sala sala);

    public int update(Sala sala);

    public int delete(Integer salaId);

}
