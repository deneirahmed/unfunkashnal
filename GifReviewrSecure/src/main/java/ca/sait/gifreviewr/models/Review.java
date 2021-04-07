package ca.sait.gifreviewr.models;

import java.sql.Date;

/**
 * Represents a review for a GIF.
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 * @version March 23, 2021
 */
public class Review {
    private int id;

    private int gifId;

    private String name;
    
    private String review;
    
    private int rating;

    private Date createdAt;

    /**
     * Initializes review instance.
     * @param id Review ID
     * @param gifId ID of associated GIF
     * @param name Name of person who wrote review
     * @param review Review
     * @param rating Rating (between 1 and 5)
     * @param createdAt When review was created
     */
    public Review(int id, int gifId, String name, String review, int rating, Date createdAt) {
        this.id = id;
        this.gifId = gifId;
        this.name = name;
        this.review = review;
        this.rating = rating;
        this.createdAt = createdAt;
    }
    
    /**
     * Gets review ID
     * @return Review ID
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Gets GIF ID
     * @return ID of associated GIF
     */
    public int getGifId() {
        return this.gifId;
    }
    
    /**
     * Gets the name
     * @return Name of person who wrote review
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Gets the review
     * @return Review
     */
    public String getReview() {
        return this.review;
    }
    
    /**
     * Gets the rating
     * @return Rating (between 1 and 5)
     */
    public int getRating() {
        return this.rating;
    }
    
    /**
     * Gets when review was created
     * @return When review was created
     */
    public Date getCreatedAt() {
        return this.createdAt;
    }
}
