package com.schimidtsolutions.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.schimidtsolutions.model.Book;
import com.schimidtsolutions.service.BookService;
import com.schimidtsolutions.service.annotations.CurrentService;
import com.schimidtsolutions.service.annotations.LegacyService;

@WebServlet(urlPatterns={"/BookServlet"}, initParams={
		@WebInitParam(name="editora", value="Fundo de Quintal"),
		@WebInitParam(name="endereco", value="Rua dos malacas, 171")})

public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Logger log;
    
	@Inject @LegacyService
	private BookService bookServiceLegacy; 
	
	@Inject @CurrentService
	private BookService bookServiceCurrent; 
	
    public BookServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book currentBook = bookServiceCurrent.createBook("JEE 7", 153.25, "JEE is the old...");	
		Book legacyBook = bookServiceLegacy.createBook("JEE 3", 10.00, "JEE is the new...");
		
		log.info( "Executando método doGet..." );
		 
		PrintWriter writer = response.getWriter();
		writer.println( "<html>" );
		writer.println( "<body>" );
		writer.println( "<h1>Livro Novo: " + currentBook.toString() + " </h1>" );
		writer.println( "<h1>Livro Antigo: " + legacyBook.toString() + " </h1>" );
		writer.println( "<h2>Editora: " + getInitParameter( "editora" ) + " </h2>" );
		writer.println( "<h2>Endereco: " + getInitParameter( "endereco" ) + " </h2>" );
		writer.println( "</body>" );
		writer.println( "</html>" ); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
