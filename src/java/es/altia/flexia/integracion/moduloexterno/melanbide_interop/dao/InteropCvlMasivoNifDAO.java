package es.altia.flexia.integracion.moduloexterno.melanbide_interop.dao;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConfigurationParameter;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConstantesMeLanbideInterop;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.InteropCvlMasivoNifVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class InteropCvlMasivoNifDAO {

    private static final Logger log = Logger.getLogger(InteropCvlMasivoNifDAO.class);

    private static InteropCvlMasivoNifDAO instance = null;

    private InteropCvlMasivoNifDAO() {
    }

    public static InteropCvlMasivoNifDAO getInstance() {
        if (instance == null) {
            synchronized (InteropCvlMasivoNifDAO.class) {
                instance = new InteropCvlMasivoNifDAO();
            }
        }
        return instance;
    }

    /**
     * Inserta un registro por NIF procesado.
     */
    public int insertarRegistro(final Timestamp fechaEjecucion, final String nif, final String tipoDoc,
            final String codRespuesta, final String descRespuesta, final String payloadResumen,
            final String usuario, final Connection con) throws Exception {
        final InteropCvlMasivoNifVO registro = new InteropCvlMasivoNifVO(null, fechaEjecucion, nif,
                tipoDoc, codRespuesta, descRespuesta, payloadResumen, usuario);
        return insertarRegistro(registro, con);
    }

    public int insertarRegistro(final InteropCvlMasivoNifVO registro, final Connection con) throws Exception {

        PreparedStatement st = null;
        try {
            final int id = getNextId(con);
            final String sql = "INSERT INTO " + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_INTEROP_CVL_MASIVO_NIF, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES)
                    + " (ID, FECHA_EJECUCION, NIF, TIPO_DOC, COD_RESPUESTA, DESC_RESPUESTA, PAYLOAD_RESUMEN, USUARIO,"
                    + " COD_ORGANIZACION, COD_TRAMITE, OCURRENCIA_TRAMITE, NUM_EXPEDIENTE, FK_WS_SOLICITADO, FECHA_DESDE_CVL, FECHA_HASTA_CVL)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.setTimestamp(2, registro.getFechaEjecucion());
            st.setString(3, registro.getNif());
            st.setString(4, registro.getTipoDoc());
            st.setString(5, registro.getCodRespuesta());
            st.setString(6, registro.getDescRespuesta());
            st.setString(7, registro.getPayloadResumen());
            st.setString(8, registro.getUsuario());
            if (registro.getCodOrganizacion() != null) {
                st.setInt(9, registro.getCodOrganizacion().intValue());
            } else {
                st.setNull(9, java.sql.Types.INTEGER);
            }
            if (registro.getCodTramite() != null) {
                st.setInt(10, registro.getCodTramite().intValue());
            } else {
                st.setNull(10, java.sql.Types.INTEGER);
            }
            if (registro.getOcurrenciaTramite() != null) {
                st.setInt(11, registro.getOcurrenciaTramite().intValue());
            } else {
                st.setNull(11, java.sql.Types.INTEGER);
            }
            st.setString(12, registro.getNumExpediente());
            st.setString(13, registro.getFkWSSolicitado());
            st.setString(14, registro.getFechaDesdeCVL());
            st.setString(15, registro.getFechaHastaCVL());

            return st.executeUpdate();
        } catch (Exception ex) {
            log.error("Error insertando auditoria CVL masivo para NIF " + registro.getNif(), ex);
            throw new Exception(ex);
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    /**
     * Consulta para auditoria/reimpresion por id.
     */
    public List<InteropCvlMasivoNifVO> getRegistrosById(final Long id, final Connection con) throws Exception {
        final String tabla = ConfigurationParameter.getParameter(
                ConstantesMeLanbideInterop.TABLA_INTEROP_CVL_MASIVO_NIF,
                ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
        final String sql = "SELECT ID, FECHA_EJECUCION, NIF, TIPO_DOC, COD_RESPUESTA, DESC_RESPUESTA, PAYLOAD_RESUMEN, USUARIO,"
                + " COD_ORGANIZACION, COD_TRAMITE, OCURRENCIA_TRAMITE, NUM_EXPEDIENTE, FK_WS_SOLICITADO, FECHA_DESDE_CVL, FECHA_HASTA_CVL"
                + " FROM " + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_INTEROP_CVL_MASIVO_NIF, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES) + " WHERE ID = ?";
        return ejecutarConsulta(sql, id, null, null, con);
    }

    /**
     * Consulta para auditoria/reimpresion por rango de fechas.
     */
    public List<InteropCvlMasivoNifVO> getRegistrosByFechaEjecucion(final Timestamp fechaDesde,
            final Timestamp fechaHasta, final Connection con) throws Exception {
        final String tabla2 = ConfigurationParameter.getParameter(
                ConstantesMeLanbideInterop.TABLA_INTEROP_CVL_MASIVO_NIF,
                ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
        final String sql = "SELECT ID, FECHA_EJECUCION, NIF, TIPO_DOC, COD_RESPUESTA, DESC_RESPUESTA, PAYLOAD_RESUMEN, USUARIO,"
                + " COD_ORGANIZACION, COD_TRAMITE, OCURRENCIA_TRAMITE, NUM_EXPEDIENTE, FK_WS_SOLICITADO, FECHA_DESDE_CVL, FECHA_HASTA_CVL"
                + " FROM " + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_INTEROP_CVL_MASIVO_NIF, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES) + " WHERE FECHA_EJECUCION BETWEEN ? AND ? ORDER BY FECHA_EJECUCION DESC, ID DESC";
        return ejecutarConsulta(sql, null, new Timestamp[]{fechaDesde, fechaHasta}, null, con);
    }

    public List<InteropCvlMasivoNifVO> getRegistrosByFechaEjecucionYDni(final Timestamp fechaDesde,
            final Timestamp fechaHasta, final String dni, final Connection con) throws Exception {
        final String tabla2 = ConfigurationParameter.getParameter(
                ConstantesMeLanbideInterop.TABLA_INTEROP_CVL_MASIVO_NIF,
                ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
        final String sql = "SELECT ID, FECHA_EJECUCION, NIF, TIPO_DOC, COD_RESPUESTA, DESC_RESPUESTA, PAYLOAD_RESUMEN, USUARIO,"
                + " COD_ORGANIZACION, COD_TRAMITE, OCURRENCIA_TRAMITE, NUM_EXPEDIENTE, FK_WS_SOLICITADO, FECHA_DESDE_CVL, FECHA_HASTA_CVL"
                + " FROM " + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_INTEROP_CVL_MASIVO_NIF, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES)
                + " WHERE FECHA_EJECUCION BETWEEN ? AND ? AND UPPER(NIF)=? ORDER BY FECHA_EJECUCION DESC, ID DESC";
        return ejecutarConsulta(sql, null, new Timestamp[]{fechaDesde, fechaHasta}, dni != null ? dni.trim().toUpperCase() : "", con);
    }

    private List<InteropCvlMasivoNifVO> ejecutarConsulta(final String sql, final Long id,
            final Timestamp[] rangoFechas, final String dni, final Connection con) throws Exception {

        final List<InteropCvlMasivoNifVO> result = new ArrayList<InteropCvlMasivoNifVO>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = con.prepareStatement(sql);
            if (id != null) {
                st.setLong(1, id.longValue());
            } else if (dni != null) {
                st.setTimestamp(1, rangoFechas[0]);
                st.setTimestamp(2, rangoFechas[1]);
                st.setString(3, dni);
            } else {
                st.setTimestamp(1, rangoFechas[0]);
                st.setTimestamp(2, rangoFechas[1]);
            }

            rs = st.executeQuery();
            while (rs.next()) {
                result.add(new InteropCvlMasivoNifVO(
                        Long.valueOf(rs.getLong("ID")),
                        rs.getTimestamp("FECHA_EJECUCION"),
                        rs.getString("NIF"),
                        rs.getString("TIPO_DOC"),
                        rs.getString("COD_RESPUESTA"),
                        rs.getString("DESC_RESPUESTA"),
                        rs.getString("PAYLOAD_RESUMEN"),
                        rs.getString("USUARIO"),
                        rs.getObject("COD_ORGANIZACION") != null ? Integer.valueOf(rs.getInt("COD_ORGANIZACION")) : null,
                        rs.getObject("COD_TRAMITE") != null ? Integer.valueOf(rs.getInt("COD_TRAMITE")) : null,
                        rs.getObject("OCURRENCIA_TRAMITE") != null ? Integer.valueOf(rs.getInt("OCURRENCIA_TRAMITE")) : null,
                        rs.getString("NUM_EXPEDIENTE"),
                        rs.getString("FK_WS_SOLICITADO"),
                        rs.getString("FECHA_DESDE_CVL"),
                        rs.getString("FECHA_HASTA_CVL")));
            }
        } catch (Exception ex) {
            log.error("Error recuperando auditoria CVL masivo", ex);
            throw new Exception(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        }

        return result;
    }

    private int getNextId(final Connection con) throws Exception {

    final String secuencia = ConfigurationParameter.getParameter(
            ConstantesMeLanbideInterop.SEQ_INTEROP_CVL_MASIVO_NIF,
            ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);

    Statement st = null;
    ResultSet rs = null;

    try {
        st = con.createStatement();
        rs = st.executeQuery("SELECT " + secuencia + ".NEXTVAL FROM DUAL");

        if (rs.next()) {
            return rs.getInt(1);
        }

        throw new Exception("No se pudo obtener identificador de secuencia " + secuencia);

    } catch (Exception ex) {
        log.error("Error generando secuencia " + secuencia, ex);
        throw new Exception(ex);

    } finally {
        if (rs != null) {
            rs.close();
        }
        if (st != null) {
            st.close();
        }
    }
}
}
