package id.co.muf.okta.moviecatalouge.data;

public class MovieEntity {
    private String imageId;
    private String title;
    private String description;
    private String date;
    private boolean favorites = false;
    private Integer imagePath;

    public MovieEntity(String imageId, String title, String description, String date, Boolean favorites, Integer imagePath) {
        this.imageId = imageId;
        this.title = title;
        this.description = description;
        this.date = date;
        if (favorites != null) {
            this.favorites = favorites;
        }
        this.imagePath = imagePath;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isFavorites() {
        return favorites;
    }

    public void setFavorites(boolean favorites) {
        this.favorites = favorites;
    }

    public Integer getImagePath() {
        return imagePath;
    }

    public void setImagePath(Integer imagePath) {
        this.imagePath = imagePath;
    }
}
