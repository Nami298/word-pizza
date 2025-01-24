package maedeh.nami.worldpizza

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class LevelsAdapter(
    private val levels: List<LevelsModel>,
    private val onLevelClick: (LevelsModel) -> Unit
) : RecyclerView.Adapter<LevelsAdapter.LevelViewHolder>() {

    class LevelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val levelText: TextView = view.findViewById(R.id.levelText)
        val lockIcon: ImageView = view.findViewById(R.id.lockLevel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.level_item, parent, false)
        return LevelViewHolder(view)
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        val level = levels[position]

        // If the level is unlocked, display the level ID in levelText and hide lockIcon
        if (!level.isLocked) {
            holder.levelText.text = "${level.id}" // Display level ID
            holder.levelText.visibility = View.VISIBLE // Make sure levelText is visible
            holder.lockIcon.visibility = View.GONE // Hide the lock icon
        } else {
            // If the level is locked, show lockIcon and hide levelText
            holder.levelText.visibility = View.GONE // Hide levelText
            holder.lockIcon.visibility = View.VISIBLE // Show lock icon
        }

        holder.itemView.setOnClickListener {
            if (!level.isLocked) {
                onLevelClick(level)
            }
        }
    }

    override fun getItemCount(): Int = levels.size
}
