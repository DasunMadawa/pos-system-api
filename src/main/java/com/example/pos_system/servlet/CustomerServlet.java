package com.example.pos_system.servlet;

import com.example.pos_system.bo.BOFactory;
import com.example.pos_system.bo.custom.CustomerBO;
import com.example.pos_system.dto.CustomerDTO;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "customer_servlet", urlPatterns = "/customer_servlet")
public class CustomerServlet extends HttpServlet {
    CustomerBO customerBO = (CustomerBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        // Set CORS headers for preflight requests
//        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
//        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
//        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
//        resp.setHeader("Access-Control-Max-Age", "3600");
//
//        // Set other headers as needed for your application
//
//        // Return a successful response for the preflight request
//        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");

//        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        try {
            boolean isAdded = customerBO.addCustomer(toCustomerDTO(req));

            resp.getWriter().println(isAdded);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get");

//        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");

        String cId = req.getParameter("cId");
        Jsonb jsonb = JsonbBuilder.create();
        resp.setContentType("application/json");

        if (cId != null) {
            try {
                CustomerDTO customerDTO = customerBO.searchCustomer(cId);

                String jsonData = jsonb.toJson(customerDTO);

                resp.getWriter().write(jsonData);

            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                e.printStackTrace();
            }

        } else {
            try {
                List<CustomerDTO> allCustomers = customerBO.getAllCustomers();

                String jsonData = jsonb.toJson(allCustomers);

                resp.getWriter().write(jsonData);

            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("put");

//        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        try {
            boolean isUpdated = customerBO.updateCustomer(toCustomerDTO(req));

            resp.getWriter().println(isUpdated);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            e.printStackTrace();
        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("delete");

//        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");

        String cId = req.getParameter("cId");

        if (cId == null) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        try {
            boolean isDeleted = customerBO.deleteCustomer(cId);

            resp.getWriter().println(isDeleted);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            e.printStackTrace();
        }

    }

    private CustomerDTO toCustomerDTO(HttpServletRequest req) throws IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        return new CustomerDTO(
                jsonObject.getString("cId"),
                jsonObject.getString("cName"),
                jsonObject.getString("cAddress"),
                Double.parseDouble(jsonObject.getString("cSalary"))
        );
    }


}
