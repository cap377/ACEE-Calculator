//Created with the help of PeaMon Page - https://www.youtube.com/watch?v=K2lF862TjU8
                    //and John Tapley - https://www.youtube.com/watch?v=V1ocJmXeQ28

//All of the music used in this project is owned by Nintendo

package net.androidbootcamp.calculator;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    MediaPlayer coolSound1;
    MediaPlayer coolSound2;

    private EditText screen;
    private float screenNumber = 0;
    private String operator = "";
    private boolean operatorFlag = false;
    private ButtonClickListener btnClick = new ButtonClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coolSound1 = MediaPlayer.create(MainActivity.this, R.raw.beam);
        coolSound2 = MediaPlayer.create(MainActivity.this, R.raw.item);

        screen = (EditText) findViewById(R.id.editText);

        int idList[] = {R.id.button1,R.id.button2,R.id.button3, R.id.button4,R.id.button5,R.id.button6,
                R.id.button7,R.id.button8,R.id.button9,R.id.button0,R.id.buttonMult,R.id.buttonSub,
                R.id.buttonNeg,R.id.buttonEquals,R.id.buttonClear,R.id.buttonDiv,R.id.buttonAdd,R.id.buttonDot,
                R.id.buttonPower};

        for (int id:idList){
            View v = (View) findViewById(id);
            v.setOnClickListener(btnClick);
        }
    }

    public void setOperator(String str){
        screenNumber = Float.parseFloat(screen.getText().toString());
        operator = str;
        operatorFlag = true;
    }

    public void replaceInput(String str){
        screen.setText(str);
        operatorFlag = false;
    }

    public void sequenceInput(String str){
        String CurrentScreen = screen.getText().toString();
        if(CurrentScreen.equals("0")){
            CurrentScreen = "";
        }
        CurrentScreen += str;
        screen.setText(CurrentScreen);
    }

    public void Calculate(){
        float NumAf = Float.parseFloat(screen.getText().toString());
        float result = NumAf;
        if(operator.equals("+")){
            result = screenNumber + NumAf;
        }
        if(operator.equals("-")){
            result = screenNumber - NumAf;
        }
        if(operator.equals("*")){
            result = screenNumber * NumAf;
        }
        if(operator.equals("/")){
            result = screenNumber / NumAf;
        }
        if(operator.equals("^")){
            result = (float) Math.pow(screenNumber, NumAf);
        }
        screen.setText(String.valueOf(result));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ButtonClickListener implements View.OnClickListener {

        public void onClick(View v){
            switch(v.getId()){
                case R.id.buttonClear:
                    coolSound1.start();
                    screen.setText("0");
                    screenNumber = 0;
                    operator = "";
                    break;
                case R.id.buttonAdd:
                    coolSound1.start();
                    Calculate();
                    setOperator("+");
                    break;
                case R.id.buttonSub:
                    coolSound1.start();
                    Calculate();
                    setOperator("-");
                    break;
                case R.id.buttonMult:
                    coolSound1.start();
                    Calculate();
                    setOperator("*");
                    break;
                case R.id.buttonDiv:
                    coolSound1.start();
                    Calculate();
                    setOperator("/");
                    break;
                case R.id.buttonPower:
                    coolSound1.start();
                    Calculate();
                    setOperator("^");
                    break;
                case R.id.buttonEquals:
                    coolSound2.start();
                    Calculate();
                    operator = "=";
                    screenNumber = 0;
                    break;
                default:
                    coolSound1.start();
                    String number = ((Button) v).getText().toString();
                    if(operatorFlag){
                        replaceInput(number);
                    }
                    else {
                        sequenceInput(number);
                    }
                    break;
            }
        }
    }
}
