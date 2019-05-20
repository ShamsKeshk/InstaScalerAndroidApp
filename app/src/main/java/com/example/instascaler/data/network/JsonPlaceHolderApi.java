package com.example.instascaler.data.network;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

/*
    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("_sort") String sortBy,
            @Query("_order") String orderBy
    );

    @GET("posts/{id}")
    Call<Post> getPostById(@Path("id") int postId);

    @GET("posts")
    Call<List<Post>> getPostsOfUserById(
            @Query("userId") int[] userIds,
            @Query("_sort") String sortBy,
            @Query("_order") String orderBy
    );

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId,
                                    @Query("_sort") String sortBy,
                                    @Query("_order") String orderBy);

    @GET("comments")
    Call<List<Comment>> getCommentsFilteredById(
            @Query("postId") int postId,
            @Query("_sort") String sortBy,
            @Query("_order") String orderBy);

    @GET("comments")
    Call<List<Comment>> getCommentsFilteredById(@QueryMap Map<String, String> queryValues);

    //In This call you can type your full Url in the Main Activity
    @GET
    Call<List<Comment>> getComments(@Url String url);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String body
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> map);

    @Headers({"Header 1: Not meaning Any thing " , " Header 2 : the same as the previous "})
    @PUT("posts/{id}")//put change the hole object
    Call<Post> putPost(@Header("Dynamic-header") String header, @Path("id") int postId, @Body Post post);

    @PATCH("posts/{id}")//change only the Changed Field
    Call<Post> patchPost(@Path("id") int postId, @Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int postId);
*/

}
