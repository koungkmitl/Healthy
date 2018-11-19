package com.example.koung.healthy.retrofit;

import com.example.koung.healthy.post.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {
    @GET("posts")
    Call<List<Post>> getData();
}
