package models;

public class Review {
    private int rating;
    private String message;
    private String writerFirstName;
    private String writerLastName;

    public Review(int rating, String message, String writerFirstName, String writerLastName) {
        this.rating = rating;
        this.message = message;
        this.writerFirstName = writerFirstName;
        this.writerLastName = writerLastName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getWriterFirstName() {
        return writerFirstName;
    }

    public void setWriterFirstName(String writerFirstName) {
        this.writerFirstName = writerFirstName;
    }

    public String getWriterLastName() {
        return writerLastName;
    }

    public void setWriterLastName(String writerLastName) {
        this.writerLastName = writerLastName;
    }
}
