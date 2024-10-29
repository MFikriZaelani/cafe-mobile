package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var foodList: List<Food>
    private lateinit var adapter: FoodOrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmation)

        val receivedFood = intent.getParcelableArrayListExtra<Food>("cart")

// Now you can use the receivedFood object:
        if (receivedFood != null) {
            foodList = receivedFood.toList()

        } else {
            // Handle the case where the data is null
            foodList = listOf()
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = FoodOrderAdapter(foodList, this)
        recyclerView.adapter = adapter

        val button = findViewById<Button>(R.id.backtoMenu)
        button.setOnClickListener({
            val intent = Intent(this, ListFoodActivity::class.java)
                startActivity(intent)
        })


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}