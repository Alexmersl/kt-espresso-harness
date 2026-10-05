package dev.aleksejgolikov.espresso.demo.ui

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DemoRowAdapter(
    private val rows: List<String>,
) : RecyclerView.Adapter<DemoRowAdapter.Holder>() {
    class Holder(val label: TextView) : RecyclerView.ViewHolder(label)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): Holder {
        val label =
            TextView(parent.context).apply {
                layoutParams =
                    RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                    )
                setPadding(32, 36, 32, 36)
                textSize = 16f
            }
        return Holder(label)
    }

    override fun getItemCount(): Int = rows.size

    override fun onBindViewHolder(
        holder: Holder,
        position: Int,
    ) {
        holder.label.text = rows[position]
    }
}
