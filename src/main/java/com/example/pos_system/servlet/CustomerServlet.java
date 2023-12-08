package com.example.pos_system.servlet;

import com.example.pos_system.bo.BOFactory;
import com.example.pos_system.bo.custom.CustomerBO;
import com.example.pos_system.dto.CustomerDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( name = "customerServlet" , urlPatterns = "/customer_servlet" )
public class CustomerServlet extends HttpServlet {
    CustomerBO customerBO =(CustomerBO)BOFactory.getBOFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get");

        // Set CORS headers
//        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
//        resp.setHeader("Access-Control-Allow-Methods", "GET");
//        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String cId = req.getParameter("id");
        String cName = req.getParameter("name");
        String cAddress = req.getParameter("address");
        double cSalary = Double.parseDouble(req.getParameter("salary"));

        boolean isAdded = false;
        String error = null;
        try {
            isAdded = customerBO.addCustomer(new CustomerDTO(cId, cName, cAddress, cSalary));
        } catch (Exception e) {
            error = e.getMessage();
        }

        resp.getWriter().println(isAdded);

    }

}
