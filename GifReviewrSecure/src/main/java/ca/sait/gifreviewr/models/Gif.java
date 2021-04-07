package ca.sait.gifreviewr.models;

import java.sql.Date;

/**
 * Represents a GIF record.
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 * @version March 23, 2021
 */
public class Gif {
    private int id;

    private String title;

    private String url;
    
    private double averageRating;

    private Date createdAt;

    /**
     * Initializes GIF instance
     * @param id ID of GIF
     * @param title Title
     * @param url URL of GIF
     * @param averageRating Average rating of GIF
     * @param createdAt When GIF was added.
     */
    public Gif(int id, String title, String url, double averageRating, Date createdAt) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.averageRating = averageRating;
        this.createdAt = createdAt;
    }
    
    /**
     * Gets the ID
     * @return ID
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Gets title of GIF
     * @return Title of GIF
     */
    public String getTitle() {
        return this.title;
    }
    
    /**
     * Gets URL of GIF
     * @return URL of GIF
     */
    public String getUrl() {
        return this.url;
    }
    
    /**
     * Gets average rating
     * @return Average rating
     */
    public double getAverageRating() {
        return this.averageRating;
    }
    
    /**
     * Gets when GIF was added
     * @return When GIF was added
     */
    public Date getCreatedAt() {
        return this.createdAt;
    }
}
