package id.co.muf.okta.moviecatalouge.utils;

import android.app.Application;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import id.co.muf.okta.moviecatalouge.data.source.remote.response.MovieResponse;
import id.co.muf.okta.moviecatalouge.data.source.remote.response.TvshowResponse;

public class JsonHelper {
    private Application application;

    public JsonHelper(Application application) {
        this.application = application;
    }

    private String parsingFileToString(String fileName) {
        try {
            InputStream is = application.getAssets().open(fileName);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<MovieResponse> loadMovies() {
        ArrayList<MovieResponse> list = new ArrayList<>();

        try {
            JSONObject responseObject = new JSONObject(parsingFileToString("MovieResponses.json"));
            JSONArray listArray = responseObject.getJSONArray("movies");
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject course = listArray.getJSONObject(i);

                String id = course.getString("id");
                String title = course.getString("title");
                String description = course.getString("description");
                String date = course.getString("date");
                boolean favorites = course.getBoolean("favorites");
                String imagePath = course.getString("imagePath");

                MovieResponse courseResponse = new MovieResponse(id, title, description, date, favorites, imagePath);
                list.add(courseResponse);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("1213", String.valueOf(list.size()));
        return list;
    }

    public List<TvshowResponse> loadTvshows() {
        ArrayList<TvshowResponse> list = new ArrayList<>();
        try {

            String result = parsingFileToString("TvshowResponses.json");
            if (result != null) {
                JSONObject responseObject = new JSONObject(result);
                JSONArray listArray = responseObject.getJSONArray("tvshows");
                for (int i = 0; i < listArray.length(); i++) {
                    JSONObject course = listArray.getJSONObject(i);

                    String id = course.getString("id");
                    String title = course.getString("title");
                    String description = course.getString("description");
                    String date = course.getString("date");
                    boolean favorites = course.getBoolean("favorites");
                    String imagePath = course.getString("imagePath");

                    TvshowResponse courseResponse = new TvshowResponse(id, title, description, date, favorites, imagePath);
                    list.add(courseResponse);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("1212", String.valueOf(list.size()));
        return list;
    }
}
