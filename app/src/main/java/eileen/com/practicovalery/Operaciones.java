package eileen.com.practicovalery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Operaciones extends AppCompatActivity {

    //Elementos graficos
    private TextView tv_operation;
    private Button btn_enviar_operacion;
    private RadioButton rb_first;
    private RadioButton rb_second;
    private RadioButton rb_third;
    private RadioButton rb_fourth;
    private RadioGroup rg_results;



    public String message;
    public int correct = 0;
    public int incorrect1, incorrect2, incorrect3 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operaciones);

        genOperation();
        Log.e(">>>", "mensaje: "+message);

        tv_operation = (TextView) findViewById(R.id.tv_operation);
        btn_enviar_operacion = findViewById(R.id.btn_enviar_operacion);
        rb_first= findViewById(R.id.rb_first);
        rb_second= findViewById(R.id.rb_second);
        rb_third= findViewById(R.id.rb_third);
        rb_fourth= findViewById(R.id.rb_fourth);


        tv_operation.setText(message);

        rg_results.findViewById(R.id.rg_results);
        rb_first.setText(incorrect1+"");
        rb_second.setText(incorrect2+"");
        rb_third.setText(correct+"");
        rb_fourth.setText(incorrect3+"");



    }

    private void genOperation() {
        int a = (int)(Math.random() * ((20 - 1) + 1)) + 1;
        int b=  (int)(Math.random() * ((24 - 1) + 1)) + 1;


        int seedOperator =(int)(Math.random() * ((7 - 2) + 1)) + 2;
        message ="holi";
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
                if(a<b){
                    message = a + " + " + b + "";
                    correct = a + b;
                }else{
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
        if(incorrect1==correct){
            incorrect1=correct + seedIncorrect + 3;
        }
        incorrect2 = correct + seedIncorrect - 1;
        if(incorrect2==correct){
            incorrect2=correct + seedIncorrect -3;
        }
        incorrect3 = correct + seedIncorrect + 2;
        if(incorrect3==correct){
            incorrect3=correct + seedIncorrect -2;
        }

    }


}
