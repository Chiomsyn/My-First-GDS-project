package chioms.gds.myapplication.PostApi;

import chioms.gds.myapplication.ListResponse.SubmitResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostApiInterface {
    @FormUrlEncoded
    @POST("")
    Call<SubmitResponse> submission(@Field("") String )
}
