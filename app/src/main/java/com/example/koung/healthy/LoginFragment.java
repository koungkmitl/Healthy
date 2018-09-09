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
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onClickBtn();
        onClickRegister();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    private void onClickBtn() {
        Button btn = getView().findViewById(R.id.login_btn);
        final EditText login = getView().findViewById(R.id.login_username);
        final EditText password = getView().findViewById(R.id.login_password);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _login = login.getText().toString();
                String _password = password.getText().toString();

                if (_login.isEmpty() || _password.isEmpty()) {
                    Toast.makeText(getActivity(), "Fill the empty", Toast.LENGTH_SHORT).show();
                    Log.d("LOGIN", "Login failure some attribute was missing");
                } else if (_login.equals("admin") && _password.equals("admin")) {
                    Toast.makeText(getActivity(), "Login passed", Toast.LENGTH_SHORT).show();
                    Log.d("LOGIN", "Login passed");
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new MenuFragment())
                            .addToBackStack(null)
                            .commit();
                } else {
                    Log.d("LOGIN", "Login failure");
                    Toast.makeText(getActivity(), "password invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onClickRegister() {
        TextView textView = getView().findViewById(R.id.login_register);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new RegisterFragment())
                            .addToBackStack(null)
                        .commit();
            }
        });
    }

}
