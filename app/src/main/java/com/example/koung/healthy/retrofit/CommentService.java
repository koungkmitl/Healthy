package com.example.koung.healthy.retrofit;

import com.example.koung.healthy.post.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CommentService {
    @GET("/posts/{id}/comments")
    Call<List<Comment>> getData(@Path("id") String id);
}
