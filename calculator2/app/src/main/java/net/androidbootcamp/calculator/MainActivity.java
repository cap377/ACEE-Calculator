//Created with the help of PeaMon Page - https://www.youtube.com/watch?v=K2lF862TjU8

package net.androidbootcamp.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText Scr;
    private float screenNumber;
    private String operator;
    private ButtonClickListener btnClick = new ButtonClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Scr = (EditText) findViewById(R.id.editText);

        int idList[] = {R.id.button1,R.id.button2,R.id.button3, R.id.button4,R.id.button5,R.id.button6,
                R.id.button7,R.id.button8,R.id.button9,R.id.button0,R.id.buttonMult,R.id.buttonSub,
                R.id.buttonNeg,R.id.buttonEquals,R.id.buttonClear,R.id.buttonDiv,R.id.buttonAdd,R.id.buttonDot,
                R.id.buttonPower};

        for (int id:idList){
            View v = (View) findViewById(id);
            v.setOnClickListener(btnClick);
        }
    }

    public void mMath(String str){
        screenNumber = Float.parseFloat(Scr.getText().toString());
        operator = str;
        Scr.setText("0");
    }

    public void getKeyboard(String str){
        String CurrentScreen = Scr.getText().toString();
        if(CurrentScreen.equals("0")){
            CurrentScreen = "";
        }
        CurrentScreen += str;
        Scr.setText(CurrentScreen);
    }

    public void mResult(){
        float NumAf = Float.parseFloat(Scr.getText().toString());
        float result = 0;
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
        Scr.setText(String.valueOf(result));
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
                    Scr.setText("0");
                    screenNumber = 0;
                    operator = "";
                    break;
                case R.id.buttonAdd:
                    mMath("+");
                    break;
                case R.id.buttonSub:
                    mMath("-");
                    break;
                case R.id.buttonMult:
                    mMath("*");
                    break;
                case R.id.buttonDiv:
                    mMath("/");
                    break;
                case R.id.buttonPower:
                    mMath("^");
                    break;
                case R.id.buttonEquals:
                    mResult();
                    break;
                default:
                    String number = ((Button) v).getText().toString();
                    getKeyboard(number);
                    break;
            }
        }
    }
}
