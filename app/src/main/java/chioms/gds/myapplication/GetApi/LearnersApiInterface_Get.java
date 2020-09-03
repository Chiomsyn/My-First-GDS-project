
package chioms.gds.myapplication.GetApi;

import java.util.List;

import chioms.gds.myapplication.ListResponse.LearnersListResponse;
import retrofit2.Call;
import retrofit2.http.GET;

    public interface LearnersApiInterface_Get {
        @GET("")
        Call<List<LearnersListResponse>> getLearnersList();
    }



