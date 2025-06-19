package vcmsa.ci.mygiude

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import vcmsa.ci.mygiude.R.id.displaytext

class DetailedViewActivity : AppCompatActivity() { //second screen

    lateinit var itemNames: ArrayList<String>
    lateinit var categories: ArrayList<String>
    lateinit var quantities: ArrayList<Int>
    lateinit var comments: ArrayList<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val displayText = findViewById<TextView>(displaytext)
        val showAllButton = findViewById<Button>(R.id.displayButton)
        val filterButton = findViewById<Button>(R.id.averageButton)
        val backButton = findViewById<Button>(R.id.backbutton)

        // Get the passed ArrayLists
        itemNames = intent.getStringArrayListExtra("itemNames") ?: arrayListOf()
        categories = intent.getStringArrayListExtra("categories") ?: arrayListOf()
        quantities = intent.getIntegerArrayListExtra("quantities") ?: arrayListOf()
        comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()

        showAllButton.setOnClickListener {
            var listText = "Full Packing List:\n"
            for (i in itemNames.indices) {
                listText += "Item: ${itemNames[i]}, Category: ${categories[i]}, Quantity: ${quantities[i]}, Comments: ${comments[i]}\n"
            }
            displayText.text = listText
        }

        filterButton.setOnClickListener {
            // Call the filtering function
            val filteredList = filterHighQuantityItems(itemNames, quantities)
            displayText.text = filteredList
        }

        backButton.setOnClickListener {
            finish() // Return to MainActivity
        }
    }

    // Function to filter items with quantity >= 2
    fun filterHighQuantityItems(itemNames: ArrayList<String>, quantities: ArrayList<Int>): String {
        var result = "Items with quantity >= 2:\n"
        for (i in itemNames.indices) {
            if (quantities[i] >= 2) {
                result += "${itemNames[i]} (Quantity: ${quantities[i]})\n"
            }
        }
        return result
    }
}

