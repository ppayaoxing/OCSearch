package com.asiainfo.ocsearch.service;


import com.asiainfo.ocsearch.exception.ErrCode;
import com.asiainfo.ocsearch.exception.ServiceException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * Created by mac on 2017/3/21.
 */
public abstract class OCSearchService extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            byte[] re;

            if (request.getContentLength() > 1024)
                re = new ServiceException("request is too long",ErrCode.PARSE_ERROR).getErrorResonce();
            else {

                try {
                    JsonNode jsonNode = new ObjectMapper().readTree(request.getInputStream());
                    re = doService(jsonNode);

                } catch (IOException e) {
                    re = new ServiceException(e, ErrCode.PARSE_ERROR).getErrorResonce();
                } catch (ServiceException serviceExeption) {
                    re = serviceExeption.getErrorResonce();
                }
            }

            response.setCharacterEncoding("utf-8");
            ServletOutputStream so = response.getOutputStream();
            so.write(re);
            so.flush();
            so.close();
        } catch (Exception e) {

        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Map<String, String[]> params = request.getParameterMap();
            ObjectNode jsonNode = new ObjectMapper().createObjectNode();

            for (String key : params.keySet()) {
                jsonNode.put(key, params.get(key)[0]);
            }

            byte[] re;

            try {
                re = doService(jsonNode);
            } catch (ServiceException serviceExeption) {
                re = serviceExeption.getErrorResonce();
            }

            response.setCharacterEncoding("utf-8");
            ServletOutputStream so = response.getOutputStream();
            so.write(re);
            so.flush();
            so.close();
        } catch (Exception e) {

        }

    }

    protected abstract byte[] doService(JsonNode request) throws ServiceException;

    protected String getRequestId(){
        return UUID.randomUUID().toString();

    }

}
