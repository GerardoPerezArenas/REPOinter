/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.altia.flexia.integracion.moduloexterno.melanbide_interop.util;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.tercero.TerceroVO;
import java.sql.ResultSet;

/**
 *
 * @author davidg
 */
public class MeLanbideInteropMappingUtils {
    
    private static MeLanbideInteropMappingUtils instance = null;
    
    private MeLanbideInteropMappingUtils()
    {
        
    }
    
    public static MeLanbideInteropMappingUtils getInstance()
    {
        if(instance == null)
        {
            synchronized(MeLanbideInteropMappingUtils.class)
            {
                instance = new MeLanbideInteropMappingUtils();
            }
        }
        return instance;
    }
    
    public Object map(ResultSet rs, Class clazz) throws Exception
    {
        if(clazz == TerceroVO.class)
        {
            return mapearTercero(rs);
        }
        return null;
    }
    
    private TerceroVO mapearTercero(ResultSet rs) throws Exception
    {
        TerceroVO t = new TerceroVO();
        t.setCodTer(rs.getString("TER_COD"));
        t.setVersionTercero(rs.getString("TER_nvr"));
        t.setTipoDoc(rs.getString("TER_TID"));
        t.setDoc(rs.getString("TER_DOC"));
        t.setNombre(rs.getString("TER_NOM"));        
        t.setApellido1(rs.getString("TER_AP1"));
        t.setApellido2(rs.getString("TER_AP2"));
        t.setNombreCompleto(rs.getString("ter_NOC"));
        t.setTSexoTercero("TSEXOTERCERO");
        t.setTFecNacimiento(rs.getDate("TFECNACIMIENTO"));
        t.setTNacionTercero("TNACIONTERCERO");
        t.setCodRol(rs.getString("TER_CODROL"));
        t.setRol(rs.getString("TER_ROL"));
        t.setCodigoProvinciaDom(rs.getString("DNN_PRV"));
        t.setCodigoMunicipioDom(rs.getString("Dnn_mun"));
        return t;
    }
    
}
