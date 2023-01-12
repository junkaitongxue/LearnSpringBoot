package com.dreamkite.demo.common.utils;

import com.github.xiaoymin.knife4j.core.io.ResourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Slf4j
public class ExportUtils {

    public static final String HEADER_FILE_NAME = "fileName";
    public static final String UTF_8 = "UTF-8";

    private ExportUtils() {

    }

    public static void exportDemo(String fileName) {
        FileInputStream input = null;
        OutputStream output = null;
        try{
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert requestAttributes != null;
            HttpServletResponse response = requestAttributes.getResponse();
            File file = ResourceUtils.getFile(fileName);
            input = new FileInputStream(file);

            assert response != null;
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding(UTF_8);
            String encodedFileName = URLEncoder.encode(file.getName(), UTF_8);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + encodedFileName);
            response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HEADER_FILE_NAME);
            response.setHeader(HEADER_FILE_NAME, "fileName");

            output = response.getOutputStream();
            IOUtils.copy(input, output);
        } catch (IOException ex) {
            log.error("Failed to export demo", ex);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }

    }
}
