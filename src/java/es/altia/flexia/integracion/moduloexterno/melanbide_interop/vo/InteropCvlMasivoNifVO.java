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

    public InteropCvlMasivoNifVO(final Long id, final Timestamp fechaEjecucion, final String nif,
            final String tipoDoc, final String codRespuesta, final String descRespuesta,
            final String payloadResumen, final String usuario) {
        this.id = id;
        this.fechaEjecucion = fechaEjecucion;
        this.nif = nif;
        this.tipoDoc = tipoDoc;
        this.codRespuesta = codRespuesta;
        this.descRespuesta = descRespuesta;
        this.payloadResumen = payloadResumen;
        this.usuario = usuario;
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
}
