package com.example.tictactoe;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tictactoe.databinding.FragmentChooseBinding;


public class ChooseFragment extends Fragment {
    private FragmentChooseBinding binding;
    Button btnPlayNow;
    EditText playerOneName,playerTwoName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_choose, container, false);
//        btnPlayNow = (Button) view.findViewById(R.id.btn_play_now);
//        playerOneName = (EditText) view.findViewById(R.id.txt_player_one_value);
//        playerTwoName = (EditText) view.findViewById(R.id.txt_player_two_value);

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
                    Toast toast = Toast.makeText(getContext(),
                            "Name can not be empty",
                            Toast.LENGTH_SHORT);
                    View toastView = toast.getView();
                    toastView.setBackgroundColor(Color.RED);
                    toast.show();
                }else {
                    Navigation.findNavController(view).navigate(R.id.action_chooseFragment2_to_ticTacToe);
                }
            }
        });
//
    }
}