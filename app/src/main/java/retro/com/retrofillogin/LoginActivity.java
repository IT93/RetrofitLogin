package retro.com.retrofillogin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail;
    EditText etPassword;
    Button btnLogin;

    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mContext = this;
        mApiService = UtilsApi.getAPIService();
        initComponents();

    }

    private void initComponents() {

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestLogin();
            }
        });
    }

    private void requestLogin() {
        String email = etEmail.getText().toString();
        String pass = etPassword.getText().toString();
        if (email.equals("")){
            Toast.makeText(getApplicationContext(), "Email harus diisi", Toast.LENGTH_SHORT);
        }else if(pass.equals("")){
            Toast.makeText(getApplicationContext(), "Password harus diisi", Toast.LENGTH_SHORT);
        }else {
            loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
            mApiService.loginRequest(etEmail.getText().toString(), etPassword.getText().toString()).enqueue(new Callback<List<Item>>() {
                @Override
                public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
//                    loading.dismiss();
                    if(response.body().size() > 0) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        loading.dismiss();
                    }else{
                        Toast.makeText(getApplicationContext(), "username dan password salah", Toast.LENGTH_SHORT);
                        loading.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<List<Item>> call, Throwable t) {
                    Log.d("errorxxx", t.getMessage());
                    loading.dismiss();

                }
            });
        }

        /*(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.body().get(0).getEmail().toString()) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    loading.dismiss();
                }else{
                    Toast.makeText(getApplicationContext(), "username dan password salah", Toast.LENGTH_SHORT);
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.d("errorxxx", t.getMessage());
//                loading.dismiss();
            }

        });
*/


                /*.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());


                                if (jsonRESULTS.getString("status").equals("1")) {

                                    Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                                    String email = jsonRESULTS.getString("email");
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.putExtra("result_email", email);
                                    startActivity(intent);
                                } else {
                                    String error_message = jsonRESULTS.getString("error");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });*/
    }
}
