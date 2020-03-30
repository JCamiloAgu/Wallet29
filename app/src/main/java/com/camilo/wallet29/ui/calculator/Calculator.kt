package com.camilo.wallet29.ui.calculator

import androidx.lifecycle.MutableLiveData

class Calculator {

    var resultado = MutableLiveData("0")
    var operador = MutableLiveData("")
    private var value1 = ""
    private var value2 = ""

    fun addValue(newNumber: Char) {
        if (resultado.value == "0")
            resultado.value = "$newNumber"
        else
            resultado.value += newNumber;

        if (operador.value == "")
            value1 += newNumber
        else
            value2 += newNumber
    }

    fun btnSumaOnClickListener() {
        if (operador.value != "") {
            if(value2.isEmpty()) {
                resultado.value = resultado.value?.replace(" ${if(operador.value == "*")  "X" else operador.value} ", " + ")
                operador.value = "+"
            }
            else {
                doOperation()
                operador.value = "+"
                resultado.value += " + "
            }
        }else {
            operador.value = "+"
            resultado.value += " + "
        }
    }

    fun doOperation() {
        if (value1.isNotEmpty() && value2.isNotEmpty()) {

            val mValue1 = value1.replace(',', '.').toDouble()
            val mValue2 = value2.replace(',', '.').toDouble()

            when (operador.value) {
                "+" -> resultado.value = (mValue1 + mValue2).toString().replace(".", ",")
                "-" -> resultado.value = (mValue1 - mValue2).toString().replace(".", ",")
                "*" -> resultado.value = (mValue1 * mValue2).toString().replace(".", ",")
                "/" -> resultado.value = (mValue1 / mValue2).toString().replace(".", ",")
            }
            if (resultado.value!!.last() == '0' && resultado.value!![resultado.value!!.lastIndex - 1] == ',')
                resultado.value = resultado.value!!.replace(",0", "")
            value2 = ""
            value1 = resultado.value!!
            operador.value = ""
        }
    }

    fun btnRestaOnClickListener() {
        if (operador.value != "") {
            if(value2.isEmpty()) {
                resultado.value = resultado.value?.replace(" ${if(operador.value == "*")  "X" else operador.value} ", " - ")
                operador.value = "-"
            }
            else {
                doOperation()
                operador.value = "-"
                resultado.value += " - "
            }
        }else {
            operador.value = "-"
            resultado.value += " - "
        }
    }

    fun btnMultiplicaOnClickListener() {
        if (operador.value != "") {
            if(value2.isEmpty()) {
                resultado.value = resultado.value?.replace(" ${if(operador.value == "*")  "X" else operador.value} ", " X ")
                operador.value = "*"
            }
            else {
                doOperation()
                operador.value = "*"
                resultado.value += " X "
            }
        }else {
            operador.value = "*"
            resultado.value += " X "
        }
    }

    fun btnDivideOnClickListener() {
        if (operador.value != "") {
            if(value2.isEmpty()) {
                resultado.value = resultado.value?.replace(" ${if(operador.value == "*")  "X" else operador.value} ", " / ")
                operador.value = "/"
            }
            else {
                doOperation()
                operador.value = "/"
                resultado.value += " / "
            }
        }else {
            operador.value = "/"
            resultado.value += " / "
        }
    }

    fun btnCommaOnClickListener() {

        if(!value1.contains(',')){
            if (value2 == "") {
                value1 += "."
                resultado.value += ","
            }
        }

        else if(!value2.contains(',')){
                value2 += "."
                resultado.value += ","
        }
    }

    fun btnCleanOnClickListener() {
        resultado.value = "0";
        value1 = "";
        value2 = "";
        operador.value = "";
    }

    fun btnBorrarOnClickListener() {
        //Comprobar si resultado no es 0
        if (resultado.value != "0") {
            // comprobar el valor del Value 2 para saber si elimina el caracter de value1 o de value2
            if (value2 != "")
                value2 = value2.substring(0, value2.length - 1);
            else if (value1.length >= 0 && resultado.value!!.length <= value1.length)
                value1 = value1.substring(0, value1.length - 1);

            //Comprobar si el resultado en su última posicion es un espacio para borrar tres caractes (" + ")
            if (resultado.value!![resultado.value!!.length - 1] == ' ') {
                resultado.value = resultado.value?.substring(0, resultado.value!!.length - 3);
                operador.value = ""
            } else {
                resultado.value = resultado.value?.substring(0, resultado.value!!.length - 1);
                if (resultado.value == "")
                    resultado.value = "0"
            }
        } else
            resultado.value = "0"
    }

}