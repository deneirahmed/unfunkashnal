package ca.sait.gifreviewr.servlets;

import ca.sait.gifreviewr.dataaccess.*;
import ca.sait.gifreviewr.models.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * Handles GIF requests
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 * @version March 23, 2021
 */
public class GifServlet extends HttpServlet {
    private GifService gifService = new GifService();
    private ReviewService reviewService = new ReviewService();

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
        String paramId = request.getParameter("id");
        
        int id = Integer.parseInt(paramId);
        
        
        Gif gif = null;
        ArrayList<Review> reviews = null;
        
        try {
            gif = this.gifService.get(id);
            reviews = this.reviewService.getReviews(id);
        } catch (SQLException ex) {
            Logger.getLogger(GifServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (gif == null) {
            response.sendError(404, "No GIF with that ID was found.");
            return;
        } else if (reviews == null) {
            response.sendError(500, "An error occurred loading reviews.");
            return;
        }
        
        request.setAttribute("gif", gif);
        request.setAttribute("reviews", reviews);
        
        request.getRequestDispatcher("/WEB-INF/gif.jsp").forward(request, response);
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
        int gifId = Integer.parseInt(request.getParameter("id"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String name = request.getParameter("name");
        String reviewText = request.getParameter("review");
        
        try {
            this.reviewService.addReview(gifId, name, reviewText, rating);
            
            request.setAttribute("success", "Successfully added review.");
        } catch (SQLException ex) {
            Logger.getLogger(GifServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "An error occurred adding review.");
        }
        
        processRequest(request, response);
    }

}
