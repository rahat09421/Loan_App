package com.example.harwaqt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

public class ProfileFragment extends Fragment {
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        View logout = root.findViewById(R.id.logout);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        View customer_care = root.findViewById(R.id.customer_service);
        View reset = root.findViewById(R.id.resetpassword);
        View aboutUs = root.findViewById(R.id.aboutus);
        View voucher = root.findViewById(R.id.voucher);
        View orderInfo = root.findViewById(R.id.orderinfo);
        View loanDirection = root.findViewById(R.id.loanDirections);
        View followUs = root.findViewById(R.id.followUs);

        TextView userEmailTextView = root.findViewById(R.id.usernameTextView);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // User is signed in
            String userEmail = currentUser.getEmail();

            // Set the user email to the TextView
            if (userEmailTextView != null) {
                userEmailTextView.setText(userEmail);
            }
        }
        voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), VoucherActivity.class);
                startActivity(i);
            }
        });
        followUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), FollowUsActivity.class);
                startActivity(i);
            }
        });
        loanDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LoanDirectionsActivity.class);
                startActivity(i);
            }
        });
        orderInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), OrderInfoActivity.class);
                startActivity(i);
            }
        });


        customer_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CustomerCareActivity.class);
                startActivity(i);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Forget_Password.class);
                startActivity(i);
            }
        });
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showWarningDialogBox();
            }
        });
        return root;
    }

    public void showWarningDialogBox() {
        SweetAlertDialog pbox = new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE);
        pbox.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pbox.setTitleText("Logout");
        pbox.setCancelable(false);
        pbox.setContentText("Are you sure to Logout?");
        pbox.setConfirmText("Logout");
        pbox.setCancelText("Cancel");
        pbox.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                auth.signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        pbox.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                pbox.dismiss();
            }
        });
        pbox.show();
    }

}