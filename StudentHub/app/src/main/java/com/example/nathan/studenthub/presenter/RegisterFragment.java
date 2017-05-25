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
 * Fragment that is responsible for registering a user
 */

public class RegisterFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.register, container, false);

        LoadForm(view);
        return view;
    }

    private void LoadForm(View view)
    {
        final EditText usernameInput = (EditText) view.findViewById(R.id.registerUsernameInput);
        final EditText passwordInput = (EditText) view.findViewById(R.id.registerPasswordInput);
        final EditText confirmPasswordInput = (EditText) view.findViewById(R.id.registerPasswordConfirm);
        final EditText emailInput = (EditText) view.findViewById(R.id.registerEmailInput);
        final EditText confirmEmailInput = (EditText) view.findViewById(R.id.registerEmailConfirm);

        Button submit = (Button) view.findViewById(R.id.registerSubmitButton);
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String username = usernameInput.getText().toString();
                final String password = passwordInput.getText().toString();
                final String email = emailInput.getText().toString();

                boolean isMatchingPasswords = true;
                boolean isMatchingEmail = true;

                if(!password.equals(confirmPasswordInput.getText().toString()))
                {
                    confirmPasswordInput.setError(getString(R.string.password_match_error));
                    passwordInput.setError(getString(R.string.password_match_error));
                    isMatchingPasswords = false;
                }

                if(!email.equals(confirmEmailInput.getText().toString()))
                {
                    emailInput.setError(getString(R.string.email_match_error));
                    confirmEmailInput.setError(getString(R.string.email_match_error));
                    isMatchingEmail = false;
                }

                if(isMatchingEmail && isMatchingPasswords)
                {
                    Response.Listener<String> responseListener = new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            if(response.equals("It worked"))
                            {
                                ((MainActivity) getActivity()).LogUser(username);
                            }
                        }
                    };
                    PostData request = new PostData(username, password, email, Config.REGISTER_URL, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    queue.add(request);
                }
            }
        });
    }
}