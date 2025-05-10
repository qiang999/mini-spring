package com.minis.web;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: HttpMessageConverter
 * @Package: com.minis.web
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/10 - 18:45
 */
public interface HttpMessageConverter {
    void write(Object obj, HttpServletResponse response) throws IOException;
}
