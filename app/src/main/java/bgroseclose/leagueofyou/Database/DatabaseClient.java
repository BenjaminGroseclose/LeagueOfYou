package bgroseclose.leagueofyou.Database;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import bgroseclose.leagueofyou.Models.LeagueOfYouAccount;

public class DatabaseClient {

    private static final String TAG = "DatabaseClient";
    private static final String dbAccount = "Account";
    private static final String dbChampionList = "ChampionList";

    private static DatabaseReference databaseReference;
    private static boolean isSuccessful;

    public static void saveAccount(LeagueOfYouAccount leagueOfYouAccount) {
        if(databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }

        databaseReference.child(dbAccount)
                .child(leagueOfYouAccount.getUserId())
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
    public static void getAccount(String userId, final IDatabaseListener listener) {
        if(databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }

        listener.onStart();
        Query query = databaseReference.child(dbAccount).orderByChild("userId").equalTo(userId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    listener.onSuccess(dataSnapshot);
                } else {
                    listener.onFailure();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: " + databaseError.toString(), databaseError.toException());
                listener.onFailure();
            }
        });
    }

    public static void getChampionList(final IDatabaseListener listener) {
        if(databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }

        listener.onStart();
        Query query = databaseReference.child(dbChampionList);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    listener.onSuccess(dataSnapshot);
                } else {
                    listener.onFailure();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: " + databaseError.toString(), databaseError.toException());
                listener.onFailure();
            }
        });

    }
}
