package ca.sait.gifreviewr.servlets;

import ca.sait.gifreviewr.dataaccess.GifService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * Handles add GIF requests
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 * @version March 23, 2021
 */
public class AddGifServlet extends HttpServlet {

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
        request.getRequestDispatcher("/WEB-INF/add-gif.jsp").forward(request, response);
    }

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
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        
        GifService service = new GifService();
        
        try {
            service.add(title, url);
            
            request.setAttribute("success", "Added GIF successfully.");
        } catch (SQLException ex) {
            Logger.getLogger(AddGifServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "An error occurred adding GIF.");
        }
        
        processRequest(request, response);
    }

}
