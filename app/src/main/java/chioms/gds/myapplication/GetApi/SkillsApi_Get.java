package chioms.gds.myapplication.GetApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkillsApi_Get {

    public static Retrofit sRetrofit = null;
    public static SkillsApiInterface_Get getClient(){

        if(sRetrofit == null){
            sRetrofit = new Retrofit.Builder()
                    .baseUrl("")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        SkillsApiInterface_Get api = sRetrofit.create(SkillsApiInterface_Get.class);
                return api;
    }
}
