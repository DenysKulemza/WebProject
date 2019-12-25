import db.DBManager;
import db.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registr")
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path;
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String surName = req.getParameter("surname");

        if(DBManager.selectOne(email)){
            path = "errorRegistration.html";
            resp.sendRedirect(path);
        }else{
            DBManager.insert(new Student(name, surName, email));
            path = "successful.html";
            resp.sendRedirect(path);
        }
    }
}
