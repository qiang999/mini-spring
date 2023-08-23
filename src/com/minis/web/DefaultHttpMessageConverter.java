package com.minis.web;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Title: DefaultHttpMessageConverter
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/8/22 - 10:46
 */
public class DefaultHttpMessageConverter implements HttpMessageConverter{
    String defaultContentType = "text/json;charset=UTF-8";
    String defaultCharacterEncoding = "UTF-8";

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    ObjectMapper objectMapper;

    @Override
    public void write(Object obj, HttpServletResponse response) throws IOException, IllegalAccessException {
        response.setContentType(defaultContentType);
        response.setCharacterEncoding(defaultCharacterEncoding);
        writeInternal(obj,response);
        response.flushBuffer();
    }

    private void writeInternal(Object obj,HttpServletResponse response) throws IOException, IllegalAccessException {
        String sJsonStr = this.objectMapper.writeValuesAsString(obj);
        PrintWriter pw = response.getWriter();
        pw.write(sJsonStr);
    }
}
