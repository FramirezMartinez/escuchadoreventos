package com.example.escuchadorconeventomayor;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import android.os.Handler;


public class MainActivity extends Activity {
    //creando vareables  tipo EditText
    private EditText etcN1;
    private EditText etcN2;
    private EditText etcN3;
    private EditText resultado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencias a los campos de texto EditText
        etcN1 = findViewById(R.id.etN1);
        etcN2 = findViewById(R.id.etN2);
        etcN3 = findViewById(R.id.etntres);
        resultado = findViewById(R.id.resul);


        // Pide el foco para el EditText
        etcN1.requestFocus();

        // Configurar el botón para responder al clic
        Button objcalcular = findViewById(R.id.btncalcular);
        objcalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí ejecuta tu función
                ejecutarMiFuncion();
            }
        });

        etcN1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    // Ocultar el teclado virtual
                    //InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    //imm.hideSoftInputFromWindow(etcN1.getWindowToken(), 0);

                    // Pausa de 200 milisegundos para permitir que el segundo campo obtenga el enfoque
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            etcN2.requestFocus();
                        }
                    }, 200); // Ajusta el tiempo de pausa según sea necesario

                    return true;
                }
                return false;
            }
        });


        etcN2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    // Ocultar el teclado virtual
                   // InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    //imm.hideSoftInputFromWindow(etcN1.getWindowToken(), 0);

                    // Pausa de 200 milisegundos para permitir que el segundo campo obtenga el enfoque
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            etcN3.requestFocus();
                        }
                    }, 200); // Ajusta el tiempo de pausa según sea necesario


                    return true;
                }
                return false;
            }
        });


        etcN3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    // Ocultar el teclado virtual
                  InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                  imm.hideSoftInputFromWindow(etcN3.getWindowToken(), 0);

                    // Mueve el foco al botón de calcular
                    objcalcular.requestFocus();
                    //ejecuta la funcion
                    ejecutarMiFuncion();

                    return true;
                }
                return false;
            }
        });

    }//cierra en onCreat
    private void ejecutarMiFuncion() {
        try {
            // Obtener el texto de los EditText y convertirlos a números enteros
            int valorN1 = Integer.parseInt(etcN1.getText().toString());
            int valorN2 = Integer.parseInt(etcN2.getText().toString());
            int valorN3 = Integer.parseInt(etcN3.getText().toString());

            int numeroMayor = valorN1;  // Suponemos que el número mayor es valorN1 inicialmente

            // Comparar con valorN2 y actualizar si es necesario
            if (valorN2 > numeroMayor) {
                numeroMayor = valorN2;
            }

            // Comparar con valorN3 y actualizar si es necesario
            if (valorN3 > numeroMayor) {
                numeroMayor = valorN3;
            }

            resultado.setText("mayor es: " + numeroMayor);
        } catch (NumberFormatException e) {
            // Manejar la excepción si el texto no es un número válido
            resultado.setText("Ingrese números válidos en todos los campos");
        }
    }
}
