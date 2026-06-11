package com.example.simplecalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import com.example.simplecalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final StringBuilder history = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Load saved history
        SharedPreferences prefs = getSharedPreferences("calc_prefs", MODE_PRIVATE);
        String savedHistory = prefs.getString("history", "");
        history.append(savedHistory);
        binding.tvHistory.setText(history.toString());

        setupButton(binding.btnAdd, v -> calculate('+'));
        setupButton(binding.btnSub, v -> calculate('-'));
        setupButton(binding.btnMul, v -> calculate('*'));
        setupButton(binding.btnDiv, v -> calculate('/'));

        setupButton(binding.btnSquare, v -> {
            String numText = binding.num1.getText().toString();

            if (numText.isEmpty()) {
                Toast.makeText(this, R.string.enter_a_number, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double num = Double.parseDouble(numText);
                double result = num * num;

                String resultStr = getString(R.string.result_text, result);
                binding.result.setText(resultStr);

                updateHistory(String.format(Locale.getDefault(), "%.2f² = %.2f\n", num, result));
            } catch (NumberFormatException e) {
                binding.result.setText(R.string.invalid_input);
            }
        });

        setupButton(binding.btnPercent, v -> {
            String numText = binding.num1.getText().toString();

            if (numText.isEmpty()) {
                Toast.makeText(this, R.string.enter_a_number, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double num = Double.parseDouble(numText);
                double result = num / 100.0;

                String resultStr = getString(R.string.result_text, result);
                binding.result.setText(resultStr);

                updateHistory(String.format(Locale.getDefault(), "%.2f%% = %.2f\n", num, result));
            } catch (NumberFormatException e) {
                binding.result.setText(R.string.invalid_input);
            }
        });

        setupButton(binding.btnClear, v -> {
            binding.num1.setText("");
            binding.num2.setText("");
            binding.result.setText(R.string.result_label);
            history.setLength(0);
            binding.tvHistory.setText("");
            saveHistory();
        });
    }

    private void setupButton(View button, View.OnClickListener listener) {
        button.setOnClickListener(v -> {
            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            listener.onClick(v);
        });
    }

    private void updateHistory(String entry) {
        history.insert(0, entry);
        binding.tvHistory.setText(history.toString());
        saveHistory();
    }

    private void saveHistory() {
        SharedPreferences prefs = getSharedPreferences("calc_prefs", MODE_PRIVATE);
        prefs.edit().putString("history", history.toString()).apply();
    }

    private void calculate(char operator) {
        String n1 = binding.num1.getText().toString();
        String n2 = binding.num2.getText().toString();

        if (n1.isEmpty() || n2.isEmpty()) {
            binding.result.setText(R.string.enter_both_numbers);
            return;
        }

        try {
            double a = Double.parseDouble(n1);
            double b = Double.parseDouble(n2);
            double answer;

            switch (operator) {
                case '+':
                    answer = a + b;
                    break;
                case '-':
                    answer = a - b;
                    break;
                case '*':
                    answer = a * b;
                    break;
                case '/':
                    if (b == 0) {
                        binding.result.setText(R.string.cannot_divide_by_zero);
                        return;
                    }
                    answer = a / b;
                    break;
                default:
                    return;
            }

            String result = getString(R.string.result_text, answer);
            binding.result.setText(result);

            // Add to history
            updateHistory(String.format(Locale.getDefault(), "%s %c %s = %.2f\n", n1, operator, n2, answer));

        } catch (NumberFormatException e) {
            binding.result.setText(R.string.invalid_input);
        }
    }
}