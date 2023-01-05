package com.example.tictactoe;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tictactoe.databinding.FragmentTicTacToeBinding;


public class TicTacToeFragment extends Fragment implements View.OnClickListener {
    private FragmentTicTacToeBinding binding;
    //counters
    static int playerOneWinCount = 0;
    static int playerTwoWinCount = 0;
    static int drawCount = 0;
    TextView draw,draw2;
    //scores
    TextView playerOneScore,playerTwoScore;
    //
    TextView playerTurn;
    String playerOneName,playerTwoName;
    //
    Button[] buttons = new Button[9];
    private static final int[] BUTTON_IDS = {
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9
    };
    static int count = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        binding = FragmentTicTacToeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("dataFromChooseFragment", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                playerOneName = result.getString("firstPlayerName");
                playerTwoName = result.getString("secondPlayerName");
//              TextView playerOneTextView = view.findViewById(R.id.txt_player_one_name);
//              TextView playerTwoTextView = view.findViewById(R.id.txt_player_two_name);
                TextView playerOneTextView = binding.txtPlayerOneName;
                TextView playerTwoTextView = binding.txtPlayerTwoName;

                if(playerOneName.isEmpty()){
                    playerOneTextView.setText("Player X");
                    playerTurn.setText("Player X turn");
                }else {
                    playerOneTextView.setText(playerOneName.trim()+"(X)");
                    playerTurn.setText(playerOneName.trim()+"'s turn");
                }
                if(playerTwoName.isEmpty()){
                    playerTwoTextView.setText("Player O");
                }else {
                    playerTwoTextView.setText(playerTwoName.trim()+"(0)");
                }
            }
        });
