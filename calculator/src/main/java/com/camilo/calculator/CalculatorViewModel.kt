package com.camilo.calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    var resultado = MutableLiveData("0")
    private var value1 = "0"
    private var value2 = ""
    private var operador =""


    fun btnUnoOnClickListener() {
        changeValues("1")
    }

    private fun changeValues(newNumber: String){
        resultado.value += newNumber;
        if(operador == "")
            value1 += newNumber
        else
            value2 += newNumber
    }

    fun btnDosOnClickListener() {
        changeValues("2")
    }

    fun btnTresOnClickListener() {
        changeValues("3")
    }

    fun btnCuatroOnClickListener() {
        changeValues("4")
    }

    fun btnCincoOnClickListener() {
        changeValues("5")
    }

    fun btnSeisOnClickListener() {
        changeValues("6")
    }

    fun btnSieteOnClickListener() {
        changeValues("7")
    }

    fun btnOchoOnClickListener() {
        changeValues("8")
    }

    fun btnNueveOnClickListener() {
        changeValues("9")
    }

    fun btnSumaOnClickListener() {
        if (operador == "") {
            resultado.value += " + "
            operador = "+"
        }
        else {
            doOperation()
        }
    }

    fun doOperation(){
        when (operador){
            "+" -> resultado.value = (value1.toDouble() + value1.toDouble()).toString()
            "-" -> resultado.value = (value1.toDouble() - value1.toDouble()).toString()
        }
        operador = ""
    }

    fun btnRestaOnClickListener() {
        if (operador == "") {
            resultado.value += " - "
            operador = "-"
        }
        else {
            doOperation()
        }
    }

//    fun btnMultiplicaOnClickListener() {
//        reserva.value = Resultado.getText().toString();
//        operador.value = "*";
//        Resultado.setText("");
//    }
//
//    fun btnDivideOnClickListener() {
//        reserva.value = Resultado.getText().toString();
//        operador.value = "/";
//        Resultado.setText("");
//    }
//
//    fun btnPuntoOnClickListener() {
//        resultado.value = resultado.value + ".";
//    }
//
//    fun btnCleanOnClickListener() {
//        mostrar = "";
//        Resultado.setText(mostrar);
//        reserva.value = "";
//        operador.value = "";
//    });
//
//    fun btnBorrarOnClickListener() {
//        resultado.value = resultado.value.substring(0, mostrar.length() - 1);
//    }
//
    fun btnIgualOnClickListener() {
        doOperation()
    }
//    if(operador.value.equals("+"))
//    {
//        resultado =
//            Double.parseDouble(reserva.value) + Double.parseDouble(Resultado.getText().toString());
//        Resultado.setText(String.valueOf(resultado));
//    }
//    if(operador.value.equals("/"))
//    {
//        resultado =
//            Double.parseDouble(reserva.value) / Double.parseDouble(Resultado.getText().toString());
//        Resultado.setText(String.valueOf(resultado));
//    }
//    if(operador.value.equals("*"))
//    {
//        resultado =
//            Double.parseDouble(reserva.value) * Double.parseDouble(Resultado.getText().toString());
//        Resultado.setText(String.valueOf(resultado));
//    }
//}
//});
}
