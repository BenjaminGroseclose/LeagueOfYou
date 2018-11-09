package bgroseclose.leagueofyou.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LeagueOfYouAccount {

    private String userId;
    private String SummonerName;
    private String DateOfBirth;
    private SummonerInfo SummonerInfo;

    public LeagueOfYouAccount() {

    }

    public LeagueOfYouAccount(String summonerName, String dateOfBirth) {
        this.SummonerName = summonerName;
        this.DateOfBirth = dateOfBirth;
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

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public Calendar dateOfBirthCalendar() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
        try {
            calendar.setTime(sdf.parse(getDateOfBirth()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
