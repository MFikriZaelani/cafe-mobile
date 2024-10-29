package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class ListFoodActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodList: List<Food>
    private lateinit var adapter: FoodAdapter
    private lateinit var cartButton: Button

    private lateinit var cart: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_food)
        cart = listOf();


        cartButton = findViewById(R.id.cardCart) as Button
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        cartButton.text = cart.size.toString() + " Pesanan";
        // Menyiapkan data makanan
        foodList = listOf(
            Food("Batagor", "Batagor asli enak dari Bandung", R.drawable.batagor, 0),
            Food("Black Salad", "Salad segar yang dibuat secara langsung", R.drawable.black_salad, 0),
            Food("Cappucino", "Kopi cappucino asli yang dibuat dari Kopi Arabica", R.drawable.cappuchino, 0)
        )

        adapter = FoodAdapter(foodList, this)
        recyclerView.adapter = adapter

        cartButton.setOnClickListener({
            if(cart.isEmpty()){
                Toast.makeText(this, "Belum ada produk ditambahkan", Toast.LENGTH_SHORT).show()

            }else {
                val intent = Intent(this, ConfirmationActivity::class.java)
                intent.putParcelableArrayListExtra("cart", ArrayList<Food>(cart))
                startActivity(intent)
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun addFoodToCart(food: Food){
        val foundSame = cart.find  { it.name == food.name }
        if(foundSame == null){
            food.qty += 1
            cart = cart + food

        }else{
            val index = cart.indexOf(foundSame)
            cart[index].qty = foundSame.qty + 1;
        }
        var totalOrder = 0;
        for (food in cart){
            totalOrder += food.qty
        }
        cartButton.text = totalOrder.toString() + " Pesanan";

    }
}