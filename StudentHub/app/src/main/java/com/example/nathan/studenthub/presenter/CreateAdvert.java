package com.example.nathan.studenthub.presenter;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.nathan.studenthub.R;
import com.example.nathan.studenthub.model.Config;
import com.example.nathan.studenthub.model.PostData;
import com.example.nathan.studenthub.model.User;

public class CreateAdvert extends NavDrawer
{
    private EditText titleInput;
    private EditText contentInput;
    private EditText phoneInput;
    private EditText emailInput;
    private Spinner categoryDropdown;
    private String categoryChoice;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.LoadCurrentView(R.layout.activity_create_advert);
        super.SetupNav();

        CreateUI();
    }

    private void CreateUI()
    {
        titleInput = (EditText) findViewById(R.id.advertTitleInput);
        contentInput = (EditText) findViewById(R.id.advertContentInput);
        phoneInput = (EditText) findViewById(R.id.advertPhoneInput);
        emailInput = (EditText) findViewById(R.id.advertEmailInput);
        categoryDropdown = (Spinner) findViewById(R.id.advertCategoryInput);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                                                        getResources().getStringArray(R.array.category_array));

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryDropdown.setAdapter(categoryAdapter);


        Button submitButton = (Button) findViewById(R.id.advertSubmitButton);
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                categoryChoice = categoryDropdown.getSelectedItem().toString();
                checkInputData();
            }
        });
    }

    private void checkInputData()
    {
        boolean isTitleEmpty = false;
        boolean isContentEmpty = false;
        boolean isEmailEmpty = false;

        if(contentInput.getText().toString().trim().length() == 0)
        {
            contentInput.setError("This field is required");
            isContentEmpty = true;
        }

        if(emailInput.getText().toString().trim().length() == 0)
        {
            emailInput.setError("This field is required");
            isEmailEmpty = true;
        }

        if(titleInput.getText().toString().trim().length() == 0)
        {
            titleInput.setError("This field is required");
            isTitleEmpty = true;
        }

        if(!isTitleEmpty && !isContentEmpty && !isEmailEmpty)
        {
            sendData();
        }
    }

    private void sendData()
    {
        Toast.makeText(this, "Uploading...", Toast.LENGTH_SHORT).show();
        User user = super.getUser();

        String title = titleInput.getText().toString();
        String content = contentInput.getText().toString();
        String email = emailInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String category = categoryChoice;

        final Response.Listener<String> responseListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if(response.equals("Success"))
                {
                    Toast.makeText(CreateAdvert.this, "Advert Submitted!", Toast.LENGTH_SHORT).show();
                    //TODO - redirect to manage adverts, with new advert status
                }
                else
                {
                    Toast.makeText(CreateAdvert.this, "Advert was not submitted, please try again.", Toast.LENGTH_LONG).show();
                }
            }
        };
        PostData request = new PostData(title, content, category, email, phone, user.get_id(), user.getUsername(), Config.CREATE_ADVERT_URL, responseListener);
        RequestQueue queue = Volley.newRequestQueue(CreateAdvert.this);
        queue.add(request);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        return super.dispatchTouchEvent(ev);
    }
}
