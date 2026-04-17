/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.altia.flexia.integracion.moduloexterno.melanbide_interop.util;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.RegistroVidaLaboralVO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.Cabecera;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.Identidad;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.Persona;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.Resumen;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.Situacion;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.tercero.TerceroVO;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    
    public static List<RegistroVidaLaboralVO> mapListaSituacionToListaVidaLaboral(final Identidad identidad, final Persona persona, final String fechaDesde,
            final String fechaHasta) {
        if (identidad != null && identidad.getListaSituaciones() != null
                && identidad.getListaSituaciones().getSituacion() != null
                && !identidad.getListaSituaciones().getSituacion().isEmpty()) {
            final List<RegistroVidaLaboralVO> registros = new ArrayList<RegistroVidaLaboralVO>();
            final List<Situacion> listSituacion = identidad.getListaSituaciones().getSituacion();
            final Cabecera cabecera = identidad.getCabecera();
            final Resumen resumen = identidad.getResumen();
            int i = 0;

            for (final Situacion s : listSituacion) {

                registros.add(new RegistroVidaLaboralVO("", persona.getTipoDocumento(),
                        persona.getNumDocumento(), fromStringToDate(fechaDesde), fromStringToDate(fechaHasta), identidad.getNumeroSituaciones(),
                        cabecera.getListaNumerosAfiliacion().toString(), fromStringToDate(cabecera.getFechaNacimiento()),
                        cabecera.getTransferenciaDerechosCEE(), resumen.getTotales().getTotalDiasAlta() + resumen.getPluriempleo().getTotalDiasAlta(),
                        resumen.getPluriempleo().getTotalDiasAlta(), resumen.getTotales().getAniosAlta() + resumen.getPluriempleo().getAniosAlta(),
                        resumen.getTotales().getMesesAlta() + resumen.getPluriempleo().getMesesAlta(),
                        resumen.getTotales().getDiasAlta() + resumen.getPluriempleo().getDiasAlta(),
                        resumen.getTotales().getTotalDiasAlta(), resumen.getTotales().getAniosAlta(),
                        resumen.getTotales().getMesesAlta(), resumen.getTotales().getDiasAlta(),
                        s.getNumeroAfiliacion(), s.getRegimen(), s.getEmpresa(), s.getCodigoCuentaCotizacion(),
                        s.getProvincia(), fromStringToDate(s.getFechaAlta()),
                        fromStringToDate(s.getFechaEfectos()), fromStringToDate(s.getFechaBaja()),
                        s.getContratoTrabajo(), s.getContratoTrabajo(), s.getContratoTiempoParcial(),
                        s.getGrupoCotizacion(), s.getDiasAlta(), (float) 0, ++i));

            }
            return registros;
        } else {
            return new ArrayList<RegistroVidaLaboralVO>();
        }
    }

    private static java.sql.Date fromStringToDate(final String fecha) {
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            final java.util.Date utilFecha = (fecha != null && !fecha.equals(""))
                    ? formatter.parse(fecha) : null;
            return utilFecha != null ? new java.sql.Date(utilFecha.getTime()) : null;
        } catch (final ParseException ex) {
            return null;
        }
    }    
    
}
