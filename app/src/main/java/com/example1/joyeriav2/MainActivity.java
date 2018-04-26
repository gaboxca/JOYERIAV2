package com.example1.joyeriav2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etpeso;
    RadioGroup rg_oro, rg_material, rg_plata;
    RadioButton rb_oro, rb_plata, rbo_24, rbo_18, rbo_14, rbp_925, rbp_950;
    TextView tv_peso, tv_puro, tv_cobre, tv_material;
    Spinner spnr_ena;
    double porcentaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etpeso = (EditText) findViewById(R.id.et_peso);


        rb_oro = (RadioButton) findViewById(R.id.rb_oro);
        rb_plata = (RadioButton) findViewById(R.id.rb_plata);
        rbo_24 = (RadioButton) findViewById(R.id.rb_24);
        rbo_18 = (RadioButton) findViewById(R.id.rb_18);
        rbo_14 = (RadioButton) findViewById(R.id.rb_14);
        rbp_925 = (RadioButton) findViewById(R.id.rb_925);
        rbp_950 = (RadioButton) findViewById(R.id.rb_950);


        rg_oro = (RadioGroup) findViewById(R.id.rg_oro);
        rg_plata = (RadioGroup) findViewById(R.id.rg_plata);
        rg_material = (RadioGroup) findViewById(R.id.rg_material);

        tv_peso = (TextView) findViewById(R.id.tv_total);
        tv_puro = (TextView) findViewById(R.id.tv_puro);
        tv_cobre = (TextView) findViewById(R.id.tv_cobre);
        tv_material = (TextView) findViewById(R.id.tv_Material);

        spnr_ena = (Spinner) findViewById(R.id.spr_ena);

        rg_material.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_oro:
                        tv_peso.setText("");
                        tv_puro.setText("");
                        tv_cobre.setText("");
                        rg_oro.setVisibility(View.VISIBLE);
                        rg_plata.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.rb_plata:
                        tv_peso.setText("");
                        tv_puro.setText("");
                        tv_cobre.setText("");
                        rg_plata.setVisibility(View.VISIBLE);
                        rg_oro.setVisibility(View.INVISIBLE);
                        break;
                    default:
                        break;
                }
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.porcentaje,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnr_ena.setAdapter(adapter);

        spnr_ena.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String enarbola;
                enarbola = spnr_ena.getSelectedItem().toString();
                switch (enarbola){
                    case "0%":
                        porcentaje = 1;
                        break;
                    case "10%":
                        porcentaje = 1.1;
                        break;
                    case "15%":
                        porcentaje = 1.15;
                        break;
                    case "50%":
                        porcentaje = 1.5;
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void calcula(View view){

        String str_cera = etpeso.getText().toString();
        //String str_total, str_puro, str_cobre;
        String Ley ="", material;
        double db_cera;

        if (str_cera.isEmpty()) {
            Toast.makeText(this, "No se coloco peso de CERA",Toast.LENGTH_SHORT).show();
        } else {

            db_cera = Double.parseDouble(str_cera);
            double db_total, db_puro, db_cobre;

            if (rb_oro.isChecked() == true) {


                if (rbo_24.isChecked() == true) {
                    db_total = (db_cera * 19.32) * porcentaje;
                    tv_peso.setText(String.format("%.2f",db_total));

                    db_puro = db_total * .916;
                    tv_puro.setText(String.format("%.2f",db_puro));

                    db_cobre = db_total - db_puro;
                    tv_cobre.setText(String.format("%.2f",db_cobre));

                    Ley = "24 ct";

                }
                if (rbo_18.isChecked() == true) {
                    db_total = (db_cera * 15.58) * porcentaje;
                    tv_peso.setText(String.format("%.2f",db_total));

                    db_puro = db_total * .75;
                    tv_puro.setText(String.format("%.2f",db_puro));

                    db_cobre = db_total - db_puro;
                    tv_cobre.setText(String.format("%.2f",db_cobre));

                    Ley = "18 ct";
                }
                if (rbo_14.isChecked() == true) {
                    db_total = (db_cera * 13.07) * porcentaje;
                    tv_peso.setText(String.format("%.2f",db_total));

                    db_puro = db_total * .538;
                    tv_puro.setText(String.format("%.2f",db_puro));

                    db_cobre = db_total - db_puro;
                    tv_cobre.setText(String.format("%.2f",db_cobre));

                    Ley = "14 ct";
                }
                material = "ORO " + Ley;

                tv_material.setText(material);

            }

            if (rb_plata.isChecked() == true) {

                db_total = (db_cera * 10.5) * porcentaje;
                tv_peso.setText(String.format("%.2f",db_total));

                if (rbp_925.isChecked() == true) {

                    db_puro = db_total * .925;
                    tv_puro.setText(String.format("%.2f",db_puro));

                    db_cobre = db_total - db_puro;
                    tv_cobre.setText(String.format("%.2f",db_cobre));

                    Ley = "925";
                }

                if (rbp_950.isChecked() == true) {

                    db_puro = db_total * .95;
                    tv_puro.setText(String.format("%.2f",db_puro));

                    db_cobre = db_total - db_puro;
                    tv_cobre.setText(String.format("%.2f",db_cobre));
                    Ley = "950";
                }

                material = "PLATA " + Ley;
                tv_material.setText(material);


            }

        }
    }

}
