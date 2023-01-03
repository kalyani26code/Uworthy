package com.example.uworthy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

public class Loginactivity extends AppCompatActivity {
    TextView registerUser;
    EditText username, password1;
    Button loginButton;
    String email, password;
  private   FirebaseAuth firebaseAuth;

    @Override
    protected void onStart() {
        super.onStart();
//        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//        updateUI(currentUser);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){

        }

        registerUser = (TextView)findViewById(R.id.textView5);
        username = (EditText)findViewById(R.id.emailedit);
        password1 = (EditText)findViewById(R.id.editText2);
        loginButton = (Button)findViewById(R.id.button5);


        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loginactivity.this, Registerhome.class));
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuser();
 }
                });

//                if(email.equals("")){
//                    username.setError("can't be blank");
//                }
//                else if(pass.equals("")){
//                    password.setError("can't be blank");
//                }
//                else{
//                    String url = "https://uworthy-6fd8e.firebaseio.com/All_Image_Uploads_Database.json";
//                    final ProgressDialog pd = new ProgressDialog(Loginactivity.this);
//                    pd.setMessage("Loading...");
//                    pd.show();
//
//                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
//                        @Override
//                        public void onResponse(String s) {
//                            if(s.equals("null")){
//                                Toast.makeText(Loginactivity.this, "user not found", Toast.LENGTH_LONG).show();
//                            }
//                            else{
//                                try {
//                                    JSONObject obj = new JSONObject(s);
//
//                                    if(!obj.has(email)){
//                                        Toast.makeText(Loginactivity.this, "user not found", Toast.LENGTH_LONG).show();
//                                    }
//                                    else if(obj.getJSONObject(user).getString("password").equals(pass)){
//                                        UserDetails.useername = user;
//                                        UserDetails.password = pass;
//                                       // startActivity(new Intent(Login.this, Users.class));
//                                        Toast.makeText(Loginactivity.this, "user  found", Toast.LENGTH_LONG).show();
//
//                                    }
//                                    else {
//                                        Toast.makeText(Loginactivity.this, "incorrect password", Toast.LENGTH_LONG).show();
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            pd.dismiss();
//                        }
//                    },new Response.ErrorListener(){
//                        @Override
//                        public void onErrorResponse(VolleyError volleyError) {
//                            System.out.println("" + volleyError);
//                            pd.dismiss();
//                        }
//                    });
//
//                    RequestQueue rQueue = Volley.newRequestQueue(Loginactivity.this);
//                    rQueue.add(request);
             //   }


    }

    public void loginuser() {
        email = username.getText().toString().trim();
        password = password1.getText().toString().trim();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Loginactivity.this, "Login Succe..", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Loginactivity.this, "user not found..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//       firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
//           @Override
//           public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
//               if (task.isSuccessful()) {
//                   // Sign in success, update UI with the signed-in user's information
//                   Log.d(TAG, "createUserWithEmail:success");
//                   FirebaseUser user = mAuth.getCurrentUser();
//                   updateUI(user);
//               } else {
//                   // If sign in fails, display a message to the user.
//                   Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                   Toast.makeText(Loginactivity.this, "Authentication failed.",
//                           Toast.LENGTH_SHORT).show();
//                   updateUI(null);
               }
//
//           }
//       });
  //  }

}



