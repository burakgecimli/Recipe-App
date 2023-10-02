package com.example.sisterslabprojectrecipes.comment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import bugcompany.example.recipeapp.ui.comment.CommentAdapter
import bugcompany.example.recipeapp.ui.comment.CommentModel
import com.example.sisterslabprojectrecipes.R
import com.example.sisterslabprojectrecipes.databinding.FragmentCommentBinding
import com.example.sisterslabprojectrecipes.databinding.FragmentSignInUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class CommentFragment : Fragment() {
    private var _binding: FragmentCommentBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    private lateinit var commentList: ArrayList<CommentModel>
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCommentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        commentList = ArrayList()

        binding.exit.setOnClickListener {
            auth.signOut()
            findNavController().navigate(R.id.action_commentFragment_to_signInUpFragment)
        }

        binding.floatActionBarCommentAdd.setOnClickListener {
            findNavController().navigate(R.id.action_commentFragment_to_shareCommentFragment)
        }


        //Verileri almak
        getData()

        val commentModel = CommentModel("dsd", "dsdsd")
        val commentModel1 = CommentModel("dsd", "dsdsd")
        val commentModel2 = CommentModel("dsd", "dsdsd")

        commentList.add(commentModel)
        commentList.add(commentModel1)
        commentList.add(commentModel2)

        commentAdapter = CommentAdapter(requireContext(), commentList)
        binding.rvComment.adapter = commentAdapter
        binding.rvComment.layoutManager = LinearLayoutManager(requireContext())
        binding.rvComment.setHasFixedSize(true)

    }

    private fun getData() {
        db.collection("Comments").addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_SHORT).show()
            } else {
                if (value != null) {
                    if (!value.isEmpty) {
                        val documents = value.documents
                        commentList.clear()
                        for (document in documents) {
                            val email = document.get("email") as? String
                            val shareComment = document.get("shareComment") as? String
                            println(email)
                            println(shareComment)

                            val getData = CommentModel(email, shareComment)
                            commentList.add(getData)
                        }
                        commentAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }


}