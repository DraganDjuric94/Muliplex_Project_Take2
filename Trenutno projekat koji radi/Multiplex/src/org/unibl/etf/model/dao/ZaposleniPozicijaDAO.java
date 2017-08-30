/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.model.dao;

import java.util.List;
import org.unibl.etf.model.domain.ZaposleniPozicija;

/**
 *
 * @author Aleksandar
 */
public interface ZaposleniPozicijaDAO {
    public List<ZaposleniPozicija> selectAll();

    public List<ZaposleniPozicija> selectBy(ZaposleniPozicija zaposleniPozicija);

    public int insert(ZaposleniPozicija zaposleniPozicija);

    public int delete(Integer zaposleniId, Integer pozicijaId);
}
