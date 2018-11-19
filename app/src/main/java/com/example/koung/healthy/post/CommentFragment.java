package com.example.koung.healthy.post;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.koung.healthy.R;
import com.example.koung.healthy.retrofit.CommentService;
import com.example.koung.healthy.retrofit.PostService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CommentFragment extends Fragment {

    private static final String URL = "https://jsonplaceholder.typicode.com";
    private final String TAG = getClass().getSimpleName();
    private ListView listView;
    private CommentAdapter commentAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onCLickBack();
        listItem();
    }

    private void onCLickBack() {
        Button btn = getView().findViewById(R.id.comment_btn_back);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.main_view, new PostFragment())
                        .commit();
            }
        });
    }

    private void listItem() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CommentService service = retrofit.create(CommentService.class);

        Bundle bundle = new Bundle();
        if (bundle == null) {
            Log.d(TAG, "null");
        }
        Log.d(TAG, "not null");
        Log.d(TAG, bundle.getString("id"));
//        String bundleId = bundle.getString("id");
//        Call<List<Comment>> c = service.getData(bundleId);
//
//        Log.d(TAG, c.request().url().toString());
//
//        c.enqueue(new Callback<List<Comment>>() {
//            @Override
//            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
//                commentAdapter = new CommentAdapter(getActivity(),
//                                                    R.layout.fragment_comment_item,
//                                                    response.body());
//                listView.setAdapter(commentAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Comment>> call, Throwable t) {
//                t.printStackTrace();
//                Log.d(TAG, "Something wrong");
//                Toast.makeText(getActivity(), "Something wrong", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
