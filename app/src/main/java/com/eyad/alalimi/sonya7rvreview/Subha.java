package com.eyad.alalimi.sonya7rvreview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.eyad.alalimi.sonya7rvreview.other.ActivityUtils;

public class Subha extends AppCompatActivity implements View.OnClickListener {

    private ZikrView mZikr;
    private TextView tvZekrText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subha);
        ActivityUtils.setupToolbar(this, R.string.subhaaaa);

        tvZekrText = findViewById(R.id.tv_zeker_text);
        mZikr = findViewById(R.id.zikr_counter);
        mZikr.setOnClickListener(this);
        // mZikr.setMax(33);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.zikr_counter) {
            mZikr.click();
        } else if (v.getId() == R.id.btn_reset) {
            mZikr.reset();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sebha, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_zekr) {
            showEnterTextDialog();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    private void showEnterTextDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.action_add_zekr);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String mText = input.getText().toString();
                tvZekrText.setText(mText);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
