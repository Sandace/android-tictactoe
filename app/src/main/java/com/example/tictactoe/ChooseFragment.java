package com.example.tictactoe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class ChooseFragment extends Fragment {
    Button btnPlayNow;
    EditText playerOneName,playerTwoName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose, container, false);

        btnPlayNow = (Button) view.findViewById(R.id.btn_play_now);
        playerOneName = (EditText) view.findViewById(R.id.txt_player_one_value);
        playerTwoName = (EditText) view.findViewById(R.id.txt_player_two_value);

        btnPlayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle result = new Bundle();
                result.putString("firstPlayerName",playerOneName.getText().toString());
                result.putString("secondPlayerName",playerTwoName.getText().toString());
                getParentFragmentManager().setFragmentResult("dataFromChooseFragment",result);
                Navigation.findNavController(view).navigate(R.id.action_chooseFragment2_to_ticTacToe);
            }
        });
        return view;
    }

}