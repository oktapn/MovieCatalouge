package id.co.muf.okta.moviecatalouge.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieResponse implements Parcelable {
    private String imageId;
    private String title;
    private String description;
    private String date;
    private boolean favorites = false;
    private String imagePath;

    public MovieResponse() {
    }

    public MovieResponse(String imageId, String title, String description, String date, boolean favorites, String imagePath) {
        this.imageId = imageId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.favorites = favorites;
        this.imagePath = imagePath;
    }

    protected MovieResponse(Parcel in) {
        imageId = in.readString();
        title = in.readString();
        description = in.readString();
        date = in.readString();
        favorites = in.readByte() != 0;
        imagePath = in.readString();
    }

    public String getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageId);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(date);
        parcel.writeString(imagePath);
    }

    public static final Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {
        @Override
        public MovieResponse createFromParcel(Parcel in) {
            return new MovieResponse(in);
        }

        @Override
        public MovieResponse[] newArray(int size) {
            return new MovieResponse[size];
        }
    };


}
