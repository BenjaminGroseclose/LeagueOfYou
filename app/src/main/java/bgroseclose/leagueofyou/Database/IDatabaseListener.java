package bgroseclose.leagueofyou.Database;

import com.google.firebase.database.DataSnapshot;

public interface IDatabaseListener {
    void onStart();
    void onSuccess(DataSnapshot snapshot);
    void onFailure();
}
