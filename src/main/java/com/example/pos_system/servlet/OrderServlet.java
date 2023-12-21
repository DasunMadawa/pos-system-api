package com.example.pos_system.servlet;

import com.example.pos_system.bo.BOFactory;
import com.example.pos_system.bo.custom.OrderBO;
import com.example.pos_system.dto.OrderDTO;
import com.example.pos_system.entity.Order_t;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "order_servlet" , urlPatterns = "/order_servlet")
public class OrderServlet extends HttpServlet {
    OrderBO orderBO = (OrderBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ORDER);

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set CORS headers for preflight requests
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:port_number");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setHeader("Access-Control-Max-Age", "3600");

        // Set other headers as needed for your application

        // Return a successful response for the preflight request
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Order_get");

        resp.setHeader("Access-Control-Allow-Origin" , "http://localhost:port_number");

        String oId = req.getParameter("oId");
        Jsonb jsonb = JsonbBuilder.create();

        if (oId != null) {
            try {
                OrderDTO orderDTO = orderBO.searchOrder(oId);
                String jsonData = jsonb.toJson(orderDTO);

                resp.getWriter().println(jsonData);

            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                e.printStackTrace();
            }

        } else {
            try {
                List<OrderDTO> allOrders = orderBO.getAllOrders();

                String jsonData = jsonb.toJson(allOrders);

                resp.getWriter().println(jsonData);

            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                e.printStackTrace();
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin" , "http://localhost:port_number");

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        Jsonb jsonb = JsonbBuilder.create();

        try {
            OrderDTO fromJson = jsonb.fromJson(req.getReader(), OrderDTO.class);
            System.out.println(fromJson);

            boolean isSaved = orderBO.addOrder(fromJson);

            resp.getWriter().println(isSaved);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            e.printStackTrace();
        }

    }

}
