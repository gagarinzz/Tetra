package com.example.n18;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    private EditText editText, editText1, editText2, editText3;
    private boolean isShiftPressed = false;
    private Button submitButton, buttonEN, buttonRU, buttonUA;
    private char[] selectedLanguage;
    private EditText activeEditText;

    private final String[] characters = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "й", "ц", "у", " ", "ъ", "ш", "щ", "з", "к", "е",
            "х", "л", "н", "г", "ё", "д", "ж", "э", "ф", "ы",
            "в", "п", "р", "о", "я", "ч", "б", "ю", "с", "м",
            "а", "и", "т", "ь"
    };
    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       editText = findViewById(R.id.editText2);


        for (int i = 0; i < characters.length; i++) {
            Button button = findViewById(getResources().getIdentifier("button" + (i + 1), "id", getPackageName()));
            final String character = characters[i];

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insertCharacterIntoEditText(isShiftPressed ? character.toUpperCase() : character);
                    if (isShiftPressed) {
                        isShiftPressed = false; // Сброс состояния Shift после символа
                    }
                }
            });
        }

        Button shiftButton = findViewById(R.id.shiftButton);
        shiftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShiftPressed = !isShiftPressed; // Переключение состояния Shift
            }
        });

        Button backspaceButton = findViewById(R.id.backspaceButton);
        backspaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int start = Math.max(editText.getSelectionStart(), 0);
                int end = Math.max(editText.getSelectionEnd(), 0);
                if (start != end) {
                    editText.getText().replace(Math.min(start, end), Math.max(start, end), "", 0, 0);
                } else if (start > 0) {
                    editText.getText().replace(start - 1, start, "");
                }
            }
        });

        Button enterButton = findViewById(R.id.enterButton);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertCharacterIntoEditText("\n");
            }
        });
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        submitButton = findViewById(R.id.submitButton);
        buttonEN = findViewById(R.id.buttonEN);
        buttonRU = findViewById(R.id.buttonRU);
        buttonUA = findViewById(R.id.buttonUA);

        // Начально выбран английский язык
        selectedLanguage = getLanguageArray("EN");

        buttonEN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedLanguage = getLanguageArray("EN");
                Toast.makeText(MainActivity.this, "Выбран английский язык", Toast.LENGTH_SHORT).show();
            }
        });

        buttonRU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedLanguage = getLanguageArray("RU");
                Toast.makeText(MainActivity.this, "Выбран русский язык", Toast.LENGTH_SHORT).show();
            }
        });

        buttonUA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedLanguage = getLanguageArray("UA");
                Toast.makeText(MainActivity.this, "Выбран украинский язык", Toast.LENGTH_SHORT).show();
            }


            public EditText getActiveEditText() {
                if (editText1.hasFocus()) {
                    return editText1;
                } else if (editText2.hasFocus()) {
                    return editText2;
                    //editText = findViewById(R.id.editText2);
                }
                return null;

            }

        });

        //editText = findViewById(R.id.editText1);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editText1.getText().toString();
                String text2 = editText2.getText().toString();

                String xorKey = getXORKey(text1, text2);
                editText3.setText(xorKey);
            }
        }

        );
