package id.co.muf.okta.moviecatalouge.data;

public class TvShowEntity {
    private String imageId;
    private String title;
    private String description;
    private String date;
    private boolean favorites = false;
    private String imagePath;

    public TvShowEntity(String imageId, String title, String description, String date, Boolean favorites, String imagePath) {
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

    public String getImagePath() {
        return imagePath;
    }

}
