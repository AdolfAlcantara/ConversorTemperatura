package com.example.adolfalcantara.conversortemperatura;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Conversor extends AppCompatActivity {

    private EditText etTemperatura;
    private TextView tvTempConvertida;
    private Spinner spEscalaActual;
    private Spinner spEscalaDeseada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor);

        etTemperatura = (EditText) findViewById(R.id.etTemperatura);
        tvTempConvertida = (TextView) findViewById(R.id.tvTempConvertida);
        spEscalaActual = (Spinner) findViewById(R.id.spEscalaActual);
        spEscalaDeseada = (Spinner) findViewById(R.id.spEscalaDeseada);

        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource(this,R.array.escalas,android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEscalaActual.setAdapter(spinner_adapter);
        spEscalaDeseada.setAdapter(spinner_adapter);
    }

    public void calcular(View view){

        double temp = Double.parseDouble(etTemperatura.getText().toString());
        double temperatura=0;

        String tempDada = spEscalaActual.getSelectedItem().toString();
        String tempDeseada=spEscalaDeseada.getSelectedItem().toString();

        if(tempDada.equals(tempDeseada)){
            Toast.makeText(this,"No se puede calcular entre el mismo",Toast.LENGTH_SHORT).show();
            return;
        }else {
            switch (tempDada) {
                case "Fahrenheit":
                    switch (tempDeseada) {
                        case "Celsius":
                            temperatura = (temp - 32.00) * (5.00 / 9.00);
                            break;
                        case "Kelvin":
                            temperatura = (temp - 32.00) * (5.00 / 9.00) + 273.15;
                            break;
                        case "Rankine":
                            temperatura = temp + 459.67;
                            break;
                    }
                    break;
                case "Celsius":
                    switch (tempDeseada) {
                        case "Fahrenheit":
                            temperatura = temp * (9.00 / 5.00) + 32.00;
                            break;
                        case "Kelvin":
                            temperatura = temp + 273.15;
                            break;
                        case "Rankine":
                            temperatura = (temp + 459.67) * (9.00 / 5.00);
                            break;
                    }
                    break;
                case "Kelvin":
                    switch (tempDeseada) {
                        case "Fahrenheit":
                            temperatura = (temp - 273.15) * (9.00 / 5.00) + 32.00;
                            break;
                        case "Celsius":
                            temperatura = temp - 273.15;
                            break;
                        case "Rankine":
                            temperatura = temp * (9.00 / 5.00);
                            break;
                    }
                    break;
                case "Rankine":
                    switch (tempDeseada) {
                        case "Fahrenheit":
                            temperatura = temp - 459.67;
                            break;
                        case "Celsius":
                            temperatura = (temp - 459.67) * (5.00 / 9.00);
                            break;
                        case "Kelvin":
                            temperatura = temp * (5.00 / 9.00);
                            break;
                    }
                    break;
            }
        }
        tvTempConvertida.setText(""+temperatura);
        Toast.makeText(this,"Temperatura Calculada",Toast.LENGTH_SHORT).show();

    }//Fin Boton calcular

    public void limpiar(View view) {
        tvTempConvertida.setText("");
        etTemperatura.setText("");
    }//Fin Boton Limpiar
}//Fin de clase
