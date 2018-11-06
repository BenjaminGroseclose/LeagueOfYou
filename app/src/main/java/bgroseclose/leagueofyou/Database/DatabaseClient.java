package bgroseclose.leagueofyou.Database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Models.LeagueOfYouAccount;

public class DatabaseClient {

    private static final String TAG = "DatabaseClient";
    private static final String dbAccount = "Account";

    private static DatabaseReference databaseReference;
    private static boolean isSuccessful;

    public static void saveAccount(String userId, LeagueOfYouAccount leagueOfYouAccount) {
        if(databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }

        databaseReference.child(dbAccount)
                .child(userId)
                .setValue(leagueOfYouAccount)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        isSuccessful = task.isSuccessful();
                    }
                });
    }

    /**
     * This will should only be called if they are already logged in.
     */
    public static void getAccount(String userId) {
        if(databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }

        Query query = databaseReference.child(dbAccount).equalTo(userId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        LeagueOfYouAccount leagueOfYouAccount = snapshot.getValue(LeagueOfYouAccount.class);
                        LeagueOfYouSingleton.setLeagueOfYouAccount(leagueOfYouAccount);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: " + databaseError.toString(), databaseError.toException());
                isSuccessful = false;
            }
        });
    }

    public static void getSummonerName(String userId) {
        if(databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }

        Query query = databaseReference.child(dbAccount).equalTo(userId);
    }

    public static boolean isSuccessful() {
        return isSuccessful;
    }
}
