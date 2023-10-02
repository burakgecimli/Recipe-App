package bugcompany.example.recipeapp.ui.comment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sisterslabprojectrecipes.R
import com.example.sisterslabprojectrecipes.databinding.FragmentCommentBinding
import com.example.sisterslabprojectrecipes.databinding.FragmentShareCommentBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ShareCommentFragment : Fragment() {
    private var _binding: FragmentShareCommentBinding? = null
    private val binding get() = _binding!!
    private val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    private lateinit var shareComment: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShareCommentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.buttonShare.setOnClickListener {
            shareComment = binding.editTextComment.text.toString()
            email = auth.currentUser!!.email.toString()
            val time = Timestamp.now()

            val commentsMap = hashMapOf<String, Any>()
            commentsMap.put("shareComment", shareComment)
            commentsMap.put("email", email)
            commentsMap.put("time", time)


            db.collection("Comments").add(commentsMap).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_shareCommentFragment_to_commentFragment)
                }
            }.addOnFailureListener {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }

    }


}