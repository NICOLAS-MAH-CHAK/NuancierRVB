package com.example.nuancierrvb;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {


    private TextView TV_Red, TV_Green, TV_Blue;
    private String Temp1="#FFFFFF", Temp2="#FFFFFF";
    private int Tempcolor1= Color.BLACK,Tempcolor2= Color.BLACK;
    private boolean flag=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button BT_Actu = (Button) findViewById(R.id.Bouton_Actuel);
        final Button BT_Prece = (Button) findViewById(R.id.Bouton_Precedant);
        final Button BT_Penul = (Button) findViewById(R.id.Bouton_Penultieme);
        final Button BT_Save = (Button) findViewById(R.id.Bouton_Enregistrer);
        final Button BT_Reset = (Button) findViewById(R.id.Bouton_Reset);
        final ToggleButton ToggleBT = (ToggleButton) findViewById(R.id.ToggleBouton_WEB);

        TV_Red = (TextView) findViewById(R.id.Text_SB_RED);
        TV_Green = (TextView) findViewById(R.id.Text_SB_GREEN);
        TV_Blue = (TextView) findViewById(R.id.Text_SB_BLUE);

        final SeekBar SB_Red = (SeekBar) findViewById(R.id.SB_Red);
        final SeekBar SB_Green = (SeekBar) findViewById(R.id.SB_GREEN);
        final SeekBar SB_Blue = (SeekBar) findViewById(R.id.SB_BLUE);

        ChangeBouton("#FFFFFF", BT_Actu, Color.BLACK);
        ChangeBouton("#FFFFFF", BT_Prece, Color.BLACK);
        ChangeBouton("#FFFFFF", BT_Penul, Color.BLACK);

        if (savedInstanceState != null) {


            Temp1 = savedInstanceState.getString("TempPrece");
            Tempcolor1 = savedInstanceState.getInt("Tcolor1");
            ChangeBouton(Temp1,BT_Prece,Tempcolor1);

            Temp2 = savedInstanceState.getString("TempPenu");
            Tempcolor2 = savedInstanceState.getInt("Tcolor2");
            ChangeBouton(Temp2,BT_Penul,Tempcolor2);
            if(Temp2=="#FFFFFF"){ ChangeBouton("#FFFFFF",BT_Penul,Color.BLACK);}

            flag = savedInstanceState.getBoolean("Tempbool");





        }







        SeekBar.OnSeekBarChangeListener Poosky = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int SBREDProg = SB_Red.getProgress();
                int SBGREENProg = SB_Green.getProgress();
                int SBBLUEProg = SB_Blue.getProgress();

                if (ToggleBT.isChecked()) {

                    SBREDProg = (int) (SB_Red.getProgress() * 25.5);
                    SBGREENProg = (int) (SB_Green.getProgress() * 25.5);
                    SBBLUEProg = (int) (SB_Blue.getProgress() * 25.5);

                }

                ChangeBouton(toHex(SBREDProg, SBGREENProg, SBBLUEProg), BT_Actu, StringColor(SBREDProg, SBGREENProg, SBBLUEProg));

                ChangeSBText(SBREDProg, SBGREENProg, SBBLUEProg);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
        SB_Red.setOnSeekBarChangeListener(Poosky);
        SB_Green.setOnSeekBarChangeListener(Poosky);
        SB_Blue.setOnSeekBarChangeListener(Poosky);

        BT_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        BT_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeBouton(BT_Prece.getText().toString(), BT_Penul, BT_Prece.getCurrentTextColor());
                ChangeBouton(BT_Actu.getText().toString(), BT_Prece, BT_Actu.getCurrentTextColor());
                savecolor(BT_Prece, BT_Penul);
            }
        });

        BT_Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetSB(SB_Red);
                ResetSB(SB_Green);
                ResetSB(SB_Blue);
                ChangeBouton("#FFFFFF", BT_Actu, Color.BLACK);
                ChangeBouton("#FFFFFF", BT_Prece, Color.BLACK);
                ChangeBouton("#FFFFFF", BT_Penul, Color.BLACK);
                savecolor(BT_Prece, BT_Penul);
            }
        });

        ToggleBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToggleBT.isChecked()) {
                    flag = true;

                    double Progred = SB_Red.getProgress() / 25.5;
                    int mred = (int) Progred;

                    double Proggreen = SB_Green.getProgress() / 25.5;
                    int mgreen = (int) Proggreen;

                    double Progblue = SB_Blue.getProgress() / 25.5;
                    int mblue = (int) Progblue;


                    SB_Red.setProgress(mred);
                    SB_Green.setProgress(mgreen);
                    SB_Blue.setProgress(mblue);

                    SB_Red.setMax(10);
                    SB_Green.setMax(10);
                    SB_Blue.setMax(10);

                } else {
                    flag = false;
                    SB_Red.setMax(255);
                    SB_Green.setMax(255);
                    SB_Blue.setMax(255);

                    SB_Red.setProgress((int) (SB_Red.getProgress() * 25.5));
                    SB_Green.setProgress((int) (SB_Green.getProgress() * 25.5));
                    SB_Blue.setProgress((int) (SB_Blue.getProgress() * 25.5));
                }
            }


        });
        if(flag==true){
            SB_Red.setMax(10);
            SB_Green.setMax(10);
            SB_Blue.setMax(10);


        }



    }
    public void ChangeBouton(String text, Button button, int color){

        button.setBackgroundColor(Color.parseColor(text));
        button.setTextColor(color);
        button.setText(text);
    }

    private String toHex(int r,int g, int b){

        String string = String.format("#%02x%02x%02x", r, g, b);
        if(string.length()==7){
            return string;}
        else{
            return "#FFFFFF";
        }

    }
    public void ChangeSBText(int r,int g, int b) {

        String str1 ="R: "+ Math.round((float)r/ 255*100)+"%";
        String str2 ="G: "+ Math.round((float)g/ 255*100)+"%";
        String str3 ="B: "+ Math.round((float)b/ 255*100)+"%";


        TV_Red.setText(str1);
        TV_Green.setText(str2);
        TV_Blue.setText(str3);

    }
    public int StringColor(int r,int g, int b){

        Float lumi = ((float)(r*299)+((float)g*589)+((float)b*114))/1000;
        if(lumi < 125){
            return Color.WHITE;
        }
        else{
            return Color.BLACK;
        }
    }

    public void ResetSB(SeekBar SB){
        SB.setProgress(0);
        ChangeSBText(0,0,0);

    }

    public void savecolor(Button button1, Button button2){
        Temp1 = button1.getText().toString();
        Temp2 = button2.getText().toString();
        Tempcolor1 = button1.getCurrentTextColor();
        Tempcolor2=button2.getCurrentTextColor();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("TempPrece",Temp1);
        outState.putString("TempPenu",Temp2);
        outState.putInt("Tcolor1",Tempcolor1);
        outState.putInt("Tcolor2",Tempcolor1);
        outState.putBoolean("Tempbool",flag);


    }




}
