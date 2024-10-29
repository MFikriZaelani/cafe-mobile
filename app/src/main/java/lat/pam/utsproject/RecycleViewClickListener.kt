package lat.pam.utsproject

import android.view.View

interface RecycleViewClickListener {
    fun onItemClicked(view: View, food: Food);
}