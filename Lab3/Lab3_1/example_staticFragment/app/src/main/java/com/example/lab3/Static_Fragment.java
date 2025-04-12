package com.example.lab3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Static_Fragment extends Fragment {


    public Static_Fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Static_Fragment newInstance(String param1, String param2) {
        Static_Fragment fragment = new Static_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    // layout inflation
    // el container dih el fragmentvuew container
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment4 we inject gowa container , fattachtoroot? feature l google 5aliha false

        return inflater.inflate(R.layout.fragment_static_, container, false);
    }

     // after layour inflation , where u can reference your views
     public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
         super.onViewCreated(view,savedInstanceState);
         Button btn=view.findViewById(R.id.staticbtn);
         btn.setOnClickListener(new View.OnClickListener(){
             public void onClick(View view){
                 // getActivity : 34an ageb el activity eli na 48ala 3aliha
                 Dynamic_Fragment dynamicFragment =new Dynamic_Fragment();
                 FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                 transaction.replace(R.id.frameLayout2,dynamicFragment);
                 transaction.addToBackStack(null);
                 transaction.commit();
             }
         });
     }

}

