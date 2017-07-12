package lab.aikibo.learn_widget_mania_android;

import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextClock;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton rbBeijing;
    private RadioButton rbLondon;
    private RadioButton rbNewyork;
    private EditText editText;
    private Button button;
    private TextClock tClock;
    private CheckBox cbTransparency;
    private CheckBox cbTint;
    private CheckBox cbResize;
    private ImageView imageView;
    private Switch saklar;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exploration_layout);

        initComponent();
    }

    private void initComponent() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rbBeijing = (RadioButton) findViewById(R.id.rbBeijing);
        rbLondon = (RadioButton) findViewById(R.id.rbLondon);
        rbNewyork = (RadioButton) findViewById(R.id.rbNewYork);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        tClock = (TextClock) findViewById(R.id.textClock);

        cbTransparency = (CheckBox) findViewById(R.id.cbTransparency);
        cbTint = (CheckBox) findViewById(R.id.cbTint);
        cbResize = (CheckBox) findViewById(R.id.cbResize);
        imageView = (ImageView) findViewById(R.id.imageView);
        saklar = (Switch) findViewById(R.id.tombol);
        webView = (WebView) findViewById(R.id.webView);

        cbTransparency.setOnCheckedChangeListener(new CbTransparencyOnCheckedChangeListener());
        cbTint.setOnCheckedChangeListener(new CbTintOnCheckedChangeListener());
        cbResize.setOnCheckedChangeListener(new CbResizeOnCheckedChangeListener());
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroupOnCheckedChangeListener());

        webView.loadUrl("http://bppkad.brebeskab.go.id");
        webView.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new ButtonOnClickListener());

        saklar.setOnCheckedChangeListener(new SaklarOnCheckedChangeListener());
    }

    // --- event

    private class SaklarOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked) {
                webView.setVisibility(View.VISIBLE);
            } else {
                webView.setVisibility(View.INVISIBLE);
            }
        }
    }

    private class ButtonOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            button.setText(editText.getText());
        }
    }

    private class RadioGroupOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
            RadioButton rb = (RadioButton) radioGroup.findViewById(checkedId);
            switch(rb.getId()) {
                case R.id.rbLondon:
                    tClock.setTimeZone("Europe/London");
                    break;
                case R.id.rbBeijing:
                    tClock.setTimeZone("CST6CDT");
                    break;
                case R.id.rbNewYork:
                    tClock.setTimeZone("America/New_York");
                    break;
            }
        }
    }

    private class CbResizeOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(cbResize.isChecked()) {
                imageView.setScaleX(2);
                imageView.setScaleY(2);
            } else {
                imageView.setScaleX(1);
                imageView.setScaleY(1);
            }
        }
    }

    private class CbTintOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(cbTint.isChecked()) {
                imageView.setColorFilter(Color.argb(150,255,0,0));
            } else {
                imageView.setColorFilter(Color.argb(0,0,0,0));
            }
        }
    }

    private class CbTransparencyOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(cbTransparency.isChecked()) {
                imageView.setAlpha(.1f);
            } else {
                imageView.setAlpha(1f);
            }
        }
    }
}
