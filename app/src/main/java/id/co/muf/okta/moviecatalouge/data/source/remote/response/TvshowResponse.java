package id.co.muf.okta.moviecatalouge.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class TvshowResponse implements Parcelable {
    private String imageId;
    private String title;
    private String description;
    private String date;
    private boolean favorites = false;
    private String imagePath;

    public TvshowResponse() {
    }

    public TvshowResponse(String imageId, String title, String description, String date, boolean favorites, String imagePath) {
        this.imageId = imageId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.favorites = favorites;
        this.imagePath = imagePath;
    }

    protected TvshowResponse(Parcel in) {
        imageId = in.readString();
        title = in.readString();
        description = in.readString();
        date = in.readString();
        favorites = in.readByte() != 0;
        imagePath = in.readString();
    }

    public static final Creator<TvshowResponse> CREATOR = new Creator<TvshowResponse>() {
        @Override
        public TvshowResponse createFromParcel(Parcel in) {
            return new TvshowResponse(in);
        }

        @Override
        public TvshowResponse[] newArray(int size) {
            return new TvshowResponse[size];
        }
    };

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
}
