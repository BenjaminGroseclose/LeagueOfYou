package bgroseclose.leagueofyou.Presenters.Activities;

import java.util.List;

import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Models.LeagueOfYouAccount;
import bgroseclose.leagueofyou.Models.SummonerRankInfo;
import bgroseclose.leagueofyou.Retrofit.IRiotClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardPresenter {

    private List<SummonerRankInfo> rankedInfo;
    private View view;
    private LeagueOfYouAccount leagueOfYouAccount;
    private IRiotClient riotClient;

    public DashboardPresenter(View view, IRiotClient riotClient) {
        this.view = view;
        this.leagueOfYouAccount = LeagueOfYouSingleton.getLeagueOfYouAccount();
        this.riotClient = riotClient;
    }

    public void drawerItemSelected(int itemId) {

    }

    public void loadSummoner() {
        view.loadDashboard(true);
        Call<List<SummonerRankInfo>> call = riotClient.getSummonerRankInfo(String.valueOf(leagueOfYouAccount.getSummonerInfo().getSummonerId()));
        call.enqueue(new Callback<List<SummonerRankInfo>>() {
            @Override
            public void onResponse(Call<List<SummonerRankInfo>> call, Response<List<SummonerRankInfo>> response) {
                if(response.body() != null) {
                    rankedInfo = response.body();
                    leagueOfYouAccount.getSummonerInfo().setSummonerRankedInfo(rankedInfo);
                    LeagueOfYouSingleton.setLeagueOfYouAccount(leagueOfYouAccount);
                }
                view.loadDashboard(false);
            }

            @Override
            public void onFailure(Call<List<SummonerRankInfo>> call, Throwable t) {
                view.loadDashboard(false);
                view.displayServerError();
            }
        });

    }

    public interface View {
        void loadDashboard(boolean isVisible);
        void displayServerError();
    }
}
