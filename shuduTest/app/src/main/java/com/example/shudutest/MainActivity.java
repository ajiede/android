package com.example.shudutest;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private GridAdapter adapter;
    private String[] numbers;
    private int[] puzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        adapter = new GridAdapter();
        gridView.setAdapter(adapter);

        numbers = generateNumbers();
        puzzle = generatePuzzle();

        fillGrid();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int row = position / 9;
                int col = position % 9;
                int value = puzzle[row * 9 + col];

                if (value == 0) {
                    // The cell is empty, allow user to fill it
                    // You can implement your own logic for entering numbers
                    // For simplicity, let's just randomly fill the cell with a valid number
                    Random random = new Random();
                    int randomNumber = 0;
                    boolean isValid = false;

                    while (!isValid) {
                        randomNumber = random.nextInt(9) + 1;
                        isValid = isValidMove(row, col, randomNumber);
                    }

                    puzzle[row * 9 + col] = randomNumber;
                    adapter.notifyDataSetChanged();

                    if (isPuzzleSolved()) {
                        Toast.makeText(MainActivity.this, "Congratulations! Puzzle solved.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // The cell is pre-filled, show a message
                    Toast.makeText(MainActivity.this, "This cell is pre-filled.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String[] generateNumbers() {
        String[] numbers = new String[10];
        for (int i = 0; i <= 9; i++) {
            numbers[i] = String.valueOf(i);
        }
        return numbers;
    }

    private int[] generatePuzzle() {
        int[] puzzle = new int[81];
        // Generate a complete and valid puzzle using your preferred algorithm or library
        // For simplicity, let's assume we have
        int[] initialPuzzle = {
                5, 3, 0, 0, 7, 0, 0, 0, 0,
                6, 0, 0, 1, 9, 5, 0, 0, 0,
                0, 9, 8, 0, 0, 0, 0, 6, 0,
                8, 0, 0, 0, 6, 0, 0, 0, 3,
                4, 0, 0, 8, 0, 3, 0, 0, 1,
                7, 0, 0, 0, 2, 0, 0, 0, 6,
                0, 6, 0, 0, 0, 0, 2, 8, 0,
                0, 0, 0, 4, 1, 9, 0, 0, 5,
                0, 0, 0, 0, 8, 0, 0, 7, 9
        };

        System.arraycopy(initialPuzzle, 0, puzzle, 0, initialPuzzle.length);

        return puzzle;
    }

    private void fillGrid() {
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] == 0) {
                adapter.add(""); // Add an empty string for an empty cell
            } else {
                adapter.add(String.valueOf(puzzle[i]));
            }
        }
    }

    private boolean isValidMove(int row, int col, int number) {
        // Check if the number is already present in the same row
        for (int i = 0; i < 9; i++) {
            if (puzzle[row * 9 + i] == number) {
                return false;
            }
        }

        // Check if the number is already present in the same column
        for (int i = 0; i < 9; i++) {
            if (puzzle[i * 9 + col] == number) {
                return false;
            }
        }

        // Check if the number is already present in the same 3x3 box
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (puzzle[i * 9 + j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isPuzzleSolved() {
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] == 0) {
                return false; // There are still empty cells
            }
        }
        return true;
    }

    private class GridAdapter extends BaseAdapter {

        private ArrayList<String> cellValues;
        private int strokeWidth;
        private int[] puzzle;

        public GridAdapter(ArrayList<String> cellValues) {
            this.cellValues = cellValues;
            this.puzzle = new int[cellValues.size()]; // 使用 cellValues 的长度来初始化 puzzle 数组
            // 其他代码...
        }



        public GridAdapter(int strokeWidth) {
            cellValues = new ArrayList<>();
            this.strokeWidth = strokeWidth;
        }

        public GridAdapter() {
            cellValues = new ArrayList<>();
            puzzle = new int[cellValues.size()]; // 初始化 puzzle 数组
        }


        public void add(String value) {
            cellValues.add(value);
        }

        @Override
        public int getCount() {
            return puzzle.length;
        }

        @Override
        public Object getItem(int position) {
            return puzzle[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View cellView = convertView;
            ViewHolder holder;

            if (cellView == null) {
                cellView = getLayoutInflater().inflate(R.layout.grid_item, null);
                holder = new ViewHolder(cellView); // 传递 cellView 参数给构造函数
                cellView.setTag(holder);
            } else {
                holder = (ViewHolder) cellView.getTag();
            }

            int row = position / 9;
            int col = position % 9;
            int value = puzzle[row * 9 + col];

            // Set cell background and border
            GradientDrawable drawable = (GradientDrawable) getResources().getDrawable(R.drawable.cell_bg);
            cellView.setBackground(drawable);

            if (row % 3 == 0) {
                drawable.setStroke(strokeWidth, Color.BLACK, 4, 4);
            } else {
                drawable.setStroke(strokeWidth, Color.BLACK, 0, 4);
            }

            if (col % 3 == 0) {
                drawable.setStroke(strokeWidth, Color.BLACK);
            }


            if (value == 0) {
                holder.cellNumber.setText("");
            } else {
                holder.cellNumber.setText(String.valueOf(value));
            }

            return cellView;
        }

        private class ViewHolder {
            TextView cellNumber;

            ViewHolder(View view) {
                cellNumber = view.findViewById(R.id.cellNumber);
            }
        }
    }
}

