package com.example.pos_system.servlet;

import com.example.pos_system.bo.BOFactory;
import com.example.pos_system.bo.custom.ItemBO;
import com.example.pos_system.dto.ItemDTO;
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

@WebServlet(name = "item_servlet", urlPatterns = "/item_servlet")
public class ItemServlet extends HttpServlet {
    ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ITEM);

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
        System.out.println("get");

        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:port_number");

        String iCode = req.getParameter("iCode");
        Jsonb jsonb = JsonbBuilder.create();

        if (iCode != null) {
            try {
                ItemDTO itemDTO = itemBO.searchItem(iCode);

                String jsonData = jsonb.toJson(itemDTO);

                resp.getWriter().println(jsonData);
            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                e.printStackTrace();
            }
        } else {
            try {
                List<ItemDTO> allItems = itemBO.getAllItems();

                String jsonData = jsonb.toJson(allItems);

                resp.getWriter().println(jsonData);
            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");

        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:port_number");

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        try {
            boolean isSaved = itemBO.addItem(toItemDTO(req));

            resp.getWriter().println(isSaved);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            e.printStackTrace();
        }


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("put");

        resp.setHeader("Access-Control-Allow-Origin" , "http://localhost:port_number");

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        try {
            boolean isUpdated = itemBO.updateItem(toItemDTO(req));

            resp.getWriter().println(isUpdated);

        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            e.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("delete");

        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:port_number");

        String iCode = req.getParameter("iCode");

        if (iCode == null) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        try {
            boolean isDeleted = itemBO.deleteItem(iCode);

            resp.getWriter().println(isDeleted);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            e.printStackTrace();
        }

    }

    private ItemDTO toItemDTO(HttpServletRequest req) throws IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        return new ItemDTO(
                jsonObject.getString("iCode"),
                jsonObject.getString("iName"),
                Double.parseDouble(jsonObject.getString("iPrice")),
                Integer.parseInt(jsonObject.getString("iQty"))
        );

    }

}
