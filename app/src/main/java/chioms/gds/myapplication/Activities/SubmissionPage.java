package chioms.gds.myapplication.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.Objects;

import chioms.gds.myapplication.PostApi.PostApi;
import chioms.gds.myapplication.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionPage extends AppCompatActivity {

    private EditText mNameTxt;
    private  EditText mLNameTxt;
    private  EditText mEmailTxt;
    private  EditText mUrl;
    private Button mAcceptBtn;
    Toolbar tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission_page);
        tool = findViewById(R.id.toolbar);
        setSupportActionBar(tool);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


         /*   getSupportActionBar().setTitle(null);
        getSupportActionBar().setLogo(R.drawable.gads_header);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);*/

        mNameTxt = findViewById(R.id.name_txt);
        mLNameTxt = findViewById(R.id.lname_txt);
        mEmailTxt = findViewById(R.id.email_txt);
        mUrl = findViewById(R.id.project_link);
        mAcceptBtn = findViewById(R.id.accept_btn);

        mAcceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate(mNameTxt) && validateEmail() && validate(mLNameTxt) && validate(mUrl)){
                    dialogSetup();
                }
            }
        });
    }

    private boolean validateEmail(){
        String email = mEmailTxt.getText().toString().trim();

        if (email.isEmpty()|| !isValidEmail(email)){
            mEmailTxt.setError("Email is not valid.");
            mEmailTxt.requestFocus();
            return false;
        }
        return true;
    }

    private static boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validate (EditText editText){

        if(editText.getText().toString().trim().length() >0){
            return true;
        }
        editText.setError("Please fill this");
        editText.requestFocus();
        return false;
    }

    private void dialogSetup(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.first_dialog_box, null);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

        Button dialogButton = dialog.findViewById(R.id.dialogButton);
        ImageView dialogCancel = dialog.findViewById(R.id.dialog_cancel);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                setupConnection();
            }
        });

        dialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    private void setupConnection() {
        (PostApi.getClient().submission(mEmailTxt.getText().toString().trim(),
                mNameTxt.getText().toString().trim(),
                mLNameTxt.getText().toString().trim(),
                mUrl.getText().toString().trim())).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                successfulDialog();
                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                unsuccessfulDialog();
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void successfulDialog(){
        int dia = 5000;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.positive_dialog, null);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, dia);
    }

    private void unsuccessfulDialog(){
        int dia = 5000;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.negative_dialog, null);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, dia);
    }

}
