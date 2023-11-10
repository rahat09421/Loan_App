package com.example.harwaqt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.github.hamzaahmedkhan.counteranimationtextview.CountAnimationTextView;
import com.shuhart.stepview.StepView;

public class Application_Progress_Fragment extends Fragment {
    CountAnimationTextView counter,approved,rejected,pending;
    StepView stepView;
    Button re_apply,summary;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_application_progress, container, false);
        approved = view.findViewById(R.id.loanapproved);
        counter = view.findViewById(R.id.moneyPositive);
        rejected=view.findViewById(R.id.rejected);
        pending=view.findViewById(R.id.pending);
        stepView = view.findViewById(R.id.step_view);
        re_apply=view.findViewById(R.id.re_apply);
        summary=view.findViewById(R.id.summary);
        re_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ApplyNowActivity.class);
                startActivity(i);
            }
        });
        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), OrderInfoActivity.class);
                startActivity(i);
            }
        });
        counter.setAnimationDuration(5000).countAnimation(0, 49598);
        approved.setAnimationDuration(5000).countAnimation(0, 40451);
        rejected.setAnimationDuration(5000).countAnimation(0, 7499);
        pending.setAnimationDuration(5000).countAnimation(0, 1648);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("StepPrefs", Context.MODE_PRIVATE);
        int currentStep = sharedPreferences.getInt("currentStep", 1);

// Update the StepView based on the current step
        stepView.go(currentStep - 1, true);
        return view;
    }
}
