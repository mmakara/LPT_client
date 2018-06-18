package com.lpt.lpt_v4;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserFormFragment extends Fragment {
    public User active_user;

    public static UserFormFragment newInstance(@Nullable User user) {
        UserFormFragment uff = new UserFormFragment();
        if(user != null) {
            uff.active_user = user;
        }

        return uff;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.form_user_fields, container, false);

        if(active_user != null) {
            TextView tvUsername = (TextView) view.findViewById(R.id.userNameInput);
            TextView tvEmail = (TextView) view.findViewById(R.id.emailInput);
            TextView tvName = (TextView) view.findViewById(R.id.firstNameInput);
            TextView tvPassword = (TextView) view.findViewById(R.id.loginPassword);
            tvUsername.setText(active_user.username);
            tvPassword.setText(active_user.password);
            tvName.setText(active_user.first_name);
            tvEmail.setText(active_user.email);
        }

        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(active_user != null) {

        }
    }


}
