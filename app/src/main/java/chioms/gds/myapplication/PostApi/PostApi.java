package chioms.gds.myapplication.PostApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostApi {
    private static Retrofit sRetrofit = null;
    public static PostApiInterface getClient(){

        if (sRetrofit == null){
            sRetrofit = new Retrofit.Builder()
                    .baseUrl("https://docs.google.com/forms/d/e/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        PostApiInterface api = sRetrofit.create(PostApiInterface.class);
        return api;
    }
}
