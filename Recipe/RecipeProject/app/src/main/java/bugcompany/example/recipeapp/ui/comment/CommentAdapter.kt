package bugcompany.example.recipeapp.ui.comment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sisterslabprojectrecipes.databinding.CommentCardBinding


class CommentAdapter(private val mContext: Context, private val commentList: List<CommentModel>) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    class CommentViewHolder(val binding: CommentCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = CommentCardBinding.inflate(layoutInflater)
        return CommentViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = commentList[position]
        holder.binding.textViewEmail.text = comment.email
        holder.binding.textViewComment.text = comment.shareComment
    }


}