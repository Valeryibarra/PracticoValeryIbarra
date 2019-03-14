package eileen.com.practicovalery;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Operaciones extends AppCompatActivity {

    //Elementos graficos
    private TextView tv_operation;
    private TextView tv_puntos_actuales;
    private Button btn_enviar_operacion;
    private RadioButton rb_first;
    private RadioButton rb_second;
    private RadioButton rb_third;
    private RadioButton rb_fourth;
    private RadioGroup rg_results;

    //para la variable global que es donde esta el puntaje
    private Variable variable;


    public boolean dificultad;
    public String message;
    public int correct;
    public int incorrect1, incorrect2, incorrect3;
    private int ran;
    private int puntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operaciones);

        Intent datos = getIntent();
        dificultad = datos.getExtras().getBoolean("dificultad");

        correct = 0;
        incorrect1 = 0;
        incorrect2 = 0;
        incorrect3 = 0;

        variable = (Variable) getApplication();
        puntaje = variable.getPuntaje();


        tv_puntos_actuales = (TextView) findViewById(R.id.tv_puntos_actuales);
        tv_operation = (TextView) findViewById(R.id.tv_operation);
        btn_enviar_operacion = findViewById(R.id.btn_enviar_operacion);
        rb_first = findViewById(R.id.rb_first);
        rb_second = findViewById(R.id.rb_second);
        rb_third = findViewById(R.id.rb_third);
        rb_fourth = findViewById(R.id.rb_fourth);

        genOperation();
        afterGenOperation();

        btn_enviar_operacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntaje = variable.getPuntaje();

                switch (ran) {
                    case 2:
                        //Correct answers is rb_first
                        if (rb_first.isChecked()) {
                            //is correct
                            //debe sumar los puntos
                            Log.e("Mensaje", "onClick:llego");
                            puntaje += 5;
                            variable.setPuntaje(puntaje);
                            tv_puntos_actuales.setText(puntaje + "");
                            genOperation();
                            afterGenOperation();
                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            "Respuesta Correcta, Einstein", Toast.LENGTH_LONG);

                            toast1.show();


                        } else {
                            //is not correct
                            genOperation();
                            afterGenOperation();
                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            "Respuesta Incorrecta, :C", Toast.LENGTH_LONG);

                            toast1.show();
                        }

                        break;
                    case 3:
                        //Correct answers is rb_second
                        if (rb_second.isChecked()) {
                            //is correct
                            puntaje += 5;
                            variable.setPuntaje(puntaje);

                            tv_puntos_actuales.setText(puntaje + "");
                            genOperation();
                            afterGenOperation();
                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            "Respuesta correcta, Einstein", Toast.LENGTH_LONG);

                            toast1.show();


                        } else {
                            //is not correct
                            genOperation();
                            afterGenOperation();
                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            "Respuesta Incorrecta, :C", Toast.LENGTH_LONG);

                            toast1.show();
                        }
                        break;
                    case 4:
                        //Correct answers is rb_third
                        if (rb_third.isChecked()) {
                            //is correct
                            puntaje += 5;
                            variable.setPuntaje(puntaje);

                            tv_puntos_actuales.setText(puntaje + "");
                            genOperation();
                            afterGenOperation();
                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            "Respuesta correcta, Einstein", Toast.LENGTH_LONG);

                            toast1.show();


                        } else {
                            //is not correct
                            genOperation();
                            afterGenOperation();
                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            "Respuesta Incorrecta, :C", Toast.LENGTH_LONG);

                            toast1.show();

                        }
                        break;
                    case 5:
                        //Correct answers is rb_fourth
                        if (rb_fourth.isChecked()) {
                            //is correct
                            puntaje += 5;
                            variable.setPuntaje(puntaje);
                            tv_puntos_actuales.setText(puntaje + "");
                            genOperation();
                            afterGenOperation();
                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            "Respuesta correcta, Einstein", Toast.LENGTH_LONG);

                            toast1.show();


                        } else {
                            //is not correct
                            genOperation();
                            afterGenOperation();
                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            "Respuesta Incorrecta :C", Toast.LENGTH_LONG);

                            toast1.show();
                        }
                        break;
                }

            }
        });


    }

    private void afterGenOperation() {

        puntaje = variable.getPuntaje();
        tv_puntos_actuales.setText(puntaje + "");
        tv_operation.setText(message);

        ran = (int) (Math.random() * ((5 - 2) + 1)) + 2;

        switch (ran) {
            case 2:
                //Correct answers is rb_first
                rb_first.setText(correct + "");
                rb_second.setText(incorrect1 + "");
                rb_third.setText(incorrect2 + "");
                rb_fourth.setText(incorrect3 + "");
                break;
            case 3:
                //Correct answers is rb_second
                rb_first.setText(incorrect1 + "");
                rb_second.setText(correct + "");
                rb_third.setText(incorrect2 + "");
                rb_fourth.setText(incorrect3 + "");
                break;
            case 4:
                //Correct answers is rb_third
                rb_first.setText(incorrect1 + "");
                rb_second.setText(incorrect2 + "");
                rb_third.setText(correct + "");
                rb_fourth.setText(incorrect3 + "");
                break;
            case 5:
                //Correct answers is rb_fourth
                rb_first.setText(incorrect1 + "");
                rb_second.setText(incorrect3 + "");
                rb_third.setText(incorrect2 + "");
                rb_fourth.setText(correct + "");
                break;
        }
    }

    private void genOperation() {

        int a=0;
        int b=0;
        if(dificultad){
            a = (int) (Math.random() * ((24 - 1) + 1)) + 1;
            b = (int) (Math.random() * ((20 - 1) + 1)) + 1;
        }else{
            a = (int) (Math.random() * ((42 - 1) + 1)) + 1;
            b = (int) (Math.random() * ((44 - 1) + 1)) + 1;
        }


        int seedOperator = (int) (Math.random() * ((7 - 2) + 1)) + 2;
        message = "holi";
        switch (seedOperator) {
            case 2:
                //Suma +
                message = a + " + " + b + "";
                correct = a + b;
                break;
            case 3:
                //Resta -
                message = a + " - " + b + "";
                correct = a - b;
                break;

            case 4:
                //Multiplicacion
                message = a + " * " + b + "";
                correct = a * b;
                break;
            case 5:
                //Division
                if (a < b) {
                    message = a + " + " + b + "";
                    correct = a + b;
                } else {
                    message = a + " / " + b + "";
                    correct = a / b;
                }

                break;
            case 6:
                //Suma +
                message = a + " + " + b + "";
                correct = a + b;
                break;
            case 7:
                //Resta -
                message = a + " - " + b + "";
                correct = a - b;
                break;

        }

        int seedIncorrect = (int) (Math.random() * ((4 - 1) + 1)) + 1;

        incorrect1 = correct + seedIncorrect + 1;
        if (incorrect1 == correct) {
            incorrect1 = correct + seedIncorrect + 3;
        }
        incorrect2 = correct + seedIncorrect - 1;
        if (incorrect2 == correct) {
            incorrect2 = correct + seedIncorrect - 3;
        }
        incorrect3 = correct + seedIncorrect + 2;
        if (incorrect3 == correct) {
            incorrect3 = correct + seedIncorrect - 2;
        }

    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(Operaciones.this, MapsActivity.class);
        startActivity(i);

    }


}
