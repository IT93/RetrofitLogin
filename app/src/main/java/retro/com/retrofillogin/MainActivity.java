package retro.com.retrofillogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvResultEmail;
    String resultEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            resultEmail = extras.getString("result_email");
        tvResultEmail.setText(resultEmail);
    }

    private void initComponents() {
        tvResultEmail = (TextView) findViewById(R.id.tvResultEmail);
    }
}
