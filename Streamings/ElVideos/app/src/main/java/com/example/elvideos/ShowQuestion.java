package com.example.elvideos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowQuestion extends AppCompatActivity {
    private TextView ques, stat;
    private Button op1, op2, op3, op4, back, forward;
    private ListView listView;
    private DatabaseReference databaseReference;
    private List<Question> questionList;
    private ShowAllQuestion showAllQuestion;
    private int i;
    private String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_question);

        initComp();
        i=0;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Question question = questionList.get(i);

                ques.setText(question.getQuestion());
                op1.setText(question.getOption1());
                op2.setText(question.getOption2());
                op3.setText(question.getOption3());
                op4.setText(question.getOption4());
                answer = question.getAnswer();

                back.setEnabled(true);
                forward.setEnabled(true);

//                Intent intent = new Intent(ShowQuestion.this, QuizPage.class);
//
//                intent.putExtra("q", question.getQuestion());
//                intent.putExtra("a", question.getAnswer());
//                intent.putExtra("o1", question.getOption1());
//                intent.putExtra("o2", question.getOption2());
//                intent.putExtra("o3", question.getOption3());
//                intent.putExtra("o4", question.getOption4());
//
//                startActivity(intent);

                //Toast.makeText(ShowQuestion.this, ""+questionList.size(), Toast.LENGTH_LONG).show();
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = i+1;

                if(i==questionList.size()){
                    i=0;
                }

                Question question = questionList.get(i);

                ques.setText(question.getQuestion());
                op1.setText(question.getOption1());
                op2.setText(question.getOption2());
                op3.setText(question.getOption3());
                op4.setText(question.getOption4());
                answer = question.getAnswer();

                op1.setBackgroundResource(R.drawable.input);
                op2.setBackgroundResource(R.drawable.input);
                op3.setBackgroundResource(R.drawable.input);
                op4.setBackgroundResource(R.drawable.input);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = i-1;

                if(i==-1){
                    i=questionList.size()-1;
                }

                Question question = questionList.get(i);

                ques.setText(question.getQuestion());
                op1.setText(question.getOption1());
                op2.setText(question.getOption2());
                op3.setText(question.getOption3());
                op4.setText(question.getOption4());
                answer = question.getAnswer();

                op1.setBackgroundResource(R.drawable.input);
                op2.setBackgroundResource(R.drawable.input);
                op3.setBackgroundResource(R.drawable.input);
                op4.setBackgroundResource(R.drawable.input);
            }
        });

        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = op1.getText().toString().trim();

                if(answer.equals(tmp)){
                    stat.setText("Ok");

                    op1.setBackgroundResource(R.drawable.button_right);
                    op2.setBackgroundResource(R.drawable.input);
                    op3.setBackgroundResource(R.drawable.input);
                    op4.setBackgroundResource(R.drawable.input);
                }
                else{
                    op1.setBackgroundResource(R.drawable.button_wrong);
                    stat.setText(answer);

                    op2.setBackgroundResource(R.drawable.input);
                    op3.setBackgroundResource(R.drawable.input);
                    op4.setBackgroundResource(R.drawable.input);
                }
            }
        });
        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = op2.getText().toString().trim();

                if(answer.equals(tmp)){
                    stat.setText("Ok");

                    op1.setBackgroundResource(R.drawable.input);
                    op2.setBackgroundResource(R.drawable.button_right);
                    op3.setBackgroundResource(R.drawable.input);
                    op4.setBackgroundResource(R.drawable.input);
                }
                else{
                    op2.setBackgroundResource(R.drawable.button_wrong);
                    stat.setText(answer);

                    op1.setBackgroundResource(R.drawable.input);
                    op3.setBackgroundResource(R.drawable.input);
                    op4.setBackgroundResource(R.drawable.input);
                }
            }
        });
        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = op3.getText().toString().trim();

                if(answer.equals(tmp)){
                    stat.setText("Ok");

                    op1.setBackgroundResource(R.drawable.input);
                    op2.setBackgroundResource(R.drawable.input);
                    op3.setBackgroundResource(R.drawable.button_right);
                    op4.setBackgroundResource(R.drawable.input);
                }
                else{
                    op3.setBackgroundResource(R.drawable.button_wrong);
                    stat.setText(answer);

                    op1.setBackgroundResource(R.drawable.input);
                    op2.setBackgroundResource(R.drawable.input);
                    op4.setBackgroundResource(R.drawable.input);

                }
            }
        });
        op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = op4.getText().toString().trim();

                if(answer.equals(tmp)){
                    stat.setText("Ok");

                    op1.setBackgroundResource(R.drawable.input);
                    op2.setBackgroundResource(R.drawable.input);
                    op3.setBackgroundResource(R.drawable.input);
                    op4.setBackgroundResource(R.drawable.button_right);
                }
                else{
                    op4.setBackgroundResource(R.drawable.button_wrong);
                    stat.setText(answer);

                    op1.setBackgroundResource(R.drawable.input);
                    op2.setBackgroundResource(R.drawable.input);
                    op3.setBackgroundResource(R.drawable.input);
                }
            }
        });
    }

    private void initComp(){
        ques = findViewById(R.id.showQuestion_quesText);
        stat = findViewById(R.id.showQuestion_statText);

        op1 = findViewById(R.id.showQuestion_op1Button);
        op2 = findViewById(R.id.showQuestion_op2Button);
        op3 = findViewById(R.id.showQuestion_op3Button);
        op4 = findViewById(R.id.showQuestion_op4Button);
        back = findViewById(R.id.showQuestion_backButton);
        forward = findViewById(R.id.showQuestion_forwardButton);

        listView = findViewById(R.id.showQues_listview);
        databaseReference = FirebaseDatabase.getInstance().getReference("Question");
        questionList = new ArrayList<>();
        showAllQuestion = new ShowAllQuestion(ShowQuestion.this, questionList);
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionList.clear();

                for(DataSnapshot snapshot :dataSnapshot.getChildren()){
                    Question tmp = snapshot.getValue(Question.class);
                    questionList.add(tmp);
                }

                listView.setAdapter(showAllQuestion);

                //Toast.makeText(ShowQuestion.this, ""+newList.size(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
}