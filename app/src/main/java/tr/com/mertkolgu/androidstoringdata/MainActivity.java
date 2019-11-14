package tr.com.mertkolgu.androidstoringdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        // local'de veri saklamak için kullanılır
        sharedPreferences = this.getSharedPreferences("tr.com.mertkolgu.androidstoringdata", Context.MODE_PRIVATE);
        // MODE_PRIVATE ile oluşturulan veri tabanına sadece kendi uygulamamız tarafından erişebiliriz.

        int storadAge = sharedPreferences.getInt("storedAge", 0);

        if (storadAge == 0) {
            textView.setText("Your Age : ");
        } else {
            textView.setText("Your Age : " + storadAge);
        }
    }

    public void save(View view) {
        // eğer kullanıcı girdi olarak hiçbir şey vermediyse işlem yapılmaz
        if (!editText.getText().toString().matches("")) {
            int userAge = Integer.parseInt(editText.getText().toString());
            textView.setText("Your Age : " + userAge);

            // girilen veri uygulamanın içinde saklanıyor
            sharedPreferences.edit().putInt("storedAge", userAge).apply();
        }
    }

    public void delete(View view) {
        int storedData = sharedPreferences.getInt("storedAge", 0);

        if (storedData != 0) {
            sharedPreferences.edit().remove("storedAge").apply();
            textView.setText("Your Age : ");
        }
    }
}
