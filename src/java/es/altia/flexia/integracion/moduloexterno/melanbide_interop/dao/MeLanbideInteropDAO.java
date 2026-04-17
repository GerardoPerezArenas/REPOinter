/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.altia.flexia.integracion.moduloexterno.melanbide_interop.dao;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConfigurationParameter;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConstantesMeLanbideInterop;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.MeLanbideInteropMappingUtils;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.tercero.TerceroVO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.URL_WebServiceVO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author davidg
 */
public class MeLanbideInteropDAO {
    
    
    //Logger
    private static Logger log = Logger.getLogger(MeLanbideInteropDAO.class);
    
    //Instancia
    private static MeLanbideInteropDAO instance = null;
    
    private MeLanbideInteropDAO()
    {   
        
    }
    
    public static MeLanbideInteropDAO getInstance()
    {
        if(instance == null)
        {
            synchronized(MeLanbideInteropDAO.class)
            {
                instance = new MeLanbideInteropDAO();
            }
        }
        return instance;
    }     
    
    
     // Metodos de Recuperacion de datos
    
    public List<TerceroVO> getDatosTercerosxExpediente(int codOrganizacion, String numExpediente, Connection con) throws Exception
    {
        Statement st = null;
        ResultSet rs = null;
        List<TerceroVO> listaTerceros  = new ArrayList<TerceroVO>();
        TerceroVO tercero  = new TerceroVO();
        try
        {
            String query = null;
            query = "SELECT T_HTE.HTE_TER TER_COD,T_HTE.HTE_nvr TER_nvr, HTE_TID TER_TID, HTE_DOC TER_DOC, HTE_NOM TER_NOM, HTE_AP1 TER_AP1, HTE_AP2 TER_AP2,HTE_NOC ter_NOC , '' AS NUM_SOPORTE " +
                    "       , TSEXOTERCERO.VALOR TSEXOTERCERO , TFECNACIMIENTO.VALOR TFECNACIMIENTO , TNACIONTERCERO.VALOR TNACIONTERCERO " +
                    "       , E_EXT.EXT_ROL TER_CODROL,  E_ROL.ROL_DES as TER_ROL , E_EXT.EXT_NUM  "
                    + " ,LPAD(T_DNN.DNN_PRV,2,0) DNN_PRV,LPAD(T_DNN.Dnn_mun,3,0) Dnn_mun "
                    + " FROM " + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_RELACION_TERCEROS_EXPEDIENTES, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES)
                    + " LEFT JOIN " + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_HISTORICO_TERCEROS, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES) + "  ON T_HTE.HTE_TER=E_EXT.EXT_TER AND T_HTE.HTE_NVR=E_EXT.EXT_NVR " 
                    + " LEFT JOIN " + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_TERCERO_CAMP_SUPLE_DESPLEGABLES, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES)+ " TSEXOTERCERO ON TSEXOTERCERO.COD_MUNICIPIO=E_EXT.EXT_MUN AND TSEXOTERCERO.COD_TERCERO=T_HTE.HTE_TER AND TSEXOTERCERO.COD_CAMPO='" + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CODIGO_CAMPO_SUPL_TERCERO_SEXO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES) + "'" 
                    + " LEFT JOIN " + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_TERCERO_CAMP_SUPLE_FECHAS, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES)+ " TFECNACIMIENTO ON TFECNACIMIENTO.COD_MUNICIPIO=E_EXT.EXT_MUN AND TFECNACIMIENTO.COD_TERCERO=T_HTE.HTE_TER AND TFECNACIMIENTO.COD_CAMPO='" + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CODIGO_CAMPO_SUPL_TERCERO_FECHANACIMIENTO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES) + "'" 
                    + " LEFT JOIN " + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_TERCERO_CAMP_SUPLE_DESPLEGABLES, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES)+ " TNACIONTERCERO ON TNACIONTERCERO.COD_MUNICIPIO=E_EXT.EXT_MUN AND TNACIONTERCERO.COD_TERCERO=T_HTE.HTE_TER AND TNACIONTERCERO.COD_CAMPO='" + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CODIGO_CAMPO_SUPL_TERCERO_NACIONALIDAD, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES) + "'" 
                    + " LEFT JOIN " + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_ROLES_PROCEDIMIENTO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES) + " ON E_ROL.ROL_MUN=E_EXT.EXT_MUN AND E_ROL.ROL_PRO=E_EXT.EXT_PRO AND E_ROL.ROL_COD=E_EXT.EXT_ROL  "
                    + " LEFT JOIN T_dnn ON T_DNN.DNN_dom=e_ext.ext_dot " 
                    + " WHERE E_EXT.EXT_NUM='" + numExpediente + "' AND EXT_MUN=" + codOrganizacion +" ORDER BY ter_NOC"
                    ;
            
            if(log.isDebugEnabled()) 
                log.debug("sql = " + query);
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next())
            {
                tercero = (TerceroVO)MeLanbideInteropMappingUtils.getInstance().map(rs, TerceroVO.class);
                listaTerceros.add(tercero);
                tercero = new TerceroVO();
            }
        }
        catch(Exception ex)
        {
            log.error("Se ha producido un error recuperando Terceros x Expediente - interoperabilidad ", ex);
            throw new Exception(ex);
        }
        finally
        {
            try
            {
                if(log.isDebugEnabled()) 
                    log.debug("Procedemos a cerrar el statement y el resultset");
                if(st!=null) 
                    st.close();
                if(rs!=null) 
                    rs.close();
            }
            catch(Exception e)
            {
                log.error("Se ha producido un error cerrando el statement y el resulset", e);
                throw new Exception(e);
            }
        }
        return listaTerceros;
    }

	// Metodos de Recuperacion de datos
    
    public URL_WebServiceVO getDatosWebService(String codWebService, Connection con) throws Exception
    {
        Statement st = null;
        ResultSet rs = null;
        URL_WebServiceVO webServiceDatos  = new URL_WebServiceVO();
        try
        {
            String query = null;
            query = "SELECT * "
                    + " FROM " + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_MELANBIDE_INTEROP_URLWS, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES)
                    + " WHERE URLWS_CODWS='" + codWebService + "'";
                    // solo en altia ojoooooo PARA PROBAR LANGAI DEMAN //
                    //if(codWebService=="LANB_DLANDEM")
                    //    query += " AND URLWS_ENTORNO='PRU'";
                    //else
                    //    query += " AND URLWS_ENTORNO='DES'";
                    //
                    
            if(log.isDebugEnabled()) 
                log.debug("sql = " + query);
            st = con.createStatement();
            rs = st.executeQuery(query);
            if(rs.next())
            {
                webServiceDatos.setEntornoDespliege(rs.getString("URLWS_ENTORNO"));
                webServiceDatos.setCodigoWebService(rs.getString("URLWS_CODWS"));
                webServiceDatos.setNombreWebService(rs.getString("URLWS_NOMBREWS"));
                webServiceDatos.setUrlWebService(rs.getString("URLWS_URLWS"));
            }
        }
        catch(Exception ex)
        {
            log.error("Se ha producido un error recuperando datos del Webservice - Interoperabilidad  - :  " + codWebService, ex);
            throw new Exception(ex);
        }
        finally
        {
            try
            {
                if(log.isDebugEnabled()) 
                    log.debug("Procedemos a cerrar el statement y el resultset");
                if(st!=null) 
                    st.close();
                if(rs!=null) 
                    rs.close();
            }
            catch(Exception e)
            {
                log.error("Se ha producido un error cerrando el statement y el resulset", e);
                throw new Exception(e);
            }
        }
        return webServiceDatos;
    }  

    public String getDescripcionProcedimiento(int codOrganizacion, String codProcExpte, Connection con) throws Exception {
        log.debug("getDescripcionProcedimiento DAO - BEGIN");
        PreparedStatement  pt = null;
        ResultSet rs = null;
        String descripcion = "";
        try {
            String query ="SELECT PRO_DES FROM E_PRO WHERE PRO_COD=?";
            log.error("sql = " + query);
            pt = con.prepareStatement(query);
            pt.setString(1,codProcExpte);
            log.error("Parametros ? : " + codProcExpte);
            rs = pt.executeQuery();
            while (rs.next()) {
                descripcion=rs.getString("PRO_DES");
            }
        } catch (Exception ex) {
            log.error("Se ha producido un error recuperando Descripcopm Procedimiento - interoperabilidad ", ex);
            throw new Exception(ex);
        } finally {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("Procedemos a cerrar el prepararedstatement y el resultset");
                }
                if (pt != null) {
                    pt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                log.error("Se ha producido un error cerrando el preparedstatement y el resulset", e);
                throw new Exception(e);
            }
        }
        log.debug("getDescripcionProcedimiento DAO - return " + descripcion);
        log.debug("getDescripcionProcedimiento DAO - END");
        return descripcion;
    }

    public String getUsuarioNIF(int idUsuario, Connection con) throws Exception {
        log.debug("getUsuarioNIF DAO - BEGIN");
        PreparedStatement pt = null;
        ResultSet rs = null;
        String descripcion = "";
        try {
            String query = "select USU_NIF from flbgen.a_usu where usu_cod=?";
            log.error("sql = " + query);
            pt = con.prepareStatement(query);
            pt.setInt(1, idUsuario);
            log.error("Parametros ? : " + idUsuario);
            rs = pt.executeQuery();
            while (rs.next()) {
                descripcion = rs.getString("USU_NIF");
            }
        } catch (Exception ex) {
            log.error("Se ha producido un error recuperando Descripcopm Procedimiento - interoperabilidad ", ex);
            throw new Exception(ex);
        } finally {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("Procedemos a cerrar el prepararedstatement y el resultset");
                }
                if (pt != null) {
                    pt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                log.error("Se ha producido un error cerrando el preparedstatement y el resulset", e);
                throw new Exception(e);
            }
        }
        log.debug("getUsuarioNIF DAO - return " + descripcion);
        log.debug("getUsuarioNIF DAO - END");
        return descripcion;
    }

    public String getFechaInicioServicioFSE_DSD_CSuplementario(String codProcExpte, String numExpediente, String tabla, String nombreCampo, String codCampoSuple, String campoIDNumExp,String nombreCampoAuxWhere,Connection con) throws Exception {
        log.error("getFechaInicioServicioFSE_DSD_CSuplementario DAO - BEGIN");
        PreparedStatement pt = null;
        ResultSet rs = null;
        String fechaInicioServicio = "";
        try {
            String query = "SELECT TO_CHAR("+nombreCampo+",'dd/mm/yyyy') "+nombreCampo
                    +" FROM " + tabla
                    +" WHERE " + campoIDNumExp +"=? "
                    ;
            if(codCampoSuple!=null && !codCampoSuple.equalsIgnoreCase("") && (nombreCampoAuxWhere!=null && !nombreCampoAuxWhere.equalsIgnoreCase(""))){
                query+= " AND " + nombreCampoAuxWhere + "=?";
            }
            log.error("sql = " + query);
            log.error("parametros 1,2= " + numExpediente+","+codCampoSuple);
            
            pt = con.prepareStatement(query);
            pt.setString(1, numExpediente);
            if(codCampoSuple!=null && !codCampoSuple.equalsIgnoreCase("")){
                pt.setString(2, codCampoSuple);
            }
            rs = pt.executeQuery();
            while (rs.next()) {
                fechaInicioServicio = rs.getString(nombreCampo);
            }
            // Si es APEC es un caso especial, es una fecha calcula en funcio de la fecha de acuse de resolucion
            /**
             * Fecha inicio periodo: F. Acuse Recibo Resolución+1 día, 
             * Fecha fin periodo: F. Acuse Recibo Resolución + 6 meses, 
             * Fecha fin presentación Justificación: fecha fin periodo + 1 mes * osea, fecha de acuse de recibo resolución + 7 meses) 
             */
            if("APEC".equalsIgnoreCase(codProcExpte))
                fechaInicioServicio=calcularFechasInicioFinServicioCasoAPEC(fechaInicioServicio,1);
        } catch (Exception ex) {
            log.error("Se ha producido un error recuperando Fecha de Incio Contrato (servicio) FSE - interoperabilidad ", ex);
            throw new Exception(ex);
        } finally {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("Procedemos a cerrar el prepararedstatement y el resultset");
                }
                if (pt != null) {
                    pt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                log.error("Se ha producido un error cerrando el preparedstatement y el resulset", e);
                throw new Exception(e);
            }
        }
        log.debug("getFechaInicioServicioFSE_DSD_CSuplementario DAO - return " + fechaInicioServicio);
        log.debug("getFechaInicioServicioFSE_DSD_CSuplementario DAO - END ()");
        return fechaInicioServicio;
    }

    public String getFechaFinServicioFSE(String codProcExpte, String numExpediente, String tabla, String nombreCampo, String codCampoSuple, String campoIDNumExp,String nombreCampoAuxWhere, Connection con) throws Exception {
        log.error("getFechaFinServicioFSE DAO - BEGIN");
        PreparedStatement pt = null;
        ResultSet rs = null;
        String fechaFinServicio = "";
        try {
            String query = "SELECT TO_CHAR(" + nombreCampo + ",'dd/mm/yyyy') " + nombreCampo
                    + " FROM " + tabla
                    + " WHERE " + campoIDNumExp + "=? ";
            if (codCampoSuple != null && !codCampoSuple.equalsIgnoreCase("") && (nombreCampoAuxWhere != null && !nombreCampoAuxWhere.equalsIgnoreCase(""))) {
                query += " AND " + nombreCampoAuxWhere + "=?";
            }
            log.error("sql = " + query);
            log.error("parametros 1,2= " + numExpediente + "," + codCampoSuple);

            pt = con.prepareStatement(query);
            pt.setString(1, numExpediente);
            if (codCampoSuple != null && !codCampoSuple.equalsIgnoreCase("")) {
                pt.setString(2, codCampoSuple);
            }
            rs = pt.executeQuery();
            while (rs.next()) {
                fechaFinServicio = rs.getString(nombreCampo);
            }
            // Si es APEC es un caso especial, es una fecha calcula en funcio de la fecha de acuse de resolucion
            /**
             * Fecha inicio periodo: F. Acuse Recibo Resolución+1 día, Fecha fin
             * periodo: F. Acuse Recibo Resolución + 6 meses, Fecha fin
             * presentación Justificación: fecha fin periodo + 1 mes * osea,
             * fecha de acuse de recibo resolución + 7 meses)
             */
            if ("APEC".equalsIgnoreCase(codProcExpte)) {
                fechaFinServicio = calcularFechasInicioFinServicioCasoAPEC(fechaFinServicio, 2);
            }
        } catch (Exception ex) {
            log.error("Se ha producido un error recuperando Fecha de Fin Contrato (servicio) FSE - interoperabilidad ", ex);
            throw new Exception(ex);
        } finally {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("Procedemos a cerrar el prepararedstatement y el resultset");
                }
                if (pt != null) {
                    pt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                log.error("Se ha producido un error cerrando el preparedstatement y el resulset", e);
                throw new Exception(e);
            }
        }
        log.debug("getFechaFinServicioFSE DAO - return " + fechaFinServicio);
        log.debug("getFechaFinServicioFSE DAO - END ()");
        return fechaFinServicio;
    }

    private String calcularFechasInicioFinServicioCasoAPEC(String fecha, Integer caso) {
        log.error("calcularFechasInicioFinServicioCasoAPEC - Begin()");
        String valorReturn ="";
        try {
            /**
             * caso 1 = Fecha inicio periodo: F. Acuse Recibo Resolución+1 día, 
             * caso 2 = Fecha fin periodo: F. Acuse Recibo Resolución + 6 meses, 
             * Fecha fin presentación Justificación: fecha fin periodo + 1 mes * osea, fecha de acuse de recibo resolución + 7 meses) 
             */
            if(fecha!=null && !fecha.equalsIgnoreCase("")){
                SimpleDateFormat dateFormat = new  SimpleDateFormat("dd/MM/yyyy");
                Date fechaDate = dateFormat.parse(fecha);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaDate);
                if(caso==1){
                    calendar.add(Calendar.DATE,1);
                } else if (caso==2){
                    calendar.add(Calendar.MONTH,6);
                }
                Date temp = calendar.getTime();
                valorReturn = dateFormat.format(temp);
            } 
        } catch (Exception e) {
            log.error("Error al calcular los valore sde Fechas Inicio/Fin Contrato APEC", e);
        }
        log.error("calcularFechasInicioFinServicioCasoAPEC - End () + " + valorReturn);
        return valorReturn;
    }

    public String getResultadoParametroWSFSE_DSD_CSE_DESPLEGABLE(String codProcExpte, String numExpediente, String codCampoSuple, Connection con) throws Exception {
        log.error("getResultadoParametroWSFSE_DSD_CSE_DESPLEGABLE DAO - BEGIN");
        PreparedStatement pt = null;
        ResultSet rs = null;
        String resultado = "";
        try {
            String query = "SELECT E_TDE.TDE_VALOR RESULTADO " 
                    //+ "E_TDE.TDE_VALOR ||'-'|| E_DES_VAL.DES_NOM RESULTADO " +
                    + " FROM E_TDE " 
                    //+ "LEFT JOIN E_DES_VAL ON E_DES_VAL.DES_COD=? AND E_DES_VAL.DES_VAL_COD=E_TDE.TDE_VALOR " +
                    + " WHERE TDE_NUM=? AND TDE_COD=? ";
            
            log.error("sql = " + query);
            log.error("parametros 1,2= " + numExpediente + "," + codCampoSuple);

            pt = con.prepareStatement(query);
            pt.setString(1, numExpediente);
            pt.setString(2, codCampoSuple);

            rs = pt.executeQuery();
            while (rs.next()) {
                resultado = rs.getString("RESULTADO");
            }
            if (resultado!=null && !resultado.equalsIgnoreCase("")){ 
                if(resultado.toUpperCase().equalsIgnoreCase("S")) {
                    resultado = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_CODIGO_RESULTADO_FAVORABLE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                }else if(resultado.toUpperCase().equalsIgnoreCase("N")) {
                    resultado = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_CODIGO_RESULTADO_DENEGADO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                }
            }
        } catch (Exception ex) {
            log.error("Se ha producido un error recuperando parametro resultado para WS FSE - interoperabilidad ", ex);
            throw new Exception(ex);
        } finally {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("Procedemos a cerrar el prepararedstatement y el resultset");
                }
                if (pt != null) {
                    pt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                log.error("Se ha producido un error cerrando el preparedstatement y el resulset", e);
                throw new Exception(e);
            }
        }
        log.debug("getResultadoParametroWSFSE_DSD_CSE_DESPLEGABLE DAO - return " + resultado);
        log.debug("getResultadoParametroWSFSE_DSD_CSE_DESPLEGABLE DAO - END ()");
        return resultado;
        
    }

    public List<TerceroVO> getlistaTercerosAdicionalesLAK(String codProcExpte, String numExpediente, Connection con) throws Exception {
        log.debug("getlistaTercerosAdicionalesLAK DAO - Begin ()");
        Statement st = null;
        ResultSet rs = null;
        List<TerceroVO> listaTerceros = new ArrayList<TerceroVO>();
        TerceroVO tercero = new TerceroVO();
            String query = null;
            query = "SELECT 0 TER_COD,0 TER_nvr, nvl(COD_TIPO_NIF,1) TER_TID, DNI_TRAB TER_DOC, NOMBRE TER_NOM, APELLIDO1 TER_AP1, APELLIDO2 TER_AP2,RTRIM(NOMBRE||' '||APELLIDO1||' '||APELLIDO2) ter_NOC , '' AS NUM_SOPORTE " +
                    ", SEXO_OFE TSEXOTERCERO , FEC_NACIMIENTO TFECNACIMIENTO , '' TNACIONTERCERO " +
                    ",0 TER_CODROL, 'MODULO EXTENSION' as TER_ROL , NUM_EXP EXT_NUM " +
                    ", FEC_INI_CONTR_SOL, FEC_INI_CONTR_OFE, FEC_FIN_CONTR_SOL, FEC_FIN_CONTR_OFE"
                    + " ,DNN_PRV,dnn_mun "
                    + " FROM MELANBIDE67_EMPRESAS "
                    + " LEFT JOIN (select datos.*, ROW_NUMBER()over (partition by HTE_DOC  order by HTE_DOC, DNN_SIT ASC,DNN_FAL DESC) orden_dom " +
                        " from (" + 
                        " select distinct HTE_DOC,LPAD(T_DNN.DNN_PRV,2,0) DNN_PRV,LPAD(T_DNN.Dnn_mun,3,0) dnn_mun, " +
                        " T_DNN.DNN_SIT,DNN_FAL " +
                        " from t_hte " +
                        " left join t_dot on t_HTE.hte_ter=T_DOT.DOT_TER " +
                        " left join T_dnn on T_DNN.DNN_dom=T_DOT.DOT_DOM " +
                        " ORDER BY T_HTE.HTE_DOC, DNN_SIT ASC,DNN_FAL DESC " +
                        ") datos "
                    + " ) ON HTE_DOC=DNI_TRAB and orden_dom=1 "
                    + " WHERE NUM_EXP='" + numExpediente + "' ORDER BY ter_NOC";

            if (log.isDebugEnabled()) {
                log.error("sql = " + query);
            }
            st = con.createStatement();        try {

            rs = st.executeQuery(query);
            while (rs.next()) {
                tercero = (TerceroVO) MeLanbideInteropMappingUtils.getInstance().map(rs, TerceroVO.class);
                listaTerceros.add(tercero);
                tercero = new TerceroVO();
            }
        } catch (Exception ex) {
            log.error("Se ha producido un error recuperando Terceros Adicionesl  x Expediente - interoperabilidad ", ex);
            throw new Exception(ex);
        } finally {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("Procedemos a cerrar el statement y el resultset");
                }
                if (st != null) {
                    st.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                log.error("Se ha producido un error cerrando el statement y el resulset", e);
                throw new Exception(e);
            }
        }
        log.debug("getlistaTercerosAdicionalesLAK DAO - End ()");
        return listaTerceros;
    }

    public String getFechaInicioFinServicioFSE_DSD_ModuloExte(String codProcExpte, String numExpediente, String tabla, String nombreCampo, String campoIDNumExp, HashMap<String, String> paramAdicioWhereModExten, Connection con) throws Exception {
        log.error("getFechaInicioServicioFSE_DSD_ModuloExte DAO - BEGIN");
        PreparedStatement pt = null;
        ResultSet rs = null;
        String fechaInicioFinServicio = "";
        try {
            String query = "SELECT TO_CHAR(" + nombreCampo + ",'dd/mm/yyyy') " + nombreCampo
                    + " FROM " + tabla
                    + " WHERE " + campoIDNumExp + "=? ";
            if (paramAdicioWhereModExten!=null && paramAdicioWhereModExten.size()>0) {
                for(String campoClave :paramAdicioWhereModExten.keySet()){
                    query += " AND TO_CHAR(" + campoClave + ")='"+paramAdicioWhereModExten.get(campoClave)+"'";
                }
            }
            log.error("sql = " + query);
            log.error("parametros 1= " + numExpediente);

            pt = con.prepareStatement(query);
            pt.setString(1, numExpediente);
            
            rs = pt.executeQuery();
            while (rs.next()) {
                fechaInicioFinServicio = rs.getString(nombreCampo);
            }
        } catch (Exception ex) {
            log.error("Se ha producido un error recuperando Fecha de IncioFin Contrato DESDE MOD. DE EXTENSION (servicio) FSE - interoperabilidad ", ex);
            throw new Exception(ex);
        } finally {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("Procedemos a cerrar el prepararedstatement y el resultset");
                }
                if (pt != null) {
                    pt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                log.error("Se ha producido un error cerrando el preparedstatement y el resulset", e);
                throw new Exception(e);
            }
        }
        log.debug("getFechaInicioServicioFSE_DSD_ModuloExte DAO - return " + fechaInicioFinServicio);
        log.debug("getFechaInicioServicioFSE_DSD_ModuloExte DAO - END ()");
        return fechaInicioFinServicio;
    }
    
}
