package com.knx.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.knx.Instructions;

//commentaire test
/**
 * Servlet implementation class RestKNX
 */
@WebServlet("/RestKNX")
public class RestKNX extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RestKNX() { 
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Instructions instruction = new Instructions();
		instruction.lancerInstruction(request);
		
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
