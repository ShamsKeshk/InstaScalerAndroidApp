package com.example.instascaler.data.network;

import com.example.instascaler.data.database.Campaign;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    private static final String baseUrl = "http://192.168.1.11:8080/";

    private Retrofit retrofit;
    private CampaignApi mCampaignApi;


    private static NetworkUtils networkUtils;
    private static final Object LOCK = new Object();

    private NetworkUtils() {
       this.retrofit = initRetrofit();
    }


    public static NetworkUtils getInstance() {
        if (networkUtils == null) {
            synchronized (LOCK) {
                networkUtils = new NetworkUtils();
            }
        }
        return networkUtils;
    }

    public Retrofit initRetrofit(){
        if (retrofit == null){
            synchronized (LOCK){
                Gson gson = new GsonBuilder().serializeNulls().create();
                retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(initializeOkHttp())
                        .build();
            }
        }
        return retrofit;
    }

    private OkHttpClient initializeOkHttp() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient =
                new OkHttpClient.Builder()
                        .addInterceptor(new Interceptor() {
                            @Override
                            public okhttp3.Response intercept(Chain chain) throws IOException {
                                Request originalRequest = chain.request();

                                Request newRequest = originalRequest.newBuilder().header("Intercept-Header", "Shams Is Back")
                                        .build();
                                return chain.proceed(newRequest);
                            }
                        })
                        .addInterceptor(httpLoggingInterceptor)
                        .build();

        return okHttpClient;
    }



    public List<Campaign> getCampaings() {
        mCampaignApi =
                retrofit.create(CampaignApi.class);

        Call<List<Campaign>> listCall = mCampaignApi.getCampaigns();


        listCall.enqueue(new Callback<List<Campaign>>() {


            @Override
            public void onResponse(Call<List<Campaign>> call, Response<List<Campaign>> response) {
                if (!response.isSuccessful()) {

                }

                if (response.isSuccessful()) {

                }

            }

            @Override
            public void onFailure(Call<List<Campaign>> call, Throwable t) {

            }
        });
    return null;
}

    /*
    public void getComments(final TextView textView){

        /*
        * ") int postId ,
            @Query("") String sortBy,
            @Query(""*/
/*
        Map<String ,String> queryValues = new HashMap<>();
        queryValues.put("postId","1");
        queryValues.put("_sort","id");
        queryValues.put("_order","DESC");


        // Call<List<Comment>> listCall = jsonPlaceHolderApi.getComments(2,"id","DESC");
        Call<List<Comment>> listCall = jsonPlaceHolderApi.getCommentsFilteredById(queryValues);

        listCall.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()){
                    String message = context.getString(R.string.http_code,String.valueOf(response.code()));
                    textView.setText(message);
                }

                if (response.isSuccessful()){
                    List<Comment> list = response.body();
                    String content = " ";
                    if (list == null){
                        textView.setText("List Is Empty");
                    }else {
                        for (Comment comment : list) {
                            content += "ID : " + comment.getId()+"\n"
                                    + "\n Post ID : " + comment.getPostId() + "\n"
                                    + "\n Title : " + comment.getName() +"\n"
                                    + "\n Body : " + comment.getText() + "\n"
                                    + "\n Email : " + comment.getEmail() + "\n\n";
                        }

                        textView.setText(content);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textView.setText(context.getString(R.string.http_error_message,t.getMessage()));
            }
        });
    }

    public void createPost(final TextView textView){
        Map<String ,String> map = new HashMap<>();
        map.put("userId" , "800");
        map.put("title" , "udacity");
        map.put("body" , "not udemy");

      //  Call<Post> newPost =
        //        jsonPlaceHolderApi.createPost(new Post(500,"Andela","Scholarship"));

        //Call<Post> newPost =
        //        jsonPlaceHolderApi.createPost(570,"Andela","Scholarship");

        Call<Post> newPost =
                jsonPlaceHolderApi.createPost(map);



        newPost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()){
                    String message = context.getString(R.string.http_code,String.valueOf(response.code()));
                    textView.setText(message);
                }

                if (response.isSuccessful()){
                    Post post = response.body();
                    String content = " ";
                    if (post == null){
                        textView.setText("List Is Empty");
                    }else {
                            content += "ID : " + post.getId()+"\n"
                                    + "\n Post ID : " + post.getUserId() + "\n"
                                    + "\n Title : " + post.getTitle() +"\n"
                                    + "\n Body : " + post.getText() + "\n\n";

                        textView.setText(content);
                    }
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(context.getString(R.string.http_error_message,t.getMessage()));
            }
        });
    }
    */

}
