package com.example.jahir.numeros;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {
    private EditText Display1;
    private TextView Display2;
    private Button bV, bL, bS;
    private TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bV = (Button) findViewById(R.id.btn1);
        bL = (Button) findViewById(R.id.btn2);
        bS = (Button) findViewById(R.id.btn3);
        Display1 = (EditText) findViewById(R.id.editText);
        Display2 = (TextView) findViewById(R.id.textView);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(new Locale( "spa", "ESP" ));
                }
            }
        });
        //Log.e("App_Error_numeros","msg");

    }

    public void Limpiar(View view) {
        Toast.makeText(this,"Listo! Ingrese otro numero", Toast.LENGTH_SHORT).show();
        Display2.setText("");
        Display1.setText("");
    }

    String mensaje;
    String[] unidades = {
            "nada","uno","dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho","nueve" };

    public void BotonVer(View view){
        mensaje="";
        String entero="0"; String hola="0"; String decimal="0";

        hola = Display1.getText().toString();
        if (hola.length() == 0){
            Toast.makeText(this,"Ingrese un número", Toast.LENGTH_SHORT).show(); return;
        }
        else {  //int val= hola.indexOf(".");
            if ((hola.indexOf(".")) == -1) {
                entero = hola;  //decimal = ""; //Toast.makeText(this,"No hay punto", Toast.LENGTH_SHORT).show();
            } else {
                StringTokenizer separar = new StringTokenizer(hola, ".");
                if(separar.countTokens() ==1){
                    entero = separar.nextToken();
                }
                else{
                entero = separar.nextToken();
                decimal = separar.nextToken(); } //Toast.makeText(this,"Hay punto", Toast.LENGTH_SHORT).show();
            }
        }

        if(Integer.parseInt(entero) >= 1000 || Integer.parseInt(decimal) >= 100){
            if(Integer.parseInt(entero) >= 1000){
                Toast.makeText(this,"Ingrese un numero menor a mil", Toast.LENGTH_SHORT).show();
            }
            else{
            Toast.makeText(this,"Solo 2 decimales", Toast.LENGTH_SHORT).show();}
        }
        else{
                if(Integer.parseInt(entero) == 100){
                    mensaje ="Cien";
                    if(Integer.parseInt(decimal)>0){
                        if(Integer.parseInt(decimal)>0){
                            mensaje = mensaje + " punto ";
                            ceros(decimal);
                            decenas(decimal);
                        }
                    }
                }
                else if(Integer.parseInt(entero)<100){
                    decenas(entero);    //Toast.makeText(this,decimal, Toast.LENGTH_SHORT).show();
                    if(Integer.parseInt(decimal)>0){
                        mensaje = mensaje + " punto ";
                        ceros(decimal);
                        decenas(decimal);
                    }
                }
                else if(Integer.parseInt(entero)>100 && Integer.parseInt(entero)<200){
                    mensaje ="ciento ";
                    decenas(entero);    //Toast.makeText(this,decimal, Toast.LENGTH_SHORT).show();
                    if(Integer.parseInt(decimal)>0){
                        mensaje = mensaje + " punto";
                        ceros(decimal);
                        decenas(decimal);
                    }
                }
                else{
                    cientos(entero);
                    decenas(entero);
                    if(Integer.parseInt(decimal)>0){    //Toast.makeText(this,decimal, Toast.LENGTH_SHORT).show();
                        mensaje = mensaje + " punto";
                        ceros(decimal);
                        decenas(decimal);
                    }

                }
            }

        if(mensaje.length() >2){
            Display2.setText(mensaje);
            t1.speak(mensaje,TextToSpeech.QUEUE_FLUSH,null);
        }

    }

    public void cientos(String entero){
        int num1= (Integer.parseInt(entero))/100;  //int num2= (Integer.parseInt(entero))/10;
        String[] centena = {
            "Ciento ","Dos", "Tres", "Cuatro", "Quinientos ", "Seis", "Sete", "Ocho","Nove"
        };

                if(num1==1){
                    mensaje = centena[0];
                }
                else if( (num1) == 5){
                        mensaje= centena[num1-1];
                }
                else{
                    mensaje= centena[num1-1] +"cientos ";
                } //mensaje = mensaje + String.valueOf(num1); //if( num2 ==10){ mensaje= mensaje+" diez"; }
    }

    public void decenas(String entero){
        int num1= (Integer.parseInt(entero))/100;
        int num2= (Integer.parseInt(entero)) - (num1*100);
        //String holaMun= String.valueOf(num2);  Toast.makeText(this,holaMun, Toast.LENGTH_SHORT).show();
        if(num2 == 0){
            return;
        }
        String[] cadena = {
                "cero","uno","dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho","nueve","diez",
                "once","doce","trece","catorce","quince", "dieciseis","diecisiete","dieciocho", "diecinueve"
        };
        String[] veinte={
                "veinte","veintiuno", "veintidós", "veintitres", "veinticuatro", "veinticinco",
                "veintiséis", "veintisiete", "veintiocho", "veintinueve"
        };
        String[] dec={
            "nada","diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta","ochenta","noventa"
        };

        if(num2 >= 1 && num2 <20){
            mensaje= mensaje+" "+cadena[num2]; return;
        }
        else if(num2==20 || num2<30){
            mensaje= mensaje+" "+veinte[num2-20]; return;
        }

        int num3= num2/10;
        int num4= num2-(num3*10);
        if(num3>2){
            mensaje= mensaje+" "+dec[num3];
        }
        if(num4>0){
            mensaje= mensaje+" y "+unidades[num4];
        }
    }

    public void ceros(String entero){
        int num1= (Integer.parseInt(entero))/100;
        int num2= (Integer.parseInt(entero)) - (num1*100);
        /*String holaMun= String.valueOf(num2);
        Toast.makeText(this,holaMun, Toast.LENGTH_SHORT).show(); */
        if (entero.length() ==2){
            if (num2<10){
                mensaje= mensaje+" cero ";
            }
        }

    }



    public void BotonSalir(View view){
        Toast.makeText(this,"Adios!", Toast.LENGTH_SHORT).show();
        finish();
    }
    /*
        public void Salir(View view){   //Toast.makeText(this,"Cerrar Aplicacion",Toast.LENGTH_SHORT).show();
        finish();
        //num =  Float.parseFloat(Display1.getText().toString()) ;      //Display2.setText(String.valueOf(num));
    }*/

}
