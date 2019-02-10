package com.example.fyg.multiactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    private String Carga, Cantidad, RControl, Full, NomLugarSalida, NomLugarLlegada, fechaHora, Pago, TipoCaja;
    private TextView tvCantidadCarga, tvCarga, tvDobleRemolque, tvFechaAproxSalida, tvNombreLugarLlegada, tvNombreLugarSalida, tvPago, tvRControl, tvTipoCaja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        recibeDatos();

        tvCantidadCarga = (TextView) findViewById(R.id.cantidadCarga);
        tvCarga = (TextView) findViewById(R.id.carga);
        tvDobleRemolque = (TextView) findViewById(R.id.dobleRemolque);
        tvFechaAproxSalida = (TextView) findViewById(R.id.fechaAproxSalida);
        tvNombreLugarLlegada = (TextView) findViewById(R.id.nombreLugarLlegada);
        tvNombreLugarSalida = (TextView) findViewById(R.id.nombreLugarSalida);
        tvPago = (TextView) findViewById(R.id.pago);
        tvRControl = (TextView) findViewById(R.id.rControl);
        tvTipoCaja = (TextView) findViewById(R.id.tipoCaja);

        publicaDatos();
    }

    public void recibeDatos(){
        Bundle extras = getIntent().getExtras();
        Carga = extras.getString("carga");
        TipoCaja = extras.getString("tipoCaja");
        Cantidad = extras.getString("cantidad");
        RControl = extras.getString("rControl");
        Full = extras.getString("full");
        NomLugarSalida = extras.getString("nomLugarSalida");
        NomLugarLlegada = extras.getString("nomLugarLlegada");
        fechaHora = extras.getString("fechaHora");
        Pago = extras.getString("pago");
    }

    public void publicaDatos(){
        tvCantidadCarga.setText(Cantidad);
        tvCarga.setText(Carga);
        tvDobleRemolque.setText(Full);
        tvFechaAproxSalida.setText(fechaHora);
        tvNombreLugarLlegada.setText(NomLugarLlegada);
        tvNombreLugarSalida.setText(NomLugarSalida);
        tvPago.setText(Pago);
        tvRControl.setText(RControl);
        tvTipoCaja.setText(TipoCaja);
    }

    public void cierraSesion(View v){
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    public void regresar(View v){
        Intent intent = new Intent(this, Activity2.class);
        finish();
        startActivity(intent);
    }
}
