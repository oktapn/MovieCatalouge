package id.co.muf.okta.moviecatalouge.data.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tvshowentities")
public class TvShowEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "imageId")
    private String imageId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "favorites")
    private boolean favorites = false;

    @ColumnInfo(name = "imagePath")
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

    public boolean isFavorites() {
        return favorites;
    }

    public void setFavorites(boolean favorites) {
        this.favorites = favorites;
    }
}
