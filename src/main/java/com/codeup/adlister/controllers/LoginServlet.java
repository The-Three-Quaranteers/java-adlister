package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Message;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Message errMsg = new Message();
        String errStr = "";
        errMsg.setDescription("");
        User user = DaoFactory.getUsersDao().findByUsername(username);

        // validate input
        boolean inputHasErrors = username.isEmpty()
                || password.isEmpty();

        if (inputHasErrors) {
            if (username.isEmpty()){
                errStr+= "* Username Required! ";
            }
            if (password.isEmpty()){
                errStr+= "* Password Required! ";
            }
            errMsg.setDescription(errStr);
            request.getSession().setAttribute("message",errMsg);
            request.getSession().setAttribute("username",username);
            response.sendRedirect("/login");
            return;
        }

        if (user == null) {
            response.sendRedirect("/login");
            return;
        }

        boolean validAttempt = Password.check(password, user.getPassword());

        if (validAttempt) {
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("id", user.getId());
            errStr = "";
            errMsg.setDescription(errStr);
            request.getSession().setAttribute("message",errMsg);
            response.sendRedirect("/profile");
        } else {
            errStr+= "* Incorrect username or password *";
            errMsg.setDescription(errStr);
            request.getSession().setAttribute("message",errMsg);
            request.getSession().setAttribute("username",username);
            response.sendRedirect("/login");
        }
    }
}
