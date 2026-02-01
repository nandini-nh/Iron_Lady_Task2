package com.tap;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/program")
public class ProgramServlet extends HttpServlet {

    // In-memory storage
    private List<Map<String, String>> programs = new ArrayList<>();

    // CREATE
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Map<String, String> program = new HashMap<>();
        program.put("name", req.getParameter("name"));
        program.put("duration", req.getParameter("duration"));
        program.put("status", req.getParameter("status"));

        programs.add(program);
        resp.getWriter().write("Program added");
    }

    // READ
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/plain");
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < programs.size(); i++) {
            Map<String, String> p = programs.get(i);
            out.append(i)
               .append(". ")
               .append(p.get("name"))
               .append(" | ")
               .append(p.get("duration"))
               .append(" | ")
               .append(p.get("status"))
               .append("\n");
        }

        resp.getWriter().write(out.toString());
    }

    // UPDATE
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int index = Integer.parseInt(req.getParameter("index"));

        if (index >= 0 && index < programs.size()) {
            programs.get(index).put("name", req.getParameter("name"));
            programs.get(index).put("duration", req.getParameter("duration"));
            programs.get(index).put("status", req.getParameter("status"));
            resp.getWriter().write("Program updated");
        } else {
            resp.getWriter().write("Invalid index");
        }
    }

    // DELETE
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int index = Integer.parseInt(req.getParameter("index"));

        if (index >= 0 && index < programs.size()) {
            programs.remove(index);
            resp.getWriter().write("Program deleted");
        } else {
            resp.getWriter().write("Invalid index");
        }
    }
}
