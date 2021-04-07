package ca.sait.gifreviewr.dataaccess;

import ca.sait.gifreviewr.models.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Provides database services for GIFs.
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 * @version March 23, 2021
 */
public class GifService {
    private DatabaseDriver driver;
    
    public GifService() {
        this.driver = new DatabaseDriver();
    }
    
    /**
     * Finds GIFs with matching title and rating.
     * @param query Query to use for titles (% represents wildcard)
     * @param rating Minimum rating GIFs must have.
     * @return ArrayList of found Gif instances.
     * @throws SQLException Thrown if an error occurred performing SQL operation.
     */
    public ArrayList<Gif> find(String query, int rating) throws SQLException {
        String sql = String.format("SELECT gifs.*, AVG(reviews.rating) AS average FROM gifs LEFT JOIN reviews ON gifs.id = reviews.gif_id WHERE title LIKE %s GROUP BY gifs.id", query);
        Statement stmt = this.driver.getConnection().createStatement();
        
        ResultSet rs = stmt.executeQuery(sql);
        
        ArrayList<Gif> found = new ArrayList<>();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String url = rs.getString("url");
            double averageRating = rs.getDouble("average");
            Date createdAt = rs.getDate("created_at");
            
            if (averageRating >= rating) {
                Gif gif = new Gif(id, title, url, averageRating, createdAt);
                found.add(gif);
            }
        }
        
        return found;
    }
    
    /**
     * Gets GIF with matching ID
     * @param id GIF ID
     * @return Found Gif instance
     * @throws SQLException Thrown if an error occurred performing SQL operation.
     */
    public Gif get(int id) throws SQLException {
        String sql = "SELECT gifs.*, IFNULL(AVG(reviews.rating), 0) AS average FROM gifs LEFT JOIN reviews ON gifs.id = reviews.gif_id WHERE gifs.id = ? GROUP BY gifs.id";
        PreparedStatement stmt = this.driver.getConnection().prepareStatement(sql);
        
        stmt.setInt(1, id);
        
        ResultSet rs = stmt.executeQuery();
        
        if (!rs.next())
            return null;
        
        String title = rs.getString("title");
        String url = rs.getString("url");
        double averageRating = rs.getDouble("average");
        Date createdAt = rs.getDate("created_at");

        Gif gif = new Gif(id, title, url, averageRating, createdAt);
        
        return gif;
    }
    
    /**
     * Adds a GIF to the database.
     * @param title Title of GIF
     * @param url URL for GIF
     * @return True if record was created.
     * @throws SQLException Thrown if an error occurred performing SQL operation.
     */
    public boolean add(String title, String url) throws SQLException {
        String sql = "INSERT INTO gifs (title, url) VALUES(?, ?)";
        PreparedStatement stmt = this.driver.getConnection().prepareStatement(sql);
        
        stmt.setString(1, title);
        stmt.setString(2, url);
        
        int added = stmt.executeUpdate();
        
        if (added > 0)
            return true;
        else
            return false;
    }
}
