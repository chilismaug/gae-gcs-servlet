package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

	  @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		PrintWriter out;
		String title = "PhraseOmatic";
		response.setContentType("text/html");
	    response.setCharacterEncoding("UTF-8");
		out = response.getWriter();
		
		String cssTag="<link rel='stylesheet' type='text/css' href='/css/style.css'>";	
		
		out.println("<html>");
		out.println("<head><title>");
		out.println("PhraseOmatic Mark V");
		out.println("</title>"+cssTag+"</head>");
		out.println("<body><div align=\"center\">" );
		out.println("<h1>" + title + "</h1><br>");
		out.println("<h2>" + PhraseOMatic_Mark5.getPhrase() + "</h2><br><br>");
		out.println("<h4><a href=\"hello\">speak</a></h4>");
		out.println("</div></body>");
		out.println("</html>");

	  }
	}