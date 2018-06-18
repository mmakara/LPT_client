package com.lpt.lpt_v4;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileFragment extends Fragment {
    public User active_user;

    public static ProfileFragment newInstance(@Nullable  User user) {
        ProfileFragment pf = new ProfileFragment();
        if(user != null) {
            pf.active_user = user;
        }

        return pf;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.replace(R.id.profile_container, UserFormFragment.newInstance(active_user));
        ft.commit();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        loadFragment(UserFormFragment.newInstance(this.active_user));

        return view;
    }

    protected void loadFragment(Fragment fragment) {

    }


}
