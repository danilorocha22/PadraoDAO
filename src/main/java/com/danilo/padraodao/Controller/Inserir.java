package com.danilo.padraodao.Controller;

import com.danilo.padraodao.DAO.ErroBancoException;
import com.danilo.padraodao.DAO.PessoaDaoClasse;
import com.danilo.padraodao.Manipula.Manipula;
import com.danilo.padraodao.Models.Pessoa;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.lang.model.element.Element;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.w3c.dom.Node;

/**
 *
 * @author Danilo Rocha
 */
public class Inserir extends HttpServlet {
  
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Inserir</title>");
            out.println("</head>");
            out.println("<body>");

            String nome = request.getParameter("nome");
            int idade = Integer.parseInt(request.getParameter("idade"));
            String editar = request.getParameter("editar");

            Pessoa p = new Pessoa();
            p.setIdade(idade);
            p.setNome(nome);

            PessoaDaoClasse dao = null;
            try {
                if (!"false".equals(editar)) {
                    dao = new PessoaDaoClasse();
                    dao.criaPessoa(p);
                    out.print("Cadastrado com sucesso");
                } else {
                    dao = new PessoaDaoClasse();
                    dao.editarPessoa(p);
                    out.print("Atualizado com sucesso");
                }
                
               

            } catch (ErroBancoException | SQLException ex) {
                out.print("Erro ao tentar cadastrar");
            }
           
            Manipula index = new Manipula(getServletContext().getRealPath("/index.xml"));
            Node[] dados = index.pegaDados();
            
            List<Pessoa> pessoas = null;
            
            try {
                
                pessoas = dao.pegaPessoas();
                out.print("<ul>");
                for (int i = 0; i < pessoas.size(); i++) {
                    Pessoa p1 = pessoas.get(i);
                    int codigo=p1.getCodigo();
                    out.println("<li>Nome: " + p1.getNome() + ", idade: " + p1.getIdade()+";" +
                            "<a type='button' href=http://localhost:8080/PadraoDAO/>Editar</a></li>");
                    
                    dados[0].setNodeValue(p1.getNome());
                    dados[1].setNodeValue(String.valueOf(p1.getIdade()));
                    
                }
                
                out.print("</ul>");
            } catch (SQLException ex) {
                out.print("<p>Erro ao tentar ler os dados</p>");
            }

            out.println("</body>");
            out.println("</html>");
        }
            
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
