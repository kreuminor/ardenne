package com.prep.ardenne.ardenneprep.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.prep.ardenne.ardenneprep.R;

/**
 * Created by kminor on 30/4/2017.
 */
class OfficeAdapter extends ArrayAdapter<String> {


    private final int type; //0 is administration, 1 is guidance

    private Bitmap image;

    private final String[] name;
    private String[] subtitle;
    private String[] email;
    private String[] phone;
    private String[] bio;

    private Resources res;

    OfficeAdapter(Context context, int t, String[] names) {
        super(context, R.layout.item_office, names);
        type = t;
        name = names;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        res = getContext().getResources();

        getInformation(type, position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_office, parent, false);
        }

        ImageView img = (ImageView) convertView.findViewById(R.id.img);

        TextView txt_name = (TextView) convertView.findViewById(R.id.txt_name);
        TextView txt_subtitle = (TextView) convertView.findViewById(R.id.txt_subtitle);
        ImageButton btn_call = (ImageButton) convertView.findViewById(R.id.btn_call);
        ImageButton btn_email = (ImageButton) convertView.findViewById(R.id.btn_email);

        img.setImageBitmap(image);
        txt_name.setText(name[position]);
        txt_subtitle.setText(subtitle[position]);

        btn_call.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        phoneCall(phone[position]);
                    }
                }
        );

        btn_email.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emailer(email[position]);
                    }
                }
        );

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == 0) {
                    //admin
                    new OfficeDialog(getContext(), getImage(2, type, position), name[position],
                            subtitle[position], email[position], phone[position],
                            bio[position]).show();
                } else {
                    //guidance
                    new OfficeDialog(getContext(), getImage(2, type, position), name[position],
                            subtitle[position], email[position], phone[position], null).show();
                }
            }
        });

        return convertView;
    }

    private void getInformation(int i, int pos) {
        image = getImage(4, i, pos);
        if (i == 0) { //Administration
            subtitle = res.getStringArray(R.array.admin_subtitles);
            email = res.getStringArray(R.array.admin_emails);
            phone = res.getStringArray(R.array.admin_phones);
            bio = res.getStringArray(R.array.admin_bios);
        }
    }

    private Bitmap getImage(int sampleSize, int type, int position) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = sampleSize;

        if (type == 0) {
            switch (position) {
                default:
                    return null; //Required
                case 0:
                    return BitmapFactory.decodeResource(res,
                            R.drawable.admin_hammond, opts);
                case 1:
                    return BitmapFactory.decodeResource(res,
                            R.drawable.admin_dohm, opts);
                case 2:
                    return BitmapFactory.decodeResource(res,
                            R.drawable.admin_gz, opts);
                case 3:
                    return BitmapFactory.decodeResource(res,
                            R.drawable.admin_walroth, opts);
                case 4:
                    return BitmapFactory.decodeResource(res,
                            R.drawable.admin_williams, opts);
                case 5:
                    return BitmapFactory.decodeResource(res,
                            R.drawable.admin_jackson, opts);
                case 6:
                    return BitmapFactory.decodeResource(res,
                            R.drawable.admin_keilitz, opts);
                case 7:
                    return BitmapFactory.decodeResource(res,
                            R.drawable.admin_roberts, opts);
            }
        } else if (type == 1) {
            switch (position) {
                default:
                    return null; //Required
                case 0:
                    return BitmapFactory.decodeResource(res,
                            R.drawable.guidance_gardner, opts);
                case 1:
                    return BitmapFactory.decodeResource(res,
                            R.drawable.guidance_hentes, opts);
                case 2:
                    return BitmapFactory.decodeResource(res,
                            R.drawable.guidance_hall, opts);
                case 3:
                    return BitmapFactory.decodeResource(res,
                            R.drawable.guidance_kernen, opts);
                case 4:
                    return BitmapFactory.decodeResource(res,
                            R.drawable.guidance_mol, opts);
                case 5:
                    return BitmapFactory.decodeResource(res,
                            R.drawable.guidance_vanover, opts);
            }
        } else {
            return null;
        }
    }

    private void phoneCall(String number) {
        Uri dial = Uri.parse("tel:" + number);
        Intent call = new Intent(Intent.ACTION_DIAL, dial);
        getContext().startActivity(call);
    }


    private void emailer(String a) {
        Intent emailing = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", a, null));
        getContext().startActivity(emailing);
    }
}