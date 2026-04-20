package es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo;

import java.sql.Timestamp;

/**
 * Registro de auditoria de una llamada masiva de CVL por NIF.
 */
public class InteropCvlMasivoNifVO {

    private final Long id;
    private final Timestamp fechaEjecucion;
    private final String nif;
    private final String tipoDoc;
    private final String codRespuesta;
    private final String descRespuesta;
    private final String payloadResumen;
    private final String usuario;
    private final Integer codOrganizacion;
    private final Integer codTramite;
    private final Integer ocurrenciaTramite;
    private final String numExpediente;
    private final String fkWSSolicitado;
    private final String fechaDesdeCVL;
    private final String fechaHastaCVL;

    public InteropCvlMasivoNifVO(final Long id, final Timestamp fechaEjecucion, final String nif,
            final String tipoDoc, final String codRespuesta, final String descRespuesta,
            final String payloadResumen, final String usuario) {
        this(id, fechaEjecucion, nif, tipoDoc, codRespuesta, descRespuesta, payloadResumen, usuario,
                null, null, null, null, null, null, null);
    }

    public InteropCvlMasivoNifVO(final Long id, final Timestamp fechaEjecucion, final String nif,
            final String tipoDoc, final String codRespuesta, final String descRespuesta,
            final String payloadResumen, final String usuario,
            final Integer codOrganizacion, final Integer codTramite, final Integer ocurrenciaTramite,
            final String numExpediente, final String fkWSSolicitado,
            final String fechaDesdeCVL, final String fechaHastaCVL) {
        this.id = id;
        this.fechaEjecucion = fechaEjecucion;
        this.nif = nif;
        this.tipoDoc = tipoDoc;
        this.codRespuesta = codRespuesta;
        this.descRespuesta = descRespuesta;
        this.payloadResumen = payloadResumen;
        this.usuario = usuario;
        this.codOrganizacion = codOrganizacion;
        this.codTramite = codTramite;
        this.ocurrenciaTramite = ocurrenciaTramite;
        this.numExpediente = numExpediente;
        this.fkWSSolicitado = fkWSSolicitado;
        this.fechaDesdeCVL = fechaDesdeCVL;
        this.fechaHastaCVL = fechaHastaCVL;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getFechaEjecucion() {
        return fechaEjecucion;
    }

    public String getNif() {
        return nif;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public String getCodRespuesta() {
        return codRespuesta;
    }

    public String getDescRespuesta() {
        return descRespuesta;
    }

    public String getPayloadResumen() {
        return payloadResumen;
    }

    public String getUsuario() {
        return usuario;
    }

    public Integer getCodOrganizacion() {
        return codOrganizacion;
    }

    public Integer getCodTramite() {
        return codTramite;
    }

    public Integer getOcurrenciaTramite() {
        return ocurrenciaTramite;
    }

    public String getNumExpediente() {
        return numExpediente;
    }

    public String getFkWSSolicitado() {
        return fkWSSolicitado;
    }

    public String getFechaDesdeCVL() {
        return fechaDesdeCVL;
    }

    public String getFechaHastaCVL() {
        return fechaHastaCVL;
    }
}
