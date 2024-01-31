package com.outspin.app.ui.radar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.outspin.app.R;
import com.outspin.app.data.DummieData;
import com.outspin.app.databinding.FragmentRadarBinding;
import com.outspin.app.models.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class RadarFragment extends Fragment {

    private RadarViewModel radarViewModel;
    private FragmentRadarBinding binding;

    private GridView gridView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        radarViewModel =
                new ViewModelProvider(this).get(RadarViewModel.class);

        binding = FragmentRadarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        gridView = binding.gvRadar;

        RadarGridAdapter gridAdapter =
                new RadarGridAdapter(root.getContext(), new ArrayList<>(DummieData.DUMMY_USERS_FULL));
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(),R.style.BottomSheetDialogTheme);
               View contactView = LayoutInflater.from(getContext()).inflate(R.layout.layout_contact,
                       (LinearLayout) view.findViewById(R.id.contact_container));

               bottomSheetDialog.setContentView(contactView);
               bottomSheetDialog.show();
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ShapeableImageView myThumbProfile = Objects.requireNonNull(getView()).findViewById(R.id.my_thumbnail);
        String urlPath = "https://turingnotes.com/wp-content/uploads/2022/02/photo14.jpeg";
        Picasso.with(getContext())
                .load(urlPath)
                .resize(150, 150)
                .centerCrop()
                .into(myThumbProfile);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static class RadarGridAdapter extends BaseAdapter {
        private final ArrayList<User> users;
        private final LayoutInflater inflater;

        public RadarGridAdapter(Context context, ArrayList<User> users) {
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.users = users;
        }

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public Object getItem(int i) {
            return users.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = inflater.inflate(R.layout.radar_item, parent, false);

                ShapeableImageView thumbProfile = convertView.findViewById(R.id.my_thumbnail);

                String uriPath = users.get(i).getPhotoURL();
                Picasso.with(parent.getContext())
                        .load(uriPath)
                        .resize(150, 150)           // HARDCODED SHIT ***
                        .centerCrop()
                        .into(thumbProfile);

                if(i < 8)   //  simulate friends
                    thumbProfile.setStrokeColorResource(R.color.green);
            }

            return convertView;
        }
    }
}
