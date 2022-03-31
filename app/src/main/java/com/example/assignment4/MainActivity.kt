package com.example.assignment4

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var txtView: TextView;
    private  var operator: String = "";
    private var operand: Double = 0.0;
    private var equalsWasClicked: Boolean = false;
    private var lastDot: Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtView = findViewById(R.id.textView);
    }

    fun onRegularBtnClick(btn: View) {
        if (btn is Button) {
            transition()

            val current = txtView.text.toString().trimStart('0');
            val btnContent = btn.text.toString();

            val altered = current + btnContent;
            txtView.text = altered;
        }
    }

    private fun transition() {
        lastDot = false

        if (equalsWasClicked) {
            txtView.text = "";
            equalsWasClicked = false;
        }
    }

    fun onBinaryOperatorBtnClick(btn: View) {
        if (btn is Button) {
            operator = btn.text.toString();
            val txtView = txtView.text.toString();
            if (txtView.isNotEmpty()) {
                operand = txtView.toDouble();
            }
            this.txtView.text = "";
        }
    }


     fun onEqualsBtnClick(btn: View) {
        equalsWasClicked = true;

        val current = txtView.text.toString();
        var nextOp = 0.0;

        if (current.isNotEmpty()) {
            nextOp = current.toDouble();
        }
        executeBinaryOperation(nextOp)
    }

    @SuppressLint("SetTextI18n")
    private fun executeBinaryOperation(secondOperand: Double) {
        when (operator) {
            "+" -> txtView.text = ((operand + secondOperand).toString().trimToInt())
            "-" -> txtView.text = ((operand - secondOperand).toString().trimToInt())
            "/" -> txtView.text = ((operand / secondOperand).toString().trimToInt())
            "*" -> txtView.text = ((operand * secondOperand).toString().trimToInt())
        }
    }

    fun onClearBtnClick(btn: View) {
        txtView.text = "";
        operator = "";
        operand = 0.0;
    }

    fun onReverseSignBtnClick(btn: View){

        val current = txtView.text.toString().toDouble()
        txtView.text = (current * -1).toString().trimToInt()
    }

    fun onSqrtClick(btn: View){

        val result = sqrt(txtView.text.toString().toDouble())
        txtView.text = result.toString().trimToInt()
    }

    fun onPercentageBtnClick(btn: View){

        val result = txtView.text.toString().toDouble() / 100
        txtView.text = result.toString().trimToInt()
    }

    @SuppressLint("SetTextI18n")
    fun onDotBtnClicked(btn: View){
        if (!lastDot && !txtView.text.contains('.')) {
            txtView.text = txtView.text.toString() + "."
            lastDot = true
        }
     }

}