package com.example.koung.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onClickRegister();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    public void onClickRegister() {
        Button btn = getView().findViewById(R.id.register_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _username = ((EditText) getView().findViewById(R.id.register_username)).getText().toString();
                String _password = ((EditText) getView().findViewById(R.id.register_password)).getText().toString();
                String _name = ((EditText) getView().findViewById(R.id.register_age)).getText().toString();
                String _age = ((EditText) getView().findViewById(R.id.register_age)).getText().toString();

                if (_username.isEmpty() || _password.isEmpty() || _name.isEmpty() || _age.isEmpty()) {
                    Toast.makeText(getActivity(), "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                    Log.d("REGISTER", "Register failure some attribute was missing");
                } else if (_username.equals("admin")) {
                    Toast.makeText(getActivity(), "username already exists", Toast.LENGTH_SHORT).show();
                    Log.d("REGISTER", "Register failure attribute username is exists");
                } else {
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    Log.d("REGISTER", "Register successful");

                    ((EditText) getView().findViewById(R.id.register_username)).getText().clear();
                    ((EditText) getView().findViewById(R.id.register_name)).getText().clear();
                    ((EditText) getView().findViewById(R.id.register_age)).getText().clear();
                    ((EditText) getView().findViewById(R.id.register_password)).getText().clear();
                }
            }
        });
    }
}
