package com.wishlist.compression;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wishlist.model.Response;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionUtil {

    private final static Logger LOG = Logger.getLogger(CompressionUtil.class);

    public static byte[] compress(Object data){
        String json = compressToJSON(data);
        byte[] gzip =  compressToGzip(json);
        return gzip;
    }

    public static String compressToJSON(Object data) {

        String jsonString = null;
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonString = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage());
        }

        return jsonString;
    }

    public static byte[] compressToGzip(String json) {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            final GZIPOutputStream gzip = new GZIPOutputStream(stream);
            gzip.write(json.getBytes("UTF-8"));
            gzip.flush();
            gzip.close();
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return stream.toByteArray();
    }

    public static Response decompress(byte [] bytes) {
        String json = decompressGzip(bytes);
        Response response = decompressJson(json);
        return response;
    }

    public static String decompressGzip(byte [] bytes){
        try {
            GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(bytes));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int len;
            while((len = gis.read(buffer))>0)
            baos.write(buffer, 0, len);
            return new String(baos.toByteArray(), "UTF-8");

        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return null;
    }

    public static Response decompressJson(String gzip) {
        Response response = null;
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
             response = objectMapper.readValue(gzip, Response.class);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }

        return response;
    }
}



