package bgroseclose.leagueofyou.Presenters.Activities;

import java.util.List;

import androidx.annotation.NonNull;
import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Models.LeagueOfYouAccount;
import bgroseclose.leagueofyou.Models.SummonerRankInfo;
import bgroseclose.leagueofyou.R;
import bgroseclose.leagueofyou.Retrofit.IRiotClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardPresenter {

    private View view;
    private LeagueOfYouAccount leagueOfYouAccount;
    private IRiotClient riotClient;

    public DashboardPresenter(View view, IRiotClient riotClient) {
        this.view = view;
        this.leagueOfYouAccount = LeagueOfYouSingleton.getLeagueOfYouAccount();
        this.riotClient = riotClient;
    }

    public void loadSummoner() {
        view.loadDashboard(true);
        Call<List<SummonerRankInfo>> call = riotClient.getSummonerRankInfo(String.valueOf(leagueOfYouAccount.getSummonerInfo().getSummonerId()));
        call.enqueue(new Callback<List<SummonerRankInfo>>() {
            @Override
            public void onResponse(@NonNull Call<List<SummonerRankInfo>> call, @NonNull Response<List<SummonerRankInfo>> response) {
                if(response.body() != null) {
                    leagueOfYouAccount.getSummonerInfo().setSummonerRankedInfo(response.body());
                    LeagueOfYouSingleton.setLeagueOfYouAccount(leagueOfYouAccount);
                    view.initDrawerAndToolbar();
                }
                view.loadDashboard(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<SummonerRankInfo>> call, @NonNull Throwable t) {
                view.loadDashboard(false);
                view.displayServerError();
            }
        });
    }

    public void drawerItemSelected(int id) {
        if(id == R.id.drawer_dashboard_menu) {
            view.openDashboardFragment();
        } else if (id == R.id.drawer_champion_menu) {
            view.openChampionFragment();
        } else if (id == R.id.drawer_builds_menu) {
            view.openBuildFragment();
        } else if (id == R.id.drawer_logout_menu) {
            view.logout();
        }
    }

    public interface View {
        void initDrawerAndToolbar();
        void loadDashboard(boolean isVisible);
        void displayServerError();
        void openDashboardFragment();
        void openChampionFragment();
        void openBuildFragment();
        void logout();
    }
}
