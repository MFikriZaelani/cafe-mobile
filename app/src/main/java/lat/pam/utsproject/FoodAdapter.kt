package lat.pam.utsproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class FoodAdapter(private val foodList: List<Food>, val activity: ListFoodActivity ) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>()  {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.foodName.text = food.name
        holder.foodDescription.text = food.description
        holder.foodImage.setImageResource(food.imageResourceId)
        holder.buttonOrder.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                activity.addFoodToCart(food)
                Toast.makeText(view?.context, "Item ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()

            }
        })
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.foodImage)
        val foodName: TextView = itemView.findViewById(R.id.foodName)
        val foodDescription: TextView = itemView.findViewById(R.id.foodDescription)
        val buttonOrder: Button = itemView.findViewById(R.id.buttonOrder)
    }
}