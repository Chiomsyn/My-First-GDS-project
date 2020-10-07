package chioms.gds.myapplication.GetApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LearnersApi_Get {
    public static Retrofit sRetrofit = null;
    public static LearnersApiInterface_Get getClient(){

        if(sRetrofit == null){
            sRetrofit = new Retrofit.Builder()
                    .baseUrl("https://gadsapi.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        LearnersApiInterface_Get api = sRetrofit.create(LearnersApiInterface_Get.class);
        return api;
    }
}
