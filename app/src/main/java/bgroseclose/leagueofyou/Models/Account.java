package bgroseclose.leagueofyou.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Account {

    private String SummonerName;
    private Calendar DateOfBirth;
    private SummonerInfo SummonerInfo;

    public Account() {

    }

    public Account(String summonerName, String dateOfBirth) {
        this.SummonerName = summonerName;
        this.DateOfBirth = convertDateOfBirth(dateOfBirth);
    }

    public bgroseclose.leagueofyou.Models.SummonerInfo getSummonerInfo() {
        return SummonerInfo;
    }

    public void setSummonerInfo(bgroseclose.leagueofyou.Models.SummonerInfo summonerInfo) {
        SummonerInfo = summonerInfo;
    }

    public String getSummonerName() {
        return SummonerName;
    }

    public void setSummonerName(String summonerName) {
        SummonerName = summonerName;
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