//        playerOneScore = (TextView) view.findViewById(R.id.txt_player_one_score);
//        playerTwoScore = (TextView) view.findViewById(R.id.txt_player_two_score);
//        playerTurn= (TextView) view.findViewById(R.id.txt_player_turn);
//        draw = (TextView) view.findViewById(R.id.txt_player_one_draws);
//        draw2 = (TextView) view.findViewById(R.id.txt_player_two_draws);
        playerOneScore = binding.txtPlayerOneScore;
        playerTwoScore = binding.txtPlayerTwoScore;
        playerTurn = binding.txtPlayerTurn;
        draw = binding.txtPlayerOneDraws;
        draw2 = binding.txtPlayerTwoDraws;

        if(playerOneName!=null){
            playerTurn.setText(playerOneName+"'s turn");
        }
        //adding listener to buttons
        for (int i = 0; i < BUTTON_IDS.length; i++) {
            Button button = (Button) view.findViewById(BUTTON_IDS[i]);
            button.setOnClickListener(this);
            buttons[i]= button;
        }
    }

    @Override
    public void onClick(View view) {
        count++;
        switch (view.getId()) {
            case R.id.button1:
                buttons[0].setText(getButtonText(count));
                buttons[0].setEnabled(false);
                break;
            case R.id.button2:
                buttons[1].setText(getButtonText(count));
                buttons[1].setEnabled(false);
                break;
            case R.id.button3:
                buttons[2].setText(getButtonText(count));
                buttons[2].setEnabled(false);
                break;
            case R.id.button4:
                buttons[3].setText(getButtonText(count));
                buttons[3].setEnabled(false);
                break;
            case R.id.button5:
                buttons[4].setText(getButtonText(count));
                buttons[4].setEnabled(false);
                break;
            case R.id.button6:
                buttons[5].setText(getButtonText(count));
                buttons[5].setEnabled(false);
                break;
            case R.id.button7:
                buttons[6].setText(getButtonText(count));
                buttons[6].setEnabled(false);
                break;
            case R.id.button8:
                buttons[7].setText(getButtonText(count));
                buttons[7].setEnabled(false);
                break;
            case R.id.button9:
                buttons[8].setText(getButtonText(count));
                buttons[8].setEnabled(false);
                break;
            default:
                break;
        }
        if (checkWinner(buttons) == 1) {
            count = 0;
            disableEnableButtons(false);
            showAlertDialog(1);
            playerOneWinCount++;
            playerOneScore.setText(String.valueOf(playerOneWinCount));
            playerTurn.setText(playerTwoName+"'s turn");
        }
        else if(checkWinner(buttons) == 2){
            count = 0;
            disableEnableButtons(false);
            showAlertDialog(2);
            playerTwoWinCount++;
            playerTwoScore.setText(String.valueOf(playerTwoWinCount));
            playerTurn.setText(playerOneName+"'s turn");
        }
        else if(count==9){
            count = 0;
            disableEnableButtons(false);
            showAlertDialog(3);
            drawCount++;
            draw.setText(String.valueOf(drawCount));
            draw2.setText(String.valueOf(drawCount));

        }
    }

    public String getButtonText(int mCount) {
        if (mCount % 2 == 0) {
//            if(playerOneName.isEmpty()){
//                playerTurn.setText("Player X turn");
//            }else{
                playerTurn.setText(playerOneName+"'s turn");
//            }
            return "O";
        } else {
//            if(playerOneName.isEmpty()){
//                playerTurn.setText("Player O turn");
//            }else{
                playerTurn.setText(playerTwoName+"'s turn");
//            }
            return "X";
        }
    }

    public int checkWinner(Button[] buttonValue) {
        String s[] = {buttonValue[0].getText().toString(),buttonValue[1].getText().toString(),buttonValue[2].getText().toString(),
                buttonValue[3].getText().toString(),buttonValue[4].getText().toString(),buttonValue[5].getText().toString(),buttonValue[6].getText().toString(),
                buttonValue[7].getText().toString(),buttonValue[8].getText().toString()
        };
        //row
        for(int k=0;k<3;k++){
            if(s[k * 3].equals("X") && s[k * 3 + 1].equals("X") && s[k * 3 + 2].equals("X")){
                return 1;
            }else if(s[k * 3].equals("O") && s[k * 3 + 1].equals("O") && s[k * 3 + 2].equals("O")){
                return 2;
            }
        }
        //column
        for(int l=0;l<3;l++){
            if(s[l].equals("X") && s[l + 3].equals("X") && s[l + 6].equals("X")){
                return 1;
            }else if(s[l].equals("O") && s[l + 3].equals("O") && s[l + 6].equals("O")){
                return 2;
            }
        }
        //diagonal
        if((s[0].equals(s[4])) && (s[4].equals(s[8])) && (!s[0].equals(""))){
            if(s[4].equals("X")){
                return 1;
            }else if(s[4].equals("O")){
                return 2;
            }
        }
        else if((s[2].equals(s[4])) && (s[4].equals(s[6])) && (!s[2].equals(""))){
            if(s[4].equals("X")){
                return 1;
            }else if(s[4].equals("O")){
                return 2;
            }
        }
        return 0;
    }

    public void showAlertDialog(int value) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("msg").setCancelable(false).setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        disableEnableButtons(true);
//                        if(playerOneName.isEmpty() || playerOneName==null){
//                           playerTurn.setText("Player X turn");
//                        }else{
                            playerTurn.setText(playerOneName+"'s turn");
//                        }
                    }
                }).setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        drawCount=0;
                        playerOneWinCount=0;
                        playerTwoWinCount=0;
                        Navigation.findNavController(requireView()).navigate(R.id.chooseFragment2);
                    }
                });
        AlertDialog alert = builder.create();
        String playerXwon= "Player X ";
        String playerOwon = "Player O ";
        alert.setTitle("XOX0");
        switch (value){
            case 1:
                alert.setMessage((playerOneName.isEmpty()?playerXwon:playerOneName) +" won. Do you want to play again?");
                break;
            case 2:
                alert.setMessage((playerTwoName.isEmpty()?playerOwon:playerTwoName) +" won. Do you want to play again?");
                break;
            case 3:
                alert.setMessage("Draw. Do you like to play again?");
                break;
            default:
                break;
        }
        alert.show();
        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
    }
    public void disableEnableButtons(boolean enableOrDisable){
        for (int k = 0; k < 9; k++) {
            buttons[k].setText("");
            buttons[k].setEnabled(enableOrDisable);
        }
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        OnBackPressedCallback callback = new OnBackPressedCallback(
                true // default to enabled
        ) {
            @Override
            public void handleOnBackPressed() {
                playerOneWinCount=0;
                playerTwoWinCount=0;
                count=0;
                Navigation.findNavController(requireView()).navigate(R.id.chooseFragment2);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(
                this, // LifecycleOwner
                callback);
    }
}