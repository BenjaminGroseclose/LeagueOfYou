package bgroseclose.leagueofyou.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import bgroseclose.leagueofyou.Models.NewAccount;

public class DatabaseClient {

    private static DatabaseReference databaseReference;

    public static void saveAccount(NewAccount account) {
        if(databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }

        databaseReference.child("Account")
                .child(String.valueOf(account.getSummonerInfo().getSummonerId()))
                .setValue(account);
    }

}
