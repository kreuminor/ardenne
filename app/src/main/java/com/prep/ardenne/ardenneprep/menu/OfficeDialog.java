package com.prep.ardenne.ardenneprep.menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prep.ardenne.ardenneprep.R;

/**
 * Created by kminor on 30/4/2017.
 */

class OfficeDialog extends Activity{

    private final Context context;

    private final Bitmap image;

    private final String name;
    private final String subtitle;
    private final String email;
    private final String phone;
    private final String bio;

    OfficeDialog(Context c, Bitmap img, String n,
                 String s, String e, String p,
                 @Nullable String b) {
        context = c;
        image = img;
        name = n;
        subtitle = s;
        email = e;
        phone = p;
        bio = b;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_office);


    }
    public void show() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        @SuppressLint("InflateParams")
        View content = LayoutInflater.from(context).inflate(R.layout.dialog_office, null);

        ImageView img = (ImageView) content.findViewById(R.id.imgInfo);
        TextView txtName = (TextView) content.findViewById(R.id.txtName);
        TextView txtSubtitle = (TextView) content.findViewById(R.id.txtSubtitle);
        TextView txtEmail = (TextView) content.findViewById(R.id.txtEmail);
        TextView txtPhone = (TextView) content.findViewById(R.id.txtPhone);
        TextView txtBio = (TextView) content.findViewById(R.id.txtBio);

        img.setImageBitmap(image);
        txtName.setText(name);
        txtSubtitle.setText(subtitle);
        txtEmail.setText(email);
        txtPhone.setText(phone);
        if (bio != null) {
            txtBio.setText(bio);
        }

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface d, int i) {
                d.dismiss();
            }
        });
        builder.setView(content);
        builder.show();
    }
}
