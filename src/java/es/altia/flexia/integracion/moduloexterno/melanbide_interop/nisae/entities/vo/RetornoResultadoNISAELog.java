/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.entities.vo;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.entities.InteropLlamadasServiciosNisae;
import java.util.List;

/**
 *
 * @author sergio
 */
public class RetornoResultadoNISAELog {
    
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<InteropLlamadasServiciosNisae> lstRegistros;

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public List<InteropLlamadasServiciosNisae> getLstRegistros() {
        return lstRegistros;
    }

    public void setLstRegistros(List<InteropLlamadasServiciosNisae> lstRegistros) {
        this.lstRegistros = lstRegistros;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
    
    
    
    
}
