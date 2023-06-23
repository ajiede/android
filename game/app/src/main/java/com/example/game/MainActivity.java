package com.example.game;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText[][] sudokuGrid = new EditText[9][9];
    private Button checkButton;
    private int[][] solutionGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sudokuGrid = new EditText[9][9];
        solutionGrid = new int[9][9];

        // 获取布局文件中的EditText视图
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String editTextId = "cell_" + i + j;
                int resId = getResources().getIdentifier(editTextId, "id", getPackageName());
                sudokuGrid[i][j] = findViewById(resId);
            }
        }

        // 添加文本更改监听器，用于检查用户输入的数字
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final int row = i;
                final int col = j;

                sudokuGrid[i][j].addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        checkInput(row, col);
                    }
                });
            }
        }

        // 生成随机的初始题目
        generateInitialPuzzle();

        // 设置检查按钮的点击事件
        checkButton = findViewById(R.id.btn_check);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSolution();
            }
        });
    }

    // 生成随机的初始题目
    private void generateInitialPuzzle() {
        // 数独初始题目数组
        int[][] puzzle = {
                {0, 0, 0, 0, 0, 6, 8, 0, 7},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 3, 9, 0, 0, 0, 0, 0, 0},
                {4, 0, 0, 0, 0, 0, 0, 3, 0},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {8, 0, 0, 0, 0, 7, 2, 0, 0},
                {7, 0, 0, 0, 0, 0, 0, 0, 5},
                {0, 0, 0, 9, 0, 0, 0, 0, 0},
                {0, 0, 0, 3, 2, 0, 1, 0, 0}
        };

        // 将初始题目复制到数独格子中
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (puzzle[i][j] != 0) {
                    sudokuGrid[i][j].setText(String.valueOf(puzzle[i][j]));
                    sudokuGrid[i][j].setEnabled(false);
                }
                solutionGrid[i][j] = puzzle[i][j];
            }
        }
    }

    // 检查用户填入的数字是否正确
    private void checkInput(int row, int col) {
        String input = sudokuGrid[row][col].getText().toString().trim();
        if (!input.isEmpty()) {
            int number = Integer.parseInt(input);
            if (number < 1 || number > 9 || !isValidPlacement(row, col, number)) {
                sudokuGrid[row][col].setText("");
                Toast.makeText(this, "错误的数字！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 检查数独解答是否正确
    private void checkSolution() {
        boolean isValid = true;

        // 遍历数独格子，检查填入的数字是否正确
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String input = sudokuGrid[i][j].getText().toString().trim();
                if (!input.isEmpty()) {
                    int number = Integer.parseInt(input);
                    if (number < 1 || number > 9 || !isValidPlacement(i, j, number)) {
                        isValid = false;
                        break;
                    }
                }
            }
        }

        // 显示检查结果
        if (isValid) {
            Toast.makeText(this, "答案正确！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "答案错误！", Toast.LENGTH_SHORT).show();
        }
    }

    // 检查填入的数字是否符合数独规则
    private boolean isValidPlacement(int row, int col, int number) {
        // 检查所在行和列是否有重复数字
        for (int i = 0; i < 9; i++) {
            if (solutionGrid[row][i] == number || solutionGrid[i][col] == number) {
                return false;
            }
        }

        // 检查所在九宫格是否有重复数字
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (solutionGrid[i][j] == number) {
                    return false;
                }
            }
        }

        return true;
    }
}
