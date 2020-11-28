package com.example.ocr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivitySolution extends AppCompatActivity {
    private TextView step1sol,step2sol,step3sol,step4sol,eqnsol;
    char[] eqn;
    char [] fun;
    char a,b,k,p;
    public static int a1,b1,k1,p1;

    int len;
    String output,stp4;
    double logvalue;

    int a_index,b_index,func_index,k_index,p_index;
    int i,j,l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);

        step1sol=(TextView)findViewById(R.id.step1sol);
        step2sol=(TextView)findViewById(R.id.step2sol);
        step3sol=(TextView)findViewById(R.id.step3sol);
        step4sol=(TextView)findViewById(R.id.step4sol);
        eqnsol=(TextView)findViewById(R.id.eqn1);


        Intent intent=getIntent();
        String relation=intent.getStringExtra("msg");
        eqnsol.setText(relation);
        len = relation.length();
        eqn = relation.toCharArray();
        for(i=0;i<len;i++)
        {
            if(eqn[i]=='T')
            {
                if (i==0)
                    a='1';
                else{
                    a_index=i;
                    a=eqn[--a_index];
                }
            }
            if(eqn[i]=='n'&&eqn[i+1]==')')

            {
                b='1';

            }
            if(eqn[i]=='/')

            {
                b_index=i;
                b=eqn[++b_index];

            }
            if (eqn[i]==')')
            {
                func_index=i;
                func_index++;
                if (eqn[func_index]=='\0'|| eqn[func_index+2]==' ')
                {
                    k='0';
                    p='0';
                }
            }

            if (eqn[i]=='n' && eqn[i+1]=='l')
            {
                k='1';
                if (eqn[i+3]=='g' && eqn[i+4]=='^')
                {
                    p_index=i+5;
                    p=eqn[p_index];
                }
                else if (eqn[i+3]=='g' && eqn[i+4]=='n')
                {
                    p='1';
                }

            }

            if (eqn[i]=='n' && eqn[i+1]=='^')
            {
                k_index=i+2;
                k=eqn[k_index];
                if (eqn[i+3]=='l' && eqn[i+6]=='^')
                {
                    p_index=i+7;
                    p=eqn[p_index];
                }
                else if (eqn[i+3]=='l' && eqn[i+6]=='n')
                {
                    p='1';
                }


            }

            if (eqn[i]=='g' && eqn[i+1]=='^')
            {
                p_index=i+2;
                p=eqn[p_index];
            }
            if (eqn[i]=='n' && eqn[i+1]==' ' && eqn[i-1]=='+')
            {
                k='1';

            }
            if (eqn[i]!='l')
                p='0';
            if (eqn[i]=='l' && eqn[i-1]=='+')
            {
                k='0';
                if (eqn[i+2]=='g' && eqn[i+3]=='^')
                {
                    p_index=i+4;
                    p=eqn[p_index];
                }
                else if (eqn[i+2]=='g' && eqn[i+3]=='n')
                {
                    p='1';
                }
            }



        }
        a1=Character.getNumericValue(a);
        b1=Character.getNumericValue(b);
        k1=Character.getNumericValue(k);
        p1=Character.getNumericValue(p);

        logvalue = log(b1,a1);
        if(logvalue>k1)
        {
            if (logvalue==1.0)
                output="n";
            else
                output="n^"+logvalue;
        }

        if(logvalue==k1)
        {
            if(p1>-1)
            {
                stp4="Here, p>-1";
                int temp=p1+1;
                if (temp==1)
                    output="n^"+k+"logn";
                else if (k1==1)
                    output="n"+"log^"+temp+"n";
                else if (temp==1 && k1==1)
                    output="nlogn";
                else
                    output="n^"+k+"log^"+temp+"n";
            }
            if(p1==-1)
            {
                stp4="Here, p==-1";
                if (k1==1)
                    output="nloglogn";
                else
                    output="n^"+k+"loglogn";
            }
            if(p1<-1)
            {
                stp4="Here, p<-1";
                if (k1==1)
                    output="n";
                else
                    output="n^"+k;
            }
        }
        if(logvalue<k1)
        {
            if(p1>=0)
            {
                stp4="Here, p>=0";
                if (p1==1)
                    output="n^"+k+"logn";
                else if (k1==1)
                    output="n"+"log^"+p+"n";
                else if (p1==1 && k1==1)
                    output="nlogn";
                else if (p1==0)
                    output="n^"+k;
                else
                    output="n^"+k+"log^"+p+"n";

            }
            if(p1<0)
            {
                stp4="Here, p<0";
                if (k1==1)
                    output="n";
                else
                    output="n^"+k;
            }
        }
        //String tt=a1+" "+b1+" "+" "+k1+" "+p1;
      //  String ty=Double.toString(logvalue);
        String stp1="From the above equation, we have the following values:\na="+a+"\nb="+b+"\nk="+k+"\np="+p;
        step1sol.setText(stp1);

        String stp2="Now,\nlog base b(a)="+logvalue;
        step2sol.setText(stp2);

        String stp3;
        if (logvalue>k1)
        {stp3 ="Here, \n(log base b(a))>k\n So case 1 is applied.";
            step4sol.setText("So, Time complexity T(n)="+output);
        }

        else if(logvalue<k1) {
            stp3 = "Here, \n(log base b(a))<k\n So case 3 is applied.";
            step4sol.setText(stp4+"\nSo, Time complexity T(n)="+output);}
        else{
            stp3="Here, \n(log base b(a))==k\n So case 2 is applied.";
            step4sol.setText(stp4+"\nSo, Time complexity T(n)="+output);}
        step3sol.setText(stp3);







    }
    public double log(int b1 , int a1)
    {
        double c = Math.log10(a1)/Math.log10(b1);
        return c;
    }
}