// Устанавливаем активное текстовое поле при фокусе
        editText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    activeEditText = editText1;
                    editText = findViewById(R.id.editText1);
                }
            }
        });

        editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    activeEditText = editText2;
                    editText = findViewById(R.id.editText2);
                }
            }
        }

        );
    }

    private void insertTextAtCursorPosition(EditText editText, String text) {
        int start = Math.max(editText.getSelectionStart(), 0);
        int end = Math.max(editText.getSelectionEnd(), 0);
        editText.getText().replace(Math.min(start, end), Math.max(start, end), text, 0, text.length());
    }


    private void insertCharacterIntoEditText(String character) {
        int start = Math.max(editText.getSelectionStart(), 0);
        int end = Math.max(editText.getSelectionEnd(), 0);
        editText.getText().replace(Math.min(start, end), Math.max(start, end),
                character, 0, character.length());
    }


    private char[] getLanguageArray(String language) {
        switch (language) {
            case "EN":
                return new char[] {' ','g', 'i', 't', 'y', 's', 'v', 'x', 'k', 'r', 'h', 'n', 'l', 'c', 'u', 'd', '\\', ';', '|', '?', ',', '5', '=', '9', '^', '+', '"', '@', '!', '6', '_', '8', '/', '7', '1', '3', '.', '2', '%', '*', ':', '~', '4', 'й', '-', 'ё', 'ъ', '0', '×', 'ĩ', 'j', 'ã', 'ē', 'z', 'w', 'q', 'p', 'f', 'm', 'a', 'b', 'e', 'õ', 'o', '°', 'G', 'I', 'T', 'Y', 'S', 'V', 'X', 'K', 'R', 'H', 'N', 'L', 'C', 'U', 'D', '`', '{', '(', '…', '>', '$', '→', '©', '[', '←', '№', '®', '—', '€', '¥', '&', '\'', '}', ']', '↑', '<', '§', '‰', '₴', ')', '¯', '™', 'Й', '↓', 'Ё', 'Ъ', '#', '÷', 'Ĩ', 'J', 'Ã', 'Ē', 'Z', 'W', 'Q', 'P', 'F', 'M', 'A', 'B', 'E', 'Õ', 'O'};
            case "RU":
                return new char[] {' ','б', 'ы', 'т', 'у', 'ю', 'ш', 'х', 'к', 'г', 'н', 'п', 'л', 'с', 'ц', 'д', '\\', ';', '|', '?', ',', '5', '=', '9', '^', '+', '"', '@', '!', '6', '_', '8', '/', '7', '1', '3', '.', '2', '%', '*', ':', '~', '4', 'й', '-', 'ё', 'ъ', '0', '×', 'ж', 'ч', 'я', 'ь', 'з', 'щ', 'э', 'р', 'ф', 'м', 'а', 'в', 'е', 'и', 'о', '°', 'Б', 'Ы', 'Т', 'У', 'Ю', 'Ш', 'Х', 'К', 'Г', 'Н', 'П', 'Л', 'С', 'Ц', 'Д', '`', '{', '(', '…', '>', '$', '→', '©', '[', '←', '№', '®', '—', '€', '¥', '&', '\'', '}', ']', '↑', '<', '§', '‰', '₽', ')', '¯', '™', 'Й', '↓', 'Ё', 'Ъ', '#', '÷', 'Ж', 'Ч', 'Я', 'Ь', 'З', 'Щ', 'Э', 'Р', 'Ф', 'М', 'А', 'В', 'Е', 'И', 'О'};
            case "UA":
                return new char[] {' ','б', 'і', 'т', 'у', 'ю', 'ш', 'х', 'к', 'г', 'н', 'п', 'л', 'с', 'ц', 'д', '\\', ';', '|', '?', ',', '5', '=', '9', '^', '+', '"', '@', '!', '6', '_', '8', '/', '7', '1', '3', '.', '2', '%', '*', ':', '~', '4', 'й', '-', 'ё', 'ї', '0', '×', 'ж', 'ч', 'я', 'ь', 'з', 'щ', 'є', 'р', 'ф', 'м', 'а', 'в', 'е', 'и', 'о', '°', 'Б', 'І', 'Т', 'У', 'Ю', 'Ш', 'Х', 'К', 'Г', 'Н', 'П', 'Л', 'С', 'Ц', 'Д', '`', '{', '(', '…', '>', '$', '→', '©', '[', '←', '№', '®', '—', '€', '¥', '&', '\'', '}', ']', '↑', '<', '§', '‰', '₴', ')', '¯', '™', 'Й', '↓', 'Ё', 'Ї', '#', '÷', 'Ж', 'Ч', 'Я', 'Ь', 'З', 'Щ', 'Є', 'Р', 'Ф', 'М', 'А', 'В', 'Е', 'И', 'О'};
            default:
                return new char[] {}; // По умолчанию пустой массив
        }
    }

    private String getXORKey(String text1, String text2) {
        StringBuilder xorKey = new StringBuilder();

        for (int i = 0; i < Math.min(text1.length(), text2.length()); i++) {
            char char1 = text1.charAt(i);
            char char2 = text2.charAt(i);

            int index1 = findIndex(selectedLanguage, char1);
            int index2 = findIndex(selectedLanguage, char2);

            if (index1 != -1 && index2 != -1) {
                int xorValue = index1 ^ index2;
                xorKey.append(selectedLanguage[xorValue]);
            }
        }

        return xorKey.toString();
    }

    private int findIndex(char[] array, char target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
