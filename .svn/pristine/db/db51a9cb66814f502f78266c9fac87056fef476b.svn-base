/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.util;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.manager.InteropJobsUtils;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.util.InteropJobsAKSSGAConstantes;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

/**
 *
 * @author pablo.bugia
 */
public class ArchivadoDocumentosHistoricos {

    private static final int BLOQUE_BYTES = 1024;
    private static final InteropJobsUtils interopJobsUtils = new InteropJobsUtils();
    private static final Logger log = Logger.getLogger(ArchivadoDocumentosHistoricos.class);

    public static List<String> listarFicheros(final String directorio, int parametroDias) {
        final File carpeta = new File(directorio);

        File[] files = carpeta.listFiles();
        final List<String> result = new ArrayList<String>();
        final DateTime fechaActual = new DateTime();

        final DateTime fechaPasada = fechaActual.minusDays(parametroDias);

        log.info("listarFicheros " + fechaActual + ", " + fechaPasada);
        for (int i = 0; files != null && i < files.length; i++) {
            if (files[i].isFile()) {
                if (files[i].lastModified() < fechaPasada.getMillis()) {
                    result.add(files[i].getAbsolutePath());
                    log.info("listarFicheros borrese o archívese: " + files[i].getName());
                }
                log.info("listarFicheros " + files[i].getName() + ", " + new DateTime(files[i].lastModified()));
            }
        }
        return result;
    }

    private static boolean borrarFicherosAntiguos(final List<String> ficheros) {
        boolean exito = true;

        for (final String fichero : ficheros) {
            File ficheroLogs = new File(fichero);
            if (!ficheroLogs.delete()) {
                exito = false;
            }
        }
        return exito;
    }

    public static void compressOldFiles(final String directorio, final List<String> ficheros) throws IOException {
        if (ficheros == null || ficheros.size() <= 0) {
            throw new IOException();
        }
        final DateTime fechaActual = new DateTime();
        final SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("yyyyMMddHHmmss");
        boolean allCompress = true;

        final FileOutputStream fos = new FileOutputStream(directorio + File.separator
                + ConfigurationParameter.getParameter(InteropJobsAKSSGAConstantes.NOMBRE_COMPRIMIDO,
                        interopJobsUtils.FICHERO_PROPIEDADES)
                + simpleDateFormat.format(fechaActual.toDate()) + ".zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        try {
            for (final String srcFile : ficheros) {
                File fileToZip = new File(srcFile);
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[BLOQUE_BYTES];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            }
            zipOut.close();
            fos.close();
        } catch (final IOException ex) {
            allCompress = false;
        }
        if (allCompress) {
            log.info("Resultado borrarFicherosAntiguos = " + borrarFicherosAntiguos(ficheros));
        }
    }
}
