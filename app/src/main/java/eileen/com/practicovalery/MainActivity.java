package eileen.com.practicovalery;

import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class MainActivity extends AppCompatActivity {

    private TextView tv_puntos_biblio;
    private Button btn_canje;
    private RadioButton rb_lapicero;
    private RadioButton rb_cuaderno;
    private RadioButton rb_libreta;
    private RadioButton rb_camiseta;
    private RadioButton rb_saco;

    //para la variable global que es donde esta el puntaje
    private Variable variable;

    private int puntaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv_puntos_biblio = (TextView) findViewById(R.id.tv_puntos_biblio);
        btn_canje = findViewById(R.id.btn_canje);
        rb_lapicero = findViewById(R.id.rb_lapicero);
        rb_cuaderno = findViewById(R.id.rb_cuaderno);
        rb_libreta = findViewById(R.id.rb_libreta);
        rb_camiseta = findViewById(R.id.rb_camiseta);
        rb_saco = findViewById(R.id.rb_saco);

        variable = (Variable) getApplication();
        puntaje = variable.getPuntaje();


        tv_puntos_biblio.setText(puntaje + "");
        btn_canje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1;
                puntaje = variable.getPuntaje();

                if (rb_lapicero.isChecked()) {
                    //descuento 20
                    if (puntaje >= 20) {
                        puntaje -= 20;
                        variable.setPuntaje(puntaje);
                        tv_puntos_biblio.setText(puntaje + "");
                        toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Acabas de comprar un Lapicero Icesi", Toast.LENGTH_LONG);
                    } else {
                        toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "No tienes el puntaje necesario para la compra", Toast.LENGTH_LONG);
                    }
                    toast1.show();
                } else if (rb_cuaderno.isChecked()) {
                    //descuento 30
                    if (puntaje >= 30) {
                        puntaje -= 30;
                        variable.setPuntaje(puntaje);
                        tv_puntos_biblio.setText(puntaje + "");
                        toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Acabas de comprar un Cuaderno Icesi", Toast.LENGTH_LONG);
                    } else {
                        toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "No tienes el puntaje necesario para la compra", Toast.LENGTH_LONG);
                    }
                    toast1.show();
                } else if (rb_libreta.isChecked()) {
                    //descuento 40
                    if (puntaje >= 40) {
                        puntaje -= 40;
                        variable.setPuntaje(puntaje);
                        tv_puntos_biblio.setText(puntaje + "");
                        toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Acabas de comprar una Libreta Icesi", Toast.LENGTH_LONG);
                    } else {
                        toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "No tienes el puntaje necesario para la compra", Toast.LENGTH_LONG);
                    }
                    toast1.show();
                } else if (rb_camiseta.isChecked()) {
                    //descuento 80
                    if (puntaje >= 80) {
                        puntaje -= 80;
                        variable.setPuntaje(puntaje);
                        tv_puntos_biblio.setText(puntaje + "");
                        toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Acabas de comprar una Camiseta Icesi", Toast.LENGTH_LONG);
                    } else {
                        toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "No tienes el puntaje necesario para la compra", Toast.LENGTH_LONG);
                    }
                    toast1.show();
                } else if (rb_saco.isChecked()) {
                    //descuento 100
                    if (puntaje >= 100) {
                        puntaje -= 100;
                        variable.setPuntaje(puntaje);
                        tv_puntos_biblio.setText(puntaje + "");
                        toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Acabas de comprar un Saco Icesi", Toast.LENGTH_LONG);
                    } else {
                        toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "No tienes el puntaje necesario para la compra", Toast.LENGTH_LONG);
                    }
                    toast1.show();
                }

//                Intent i = new Intent(MainActivity.this, Profile.class);
//                i.putExtra("nombre",et_user.getText().toString());
//                i.putExtra("contra",et_pass.getText().toString());
//                startActivity(i);

            }
        });


    }
}
