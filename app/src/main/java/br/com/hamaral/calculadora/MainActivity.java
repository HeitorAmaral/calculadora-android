package br.com.hamaral.calculadora;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getButtonValue(View view) {
        Button button = (Button) findViewById(view.getId());
        String buttonValue = button.getText().toString();

        EditText editTextVal1 = (EditText) findViewById(R.id.editTxtVal1);
        EditText editTextVal2 = (EditText) findViewById(R.id.editTxtVal2);

        if (editTextVal1.isFocused()) {
            editTextVal1.setText(editTextVal1.getText().toString().concat(buttonValue));
        } else if (editTextVal2.isFocused()) {
            editTextVal2.setText(editTextVal2.getText().toString().concat(buttonValue));
        } else {
            Toast.makeText(this, "Selecione um dos campos para inserir o valor!", Toast.LENGTH_SHORT).show();
        }

    }

    public void calculateByOperationCode(View view) {
        EditText editTextVal1 = (EditText) findViewById(R.id.editTxtVal1);
        EditText editTextVal2 = (EditText) findViewById(R.id.editTxtVal2);


        if (editTextVal1.getText().toString().trim().isEmpty() || editTextVal2.getText().toString().trim().isEmpty())
        {
            Toast.makeText(this, "É necessário preencher os todos campos de valor!", Toast.LENGTH_SHORT).show();
        } else{
            Button button = (Button) findViewById(view.getId());
            String textButton = button.getText().toString();

            EditText editTextResultado = (EditText) findViewById(R.id.editTextResultado);

            Double doubleVal1 = Double.parseDouble(editTextVal1.getText().toString());
            Double doubleVal2 = Double.parseDouble(editTextVal2.getText().toString());

            switch (textButton) {
                case "+":
                    editTextResultado.setText(calculate(1, doubleVal1, doubleVal2).toString());
                    break;
                case "-":
                    editTextResultado.setText(calculate(2, doubleVal1, doubleVal2).toString());
                    break;
                case "×":
                    editTextResultado.setText(calculate(3, doubleVal1, doubleVal2).toString());
                    break;
                case "÷":
                    editTextResultado.setText(calculate(4, doubleVal1, doubleVal2).toString());
                    break;
            }
        }
    }

    public Double calculate(int operationCode, Double valor1, Double valor2) {
        Double resultado = 0.00;
        switch (operationCode) {
            case 1:
                resultado = valor1 + valor2;
                break;
            case 2:
                resultado = valor1 - valor2;
                break;
            case 3:
                resultado = valor1 * valor2;
                break;
            case 4:
                resultado = valor1 / valor2;
                break;

        }
        return resultado;
    }

    public void cleanFields(View view) {
        EditText editTextVal1 = (EditText) findViewById(R.id.editTxtVal1);
        EditText editTextVal2 = (EditText) findViewById(R.id.editTxtVal2);
        EditText editTextResultado = (EditText) findViewById(R.id.editTextResultado);

        editTextVal1.setText("");
        editTextVal2.setText("");
        editTextResultado.setText("");
    }

    /**
     * Esconda o teclado
     */
    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}