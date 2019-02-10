package com.example.fyg.multiactivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Activity2 extends AppCompatActivity {

    private Button Bfecha, Bhora;
    private TextView efecha, ehora;
    private int dia,mes, año, hora, minutos;

    private String SpinnerTipoCaja, Carga, NomLugarSalida, NomLugarLlegada, Fecha, Hora, fechaHora;
    private int Pago, Cantidad;
    private Boolean RControl, Full;

    private Spinner txtSpinnerTipoCaja;
    private EditText txtCarga, txtCantidad, txtNomLugarSalida, txtNomLugarLlegada, txtPago;
    private Switch switchRControl, switchFull;

    private String correoCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        txtSpinnerTipoCaja = (Spinner) findViewById(R.id.tipoCaja_spinner);
        txtCarga = (EditText) findViewById(R.id.txtCarga);
        txtCantidad = (EditText) findViewById(R.id.txtCantidad);
        txtNomLugarSalida = (EditText) findViewById(R.id.txtNomLugarSalida);
        txtNomLugarLlegada = (EditText) findViewById(R.id.txtNomLugarLlegada);
        txtPago = (EditText) findViewById(R.id.txtPago);
        switchRControl = (Switch) findViewById(R.id.switchRControl);
        switchFull = (Switch) findViewById(R.id.switchFull);

        efecha = (TextView) findViewById(R.id.efecha);
        ehora = (TextView) findViewById(R.id.ehora);
        Bfecha = (Button) findViewById(R.id.Bfecha);
        Bhora = (Button) findViewById(R.id.Bhora);

        LlenaOpciones();
    }

    public void creaViaje(View view){
        SpinnerTipoCaja = txtSpinnerTipoCaja.getSelectedItem().toString();
        Carga = txtCarga.getText().toString();
        NomLugarSalida = txtNomLugarSalida.getText().toString();
        NomLugarLlegada = txtNomLugarLlegada.getText().toString();
        RControl = switchRControl.isChecked();
        Full = switchFull.isChecked();
        Hora = ehora.getText().toString();
        Fecha = efecha.getText().toString();


        //VALIDANDO
        //Valida que todos los campos esten llenos
        if(TextUtils.isEmpty(SpinnerTipoCaja) || TextUtils.isEmpty(Carga) || TextUtils.isEmpty(txtCantidad.getText().toString()) || TextUtils.isEmpty(NomLugarSalida)
                || TextUtils.isEmpty(NomLugarLlegada) || TextUtils.isEmpty(txtPago.getText().toString()) || TextUtils.isEmpty(RControl.toString())
                || TextUtils.isEmpty(Full.toString()) || TextUtils.isEmpty(Hora) || TextUtils.isEmpty(Fecha)){

            Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();

        }else{
            Cantidad = Integer.parseInt(txtCantidad.getText().toString());
            Pago = Integer.parseInt(txtPago.getText().toString());
            fechaHora = Fecha+" "+Hora;
            //REGISTRANDO
            Toast.makeText(this, "El viaje fue registrado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Activity3.class);
            intent.putExtra("carga", Carga);
            intent.putExtra("tipoCaja", SpinnerTipoCaja);
            intent.putExtra("cantidad", Integer.toString(Cantidad));
            intent.putExtra("rControl", Boolean.toString(RControl));
            intent.putExtra("full", Boolean.toString(Full));
            intent.putExtra("nomLugarSalida", NomLugarSalida);
            intent.putExtra("nomLugarLlegada", NomLugarLlegada);
            intent.putExtra("fechaHora", fechaHora);
            intent.putExtra("pago", Integer.toString(Pago));
            finish();
            startActivity(intent);
        }
    }

    public void LlenaOpciones(){
        String[] opciones = {"Refrigerada 48ft", "Refrigerada 53ft", "Seca 48ft", "Seca 53ft", "Plana", "Jaula", "Lovoy", "Cama baja"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opciones);
        txtSpinnerTipoCaja.setAdapter(adapter);
    }

    //CODIGO PARA PICKER DATE Y TIME
    public void pickerOnClick(View view) {
        if(view == Bfecha){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            año = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    String dia;
                    String mes;
                    int tamañoDia = (Integer.toString(dayOfMonth)).length();
                    int tamañoMes = (Integer.toString(monthOfYear+1)).length();

                    if(tamañoDia == 1){
                        dia = "0" + (Integer.toString(dayOfMonth));
                    }else
                        dia = Integer.toString(dayOfMonth);

                    if(tamañoMes == 1){
                        mes = "0" + (Integer.toString(monthOfYear+1));
                    }else
                        mes = Integer.toString(monthOfYear+1);

                    efecha.setText(dia+"/"+mes+"/"+year);
                }
            }
                    ,año, mes, dia);
            datePickerDialog.show();
        }
        if(view==Bhora){
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minutos= c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    ehora.setText(hourOfDay+":"+minute);
                }
            },hora, minutos,false);
            timePickerDialog.show();
        }
    }
}
