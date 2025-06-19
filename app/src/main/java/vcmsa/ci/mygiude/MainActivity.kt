package vcmsa.ci.mygiude

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    // Parallel arrays
    val itemNames = arrayListOf<String>()
    val categories = arrayListOf<String>()
    val quantities = arrayListOf<Int>()
    val comments = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemNameInput = findViewById<EditText>(R.id.rateInput)
        val categoryInput = findViewById<EditText>(R.id.songInput)
        val quantityInput = findViewById<EditText>(R.id.artistInput)
        val commentsInput = findViewById<EditText>(R.id.commentsInput)
        val addButton = findViewById<Button>(R.id.addbutton)
        val viewButton = findViewById<Button>(R.id.nextButton)
        val exitButton = findViewById<Button>(R.id.exitbutton2)

        addButton.setOnClickListener {
            // Add the input to the parallel arrays
            val name = itemNameInput.text.toString().trim()
            val category = categoryInput.text.toString().trim()
            val quantityStr = quantityInput.text.toString().trim()
            val comment = commentsInput.text.toString().trim()

            // Validate fields
            if (name.isEmpty() || category.isEmpty() || quantityStr.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quantity = quantityStr.toIntOrNull()
            if (quantity == null) {
                Toast.makeText(this, "Quantity must be a number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Add validated data to lists
            itemNames.add(name)
            categories.add(category)
            quantities.add(quantity)
            comments.add(comment)

            Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show()

            // Clear inputs
            itemNameInput.text.clear()
            categoryInput.text.clear()
            quantityInput.text.clear()
            commentsInput.text.clear()
        }


        viewButton.setOnClickListener {
            // Navigate to ViewActivity and send arrays
            val intent = Intent(this, DetailedViewActivity::class.java)
            intent.putStringArrayListExtra("itemNames", itemNames)
            intent.putStringArrayListExtra("categories", categories)
            intent.putIntegerArrayListExtra("quantities", ArrayList(quantities))
            intent.putStringArrayListExtra("comments", comments)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finishAffinity()
        }
    }
}
