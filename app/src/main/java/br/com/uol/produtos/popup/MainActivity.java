package br.com.uol.produtos.popup;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivivy;

    private RelativeLayout mRelativeLayout;
    private TextView mTermsDescription;
    private Button mButton;

    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        mActivivy = MainActivity.this;

        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);
        mButton = (Button) findViewById(R.id.btn);

        mButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                View customView = inflater.inflate(R.layout.custom_layout, null);

                mPopupWindow = new PopupWindow(
                        customView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

                if (Build.VERSION.SDK_INT>=21) {
                    mPopupWindow.setElevation(5.0f);
                }

                mTermsDescription = (TextView) customView.findViewById(R.id.txt_popup_description);
                mTermsDescription.setMovementMethod(LinkMovementMethod.getInstance());

                Button closeButton = (Button) customView.findViewById(R.id.btn_popup_cancel);

                closeButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        finish();
                        System.exit(0);
                    }
                });

                Button okButton = (Button) customView.findViewById(R.id.btn_popup_ok);

                okButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                    }
                });

                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0,0);
            }
        });
    }
}
