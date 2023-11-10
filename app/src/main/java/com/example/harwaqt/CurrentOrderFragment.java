package com.example.harwaqt;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class CurrentOrderFragment extends Fragment {
    private DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_order, container, false);
        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Loan_Application");
        // Retrieve data from Firebase
        fetchDataFromFirebase();
        return view;
    }

    private void fetchDataFromFirebase() {
        String currentUserUid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        // Update the DatabaseReference to point to the specific user's node
        DatabaseReference userReference = databaseReference.child(currentUserUid);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataModel userData = dataSnapshot.getValue(DataModel.class);
                    Log.d("YourFragment", "Retrieved data: " + userData);
                    updateUI(userData);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
    private void updateUI(DataModel userData) {
        // Update your UI elements with the retrieved data
        // For example:
        if (getView() != null) {
            // Update your UI elements with the retrieved data
            TextView nameTextView = getView().findViewById(R.id.nameTextView);
            nameTextView.setText(String.format("Name: %s", userData.getName()));

            TextView fatherName = getView().findViewById(R.id.fatherName);
            fatherName.setText(String.format("Father Name: %s", userData.getFatherName()));

            TextView dOB = getView().findViewById(R.id.dateOfBirth);
            dOB.setText(String.format("Date of Birth: %s", userData.getDateOfBirth()));

            TextView loanAmount2 = getView().findViewById(R.id.loanamount2);
            loanAmount2.setText(String.format("Loan Amount: Rs.%s", userData.getLoanAmount1()));

            TextView loanDuration2 = getView().findViewById(R.id.loanduration2);
            loanDuration2.setText(String.format("Loan Duration: %s Days", userData.getLoanDuration1()));

            TextView email2 = getView().findViewById(R.id.email2);
            email2.setText(String.format("Email: %s", userData.getEmail()));

            TextView phone2 = getView().findViewById(R.id.phone2);
            phone2.setText(String.format("Contact Number: %s", userData.getPhone()));

            TextView address2 = getView().findViewById(R.id.address2);
            address2.setText(String.format("Address: %s", userData.getAddress()));
            // Do the same for other UI elements
        }
    }
}