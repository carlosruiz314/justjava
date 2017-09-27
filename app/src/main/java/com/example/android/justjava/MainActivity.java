/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 */
package com.example.android.justjava;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Initialize public variables
    int quantity = 1;
    int coffeePrice = 5;
    int whippedCreamPrice = 1;
    int chocolatePrice = 2;

    /**
     * Inflate a toast message, which varies depending on the increment/decrement the user asked for
     */
    public void inflateToast(CharSequence message, int duration){
        Context context = getApplicationContext();
        Toast.makeText(context, message, duration).show();
    }

    /**
     * This method increments the quantity of coffee cups ordered
     */
    public void increment(View view) {
        if (quantity == 100){
            CharSequence message = "Maximum 100 cups!";
            inflateToast(message, Toast.LENGTH_SHORT);
        }
        quantity = Math.min(quantity + 1, 100);
        displayQuantity(quantity);
    }

    /**
     * This method decrements the quantity of coffee cups ordered
     */
    public void decrement(View view) {
        if (quantity == 1){
            CharSequence message = "Minimum 1 cup!";
            inflateToast(message, Toast.LENGTH_SHORT);
        }
        quantity = Math.max(quantity - 1, 1);
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        // Get username
        EditText userNameView = (EditText) findViewById(R.id.user_name);
        String userName = userNameView.getText().toString();

        // Has whipped cream?
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Has chocolate?
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Place order
        String orderSummary = createOrderSummary(userName, price, hasWhippedCream, hasChocolate);
        displayMessage(orderSummary);
    }

    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice() {
        int finalPrice = quantity * (coffeePrice + whippedCreamPrice + chocolatePrice);
        return finalPrice;
    }

    /**
     * Create summary of the order.
     *
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate is whether or not the user wants chocolate topping
     * @param price of the order
     * @return text summary
     */
    private String createOrderSummary(String userName, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceFormat = NumberFormat.getCurrencyInstance().format(price);
        String summary = "";
        summary += "Name: " + userName;
        summary += "\nAdd whipped cream? " + addWhippedCream;
        summary += "\nAdd chocolate? " + addChocolate;
        summary += "\nQuantity: " + quantity;
        summary += "\nTotal: " + priceFormat;
        summary += "\nThank you!";
        return summary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}