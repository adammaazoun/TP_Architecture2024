package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Admin;

import java.io.IOException;

/**
 * Servlet implementation class CRUDAdminServlet
 */
public class CRUDAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		Admin.supprimer_livre(isbn);
		response.sendRedirect("HomeAdmin?search=newClient");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		String titre = request.getParameter("titre");
		String auteur = request.getParameter("auteur");
		String annee = request.getParameter("annee");
		String prix = request.getParameter("prix");
		Admin.ajout_livre(isbn, titre, auteur, annee, prix);
		response.sendRedirect("HomeAdmin?search=newClient");
		
	}

}
