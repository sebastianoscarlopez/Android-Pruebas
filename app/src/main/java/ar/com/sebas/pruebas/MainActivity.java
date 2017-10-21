package ar.com.sebas.pruebas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainListener{

    MainPresenter mainPresenter;
    private TextView txtValue;
    private TextView txtCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this, this);

        txtValue = (TextView)findViewById(R.id.txtValue);
        txtCountDown = (TextView)findViewById(R.id.txtCountDown);

        if(getIntent().getExtras() != null && getIntent().hasExtra("value"))
        {
            txtValue.setText(getIntent().getExtras().getString("value"));
        }else {
            txtValue.setText("Sin valor");
        }
    }

    @Override
    public void onComplete(String value) {
        txtValue.setText(value);
    }

    @Override
    public void onProgess(String value) {
        txtCountDown.setText(value);
    }

    public void btnClose_onClick(View view) {
        finish();
    }

    public void btnAsynCall_onClick(View view) {
        mainPresenter.AsyncCall();
    }
}
