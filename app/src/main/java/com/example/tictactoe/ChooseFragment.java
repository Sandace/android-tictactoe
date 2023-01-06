package com.example.tictactoe;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tictactoe.databinding.FragmentChooseBinding;


public class ChooseFragment extends Fragment {
    private FragmentChooseBinding binding;
    Button btnPlayNow;
    EditText playerOneName,playerTwoName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentChooseBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        playerOneName = binding.txtPlayerOneValue;
        playerTwoName = binding.txtPlayerTwoValue;
        binding.btnPlayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle result = new Bundle();
                result.putString("firstPlayerName",playerOneName.getText().toString());
                result.putString("secondPlayerName",playerTwoName.getText().toString());
                getParentFragmentManager().setFragmentResult("dataFromChooseFragment",result);

                if(playerOneName.getText().toString().trim().isEmpty()||playerTwoName.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(),
                            "Name can not be empty",
                            Toast.LENGTH_SHORT).show();
                }else {

                    Fragment fragment = new TicTacToeFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
                    fragmentTransaction.commit();
                }
            }
        });
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager im = (InputMethodManager)
                        getActivity().getSystemService(INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        0);
            }
        });
    }
}