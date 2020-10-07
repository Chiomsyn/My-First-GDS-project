package chioms.gds.myapplication.GetApi;

import java.util.List;

import chioms.gds.myapplication.ListResponse.SkillsListResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillsApiInterface_Get {
    @GET("/api/skilliq")
    Call<List<SkillsListResponse>> getSkillsList();
}
