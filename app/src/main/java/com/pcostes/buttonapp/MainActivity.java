package com.pcostes.buttonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final int RED = 0;
    final int GREEN = 1;
    final int BLUE = 2;
    final int UP = 0;
    final int DOWN = 1;

    int[] text_ids = {R.id.red_text, R.id.green_text, R.id.blue_text};
    int[] button_ids = {R.id.red_up, R.id.red_down, R.id.green_up, R.id.green_down, R.id.blue_up, R.id.blue_down};

    int[] values = new int[text_ids.length];
    TextView[] texts = new TextView[text_ids.length];
    Button[] buttons = new Button[button_ids.length];

    final String my_tag = "INC_DEC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < values.length; i++)
        {
            values[i] = 255;
        }

        for (int i = 0; i < text_ids.length; i++)
        {
            texts[i] = findViewById(text_ids[i]);
        }

        for (int i = 0; i < button_ids.length; i++)
        {
            final int index = i;
            final int text_index = i / 2;
            buttons[i] = findViewById(button_ids[i]);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if (index % 2 == UP)
                    {
                        values[text_index]++;
                        if (values[text_index] > 255)
                            values[text_index] = 255;
                        texts[text_index].setText(Integer.toHexString(values[text_index]).toUpperCase());
                        Log.i(my_tag, "Button " + index + " incremented text " + text_index + " to " + values[text_index]);
                    }
                    else
                    {
                        values[text_index]--;
                        if (values[text_index] < 0)
                            values[text_index] = 0;
                        texts[text_index].setText(Integer.toHexString(values[text_index]).toUpperCase());
                        Log.i(my_tag, "Button " + index + " decremented text " + text_index + " to " + values[text_index]);
                    }
                    String color_string = "#";
                    for (int i = 0; i < values.length; i++)
                    {
                        color_string += (i == text_index ? Integer.toHexString(values[i]) : "00");
                    }
                    texts[text_index].setBackgroundColor(Color.parseColor(color_string));
                }
            });

           /* buttons[i].setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v)
                {
                    //v.callOnClick();

                    return false;
                }
            });

            buttons[i].setOnTouchListener(new View.OnTouchListener(){

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    v.callOnClick();
                    return false;
                }
            });*/
        }


    }

    public void reset_values(View view)
    {
        for (int i = 0; i < values.length; i++)
        {
            values[i] = 255;
            texts[i].setText(Integer.toHexString(values[i]).toUpperCase());
            Log.i(my_tag, "Reset all values");
        }
    }
}