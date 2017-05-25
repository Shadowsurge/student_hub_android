package com.example.nathan.studenthub.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.nathan.studenthub.R;
import com.example.nathan.studenthub.model.*;

/**
 * Created by Nathan on 14/05/2017.
 *
 * Fragment responsible for handling the login process
 */

public class LoginFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.login, container, false);

        LoadForm(view);
        return view;
    }

    private void LoadForm(View view)
    {
        final EditText usernameInput = (EditText) view.findViewById(R.id.loginUsernameInput);
        final EditText passwordInput = (EditText) view.findViewById(R.id.loginPasswordInput);

        Button submit = (Button) view.findViewById(R.id.loginSubmitButton);

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String username = usernameInput.getText().toString();
                final String password = passwordInput.getText().toString();

                final Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        if(response.equals("Logged in"))
                        {
                            ((MainActivity) getActivity()).LogUser(username);
                        }
                    }
                };
                PostData request = new PostData(username, password, Config.LOGIN_URL, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(request);
            }
        });
    }
}
