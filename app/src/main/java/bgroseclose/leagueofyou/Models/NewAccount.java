package bgroseclose.leagueofyou.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewAccount {

    private String SummonerName;
    private String Username;
    private String Password;
    private Calendar DateOfBirth;

    public NewAccount() {

    }

    public NewAccount(String summonerName, String username, String password, String dateOfBirth) {
        this.SummonerName = summonerName;
        this.Username = username;
        this.Password = password;
        this.DateOfBirth = convertDateOfBirth(dateOfBirth);
    }

    public String getSummonerName() {
        return SummonerName;
    }

    public void setSummonerName(String summonerName) {
        SummonerName = summonerName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Calendar getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    private Calendar convertDateOfBirth(String dateOfBirth) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
        try {
            calendar.setTime(sdf.parse(dateOfBirth));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

}
