package ir.eatandroid.weather.views.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by elham on 12/13/2017.
 */

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    protected ProgressDialog mProgressDialog;
    protected AlertDialog.Builder mAlertDialog;
    protected BottomSheetDialog bottomSheetDialog;


    protected void needShowProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Please wait ...");
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    protected void needHideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void needShowAlertDialog(String message) {
        mAlertDialog = new AlertDialog.Builder(this);
        mAlertDialog.setTitle("Weather");
        mAlertDialog.setMessage(message);
        mAlertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = mAlertDialog.create();
        dialog.show();
    }


    protected void needShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    protected void needShowSnackbar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }



    @Override
    public void onStop() {
        super.onStop();
        needHideProgressDialog();
    }

}
