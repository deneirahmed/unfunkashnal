package ca.sait.gifreviewr.dataaccess;

import ca.sait.gifreviewr.models.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Provides database services for reviews.
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 * @version March 23, 2021
 */
public class ReviewService {
    private DatabaseDriver driver;
    
    public ReviewService() {
        this.driver = new DatabaseDriver();
    }
    
    /**
     * Gets reviews associated with GIF.
     * @param id GIF ID to get reviews for.
     * @return ArrayList of Review instances.
     * @throws SQLException Thrown if an error occurred performing SQL operation.
     */
    public ArrayList<Review> getReviews(int id) throws SQLException {
        String sql = "SELECT * FROM reviews WHERE gif_id = ?";
        PreparedStatement stmt = this.driver.getConnection().prepareStatement(sql);
        
        stmt.setInt(1, id);
        
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Review> found = new ArrayList<>();
        
        while (rs.next()) {
            int reviewId = rs.getInt("id");
            String name = rs.getString("name");
            String reviewText = rs.getString("review");
            int rating = rs.getInt("rating");
            Date createdAt = rs.getDate("created_at");

            Review review = new Review(reviewId, id, name, reviewText, rating, createdAt);
            found.add(review);
        }
        
        return found;
    }
    
    /**
     * Gets average rating for a GIF.
     * @param id GIF ID to get average rating for.
     * @return Average rating
     * @throws SQLException Thrown if an error occurred performing SQL operation.
     */
    public double getRating(int id) throws SQLException {
        String sql = "SELECT AVG(rating) AS rating FROM reviews WHERE gif_id = ?";
        PreparedStatement stmt = this.driver.getConnection().prepareStatement(sql);
        
        stmt.setInt(1, id);
        
        ResultSet rs = stmt.executeQuery();
        
        rs.next();
        
        double rating = rs.getDouble("rating");
        return rating;
    }
    
    /**
     * Adds a new review for a GIF.
     * @param gifId ID of GIF to add review for.
     * @param name Name of person who wrote review.
     * @param review Review
     * @param rating Rating (between 1 and 5)
     * @return True if review was created.
     * @throws SQLException Thrown if an error occurred performing SQL operation.
     */
    public boolean addReview(int gifId, String name, String review, int rating) throws SQLException {
        String sql = "INSERT INTO reviews (gif_id, name, review, rating) VALUES(?, ?, ?, ?)";
        PreparedStatement stmt = this.driver.getConnection().prepareStatement(sql);
        
        stmt.setInt(1, gifId);
        stmt.setString(2, name);
        stmt.setString(3, review);
        stmt.setInt(4, rating);
        
        int added = stmt.executeUpdate();
        
        if (added > 0)
            return true;
        else
            return false;
    }
}
