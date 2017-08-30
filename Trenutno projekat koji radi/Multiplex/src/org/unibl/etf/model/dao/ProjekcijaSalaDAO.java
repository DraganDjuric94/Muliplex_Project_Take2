/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.ProjekcijaSala;

/**
 *
 * @author Aleksandar
 */
public interface ProjekcijaSalaDAO {

    public List<ProjekcijaSala> selectAll();

    public List<ProjekcijaSala> selectBy(ProjekcijaSala projekcijaSala);

    public int insert(ProjekcijaSala projekcijaSala);

    public int delete(Integer projekcijaId, Integer salaId);
}
