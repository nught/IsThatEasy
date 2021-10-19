package com.example.isthateasy2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AddTaskActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Button closePopupBtn, multipleChoice, dropDown, typingAnAnswer, typingAnswerCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        bottomNavigationView = findViewById(R.id.bottomNavigationViewForAddTask);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.settingMenuItem:
                        return true;
                    case R.id.addMenuItem:
                        //reference from https://android--code.blogspot.com/2016/01/android-popup-window-example.html


                        LayoutInflater inflater = (LayoutInflater) AddTaskActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                        View popupView = inflater.inflate(R.layout.add_task_popup, null);
                        PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

                                popupWindow.showAsDropDown(popupView, 0, 0);
                                popupWindow.setFocusable(true);
                                closePopupBtn = (Button) popupView.findViewById(R.id.closePopupBtn);

                        View.OnClickListener onClickListener_choosing_answering_way = new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int id = view.getId();
                                switch (id){
                                    case R.id.drop_down:
                                        popupWindow.dismiss();
                                        break;
                                    case R.id.typing_an_answer:
                                        popupWindow.dismiss();



                                        View popupViewTypingAnswer = inflater.inflate(R.layout.typing_an_answer_popup, null);
                                        PopupWindow popupWindowTypingAnswer = new PopupWindow(popupViewTypingAnswer, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

                                        popupWindowTypingAnswer.showAsDropDown(popupViewTypingAnswer, 0, 0);
                                        popupWindowTypingAnswer.setFocusable(true);
                                        typingAnswerCancelButton = (Button) popupViewTypingAnswer.findViewById(R.id.typing_an_answer_cancel_btn);

                                        typingAnswerCancelButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                popupWindowTypingAnswer.dismiss();
                                            }
                                        });


                                        break;
                                    case R.id.multiple_choice:
                                        popupWindow.dismiss();
                                        break;
                                    default:
                                        return;
                                }

                            }
                        };

                                dropDown = popupView.findViewById(R.id.drop_down);
                                typingAnAnswer = popupView.findViewById(R.id.typing_an_answer);
                                multipleChoice = popupView.findViewById(R.id.multiple_choice);

                                dropDown.setOnClickListener(onClickListener_choosing_answering_way);
                                typingAnAnswer.setOnClickListener(onClickListener_choosing_answering_way);
                                multipleChoice.setOnClickListener(onClickListener_choosing_answering_way);

                                closePopupBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        popupWindow.dismiss();
                                    }
                                });

                        return true;
                    case R.id.FinishMenuItem:

                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}