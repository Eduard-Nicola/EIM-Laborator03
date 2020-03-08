package lab03.eim.systems.cs.pub.ro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PhoneDialerActivity extends AppCompatActivity {

    private static final int REQUEST_PHONE_CALL = 1;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonStar;
    private Button buttonHash;
    private TextView numberDisplay;
    private ImageButton eraseButton;
    private ImageButton callButton;
    private ImageButton hangupButton;

    class GenericButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            numberDisplay.setText(numberDisplay.getText().toString() + ((Button) v).getText().toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_phone_dialer);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonStar = findViewById(R.id.buttonStar);
        buttonHash = findViewById(R.id.buttonHash);
        numberDisplay = findViewById(R.id.numberDisplay);
        eraseButton = findViewById(R.id.eraseButton);
        callButton = findViewById(R.id.callButton);
        hangupButton = findViewById(R.id.hangupButton);

        button0.setOnClickListener(new GenericButtonClickListener());
        button1.setOnClickListener(new GenericButtonClickListener());
        button2.setOnClickListener(new GenericButtonClickListener());
        button3.setOnClickListener(new GenericButtonClickListener());
        button4.setOnClickListener(new GenericButtonClickListener());
        button5.setOnClickListener(new GenericButtonClickListener());
        button6.setOnClickListener(new GenericButtonClickListener());
        button7.setOnClickListener(new GenericButtonClickListener());
        button8.setOnClickListener(new GenericButtonClickListener());
        button9.setOnClickListener(new GenericButtonClickListener());
        buttonStar.setOnClickListener(new GenericButtonClickListener());
        buttonHash.setOnClickListener(new GenericButtonClickListener());

        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = numberDisplay.getText().toString();
                if (text.length() != 0) {
                    numberDisplay.setText(text.substring(0, text.length() - 1));
                }
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            PhoneDialerActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            REQUEST_PHONE_CALL);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + numberDisplay.getText().toString()));
                    startActivity(intent);
                }
            }
        });

        hangupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
