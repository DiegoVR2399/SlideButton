package pe.com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import slidebutton.com.pe.SlideButton;

public class MainActivity extends AppCompatActivity {

    private SlideButton sb_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb_id = findViewById(R.id.sb_id);

        sb_id.OnSetListenerSlideButton(new SlideButton.setListenerSlideButton() {
            @Override
            public void onButtonOff() {
                Toast.makeText(MainActivity.this, "Off", Toast.LENGTH_SHORT).show();
                sb_id.sb_setText("Desliza para encender");
                sb_id.sb_setTextColor(getResources().getColor(R.color.colorAccent));
            }

            @Override
            public void onButtonOn() {
                Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT).show();
                sb_id.sb_setText("Desliza para apagar");
                sb_id.sb_setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        });

    }
}
