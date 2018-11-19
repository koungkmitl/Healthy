package com.example.koung.healthy.post;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.koung.healthy.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends ArrayAdapter<Post> {
    private Context context;
    private List<Post> postList;
    private TextView id, title, body;

    public PostAdapter(@NonNull Context context,
                       int resource,
                       @NonNull List<Post> objects) {
        super(context, resource, objects);
        this.context = context;
        this.postList = (ArrayList<Post>) objects;
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View postItem = LayoutInflater.from(context).inflate(
                R.layout.fragment_post_item,
                parent,
                false);

        id = postItem.findViewById(R.id.post_item_id);
        title = postItem.findViewById(R.id.post_item_title);
        body = postItem.findViewById(R.id.post_item_body);

        Post post = postList.get(position);

        id.setText(post.getId());
        title.setText(post.getTitle());
        body.setText(post.getBody());

        return postItem;
    }
}
