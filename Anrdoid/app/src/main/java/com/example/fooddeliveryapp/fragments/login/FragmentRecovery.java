package com.example.fooddeliveryapp.fragments.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.util.Cechers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRecovery#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRecovery extends Fragment implements View.OnClickListener,TextWatcher {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LinearLayout step1,step2,step3;
    private Button nexts1,pvs2,nexts2,nexts3,pvs3;
    private TextView infotext;
    private EditText email, code,npass,cpass;
    public FragmentRecovery() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRecovery.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRecovery newInstance(String param1, String param2) {
        FragmentRecovery fragment = new FragmentRecovery();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recovery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        step1=view.findViewById(R.id.linearLayout_step1);
        step2=view.findViewById(R.id.linearLayout_step2);
        step3=view.findViewById(R.id.linearLayout_step3);

        infotext =view.findViewById(R.id.textViewInfo);
        nexts1 =view.findViewById(R.id.button3);
        pvs2 =view.findViewById(R.id.button4);
        nexts2=view.findViewById(R.id.button5);
        pvs3=view.findViewById(R.id.button6);
        nexts3=view.findViewById(R.id.button7);
        nexts1.setOnClickListener(this);
        nexts2.setOnClickListener(this);
        nexts3.setOnClickListener(this);
        pvs2.setOnClickListener(this);
        pvs3.setOnClickListener(this);
        email = view.findViewById(R.id.recovery_email);
        code = view.findViewById(R.id.recovery_code);
        npass = view.findViewById(R.id.editTextTextPassword);
        cpass =view.findViewById(R.id.editTextTextPassword2);
        email.addTextChangedListener(this);
        code.addTextChangedListener(this);
        npass.addTextChangedListener(this);
        cpass.addTextChangedListener(this);
        step1();
    }

    @Override
    public void onClick(View view) {
       switch(view.getId())
       {
           case R.id.button3: button3click(); break;
           case R.id.button4: button4click(); break;
           case R.id.button5: button5click(); break;
           case R.id.button6: button6click(); break;
           case R.id.button7: button7click(); break;

       }
    }
    private void button3click()//sa introdus adresa de email
    {
        if(Cechers.isValidEmail(email.getText().toString())) {
            // to do make request to server for change the password
            step2();
        }
        else {
            toast(R.string.invalid_email);
            email.requestFocus();
        }
    }
    private void button4click()
    {
        step1();
    }
    private void button5click()
    {
        //confirm the code is the same
        step3();
    }
    private void button6click()
    {
        step1();
    }
    private void toast(int res)
    {
        Toast.makeText(getContext(),res,Toast.LENGTH_LONG).show();
    }
    private void button7click()
    {
        if(cpass.getText().toString().compareTo(npass.getText().toString())!=0)
        {
            toast(R.string.pass_not_match);
            return;
        }
        //set the pasord to npass
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainerView, new Login_fragment());
        ft.commit();
    }
    private void step1()
    {
        hide();
        step1.setVisibility(View.VISIBLE);
        infotext.setText(R.string.pass_recovery_step1);
        if(email.getText().length()==0)
        nexts1.setEnabled(false);

    }
    private void step2()
    {
        hide();
        step2.setVisibility(View.VISIBLE);
        infotext.setText(R.string.pass_recovery_step2);
        nexts2.setEnabled(false);
        code.setText("");
    }
    private void step3()
    {
        hide();
        step3.setVisibility(View.VISIBLE);
        infotext.setText(R.string.pass_recovery_step3);
        nexts3.setEnabled(false);
        cpass.setText("");
        npass.setText("");
    }

    public void hide()
    {
        step1.setVisibility(View.GONE);
        step2.setVisibility(View.GONE);
        step3.setVisibility(View.GONE);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(step1.getVisibility()==View.VISIBLE)
        {
            if(email.getText().toString().trim().length()>0)
                nexts1.setEnabled(true);
            else
                nexts1.setEnabled(false);
        }
        if(step2.getVisibility()==View.VISIBLE)
        {
            if(code.getText().toString().trim().length()>0)
                nexts2.setEnabled(true);
            else
                nexts2.setEnabled(false);
        }
        if(step3.getVisibility()==View.VISIBLE)
        {
            if(npass.getText().length()==cpass.getText().length()&&npass.getText().length()>0)
                nexts3.setEnabled(true);
            else
                nexts3.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}