package id.co.muf.okta.moviecatalouge.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.co.muf.okta.moviecatalouge.data.source.local.entity.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.TvShowEntity;

@Database(entities = {MovieEntity.class, TvShowEntity.class},
        version = 1,
        exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase INSTANCE;

    public abstract MovieDao movieDao();

    private static final Object sLock = new Object();

    public static MovieDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MovieDatabase.class, "Movies.db")
                        .build();
            }
            return INSTANCE;
        }
    }

}
