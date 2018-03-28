package user.admin.com.myapplication;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

class MissingPeopleAdapter extends ArrayAdapter<MissingPeople> {
    public MissingPeopleAdapter(Context context, int resource, List<MissingPeople> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.missing_card, parent, false);
        }

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.MissingImage);
        TextView missingname = (TextView) convertView.findViewById(R.id.missing_name);
        TextView missingage = (TextView) convertView.findViewById(R.id.missing_age);

        MissingPeopleData missingPeopleData = new MissingPeopleData();

            Glide.with(photoImageView.getContext())
                    .load(missingPeopleData.getphotoUrl())
                    .into(photoImageView);
            missingname.setText(missingPeopleData.getName());
        missingage.setText(missingPeopleData.getAge());

        return convertView;
    }
}
