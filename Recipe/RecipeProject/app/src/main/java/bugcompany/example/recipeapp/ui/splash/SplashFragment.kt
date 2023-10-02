package com.example.sisterslabprojectrecipes.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.sisterslabprojectrecipes.R
import com.example.sisterslabprojectrecipes.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var bottomAnimation: Animation
    private lateinit var topAnimation: Animation
    private lateinit var leftAnimation: Animation
    private lateinit var rightAnimation: Animation


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getActivity()?.getWindow()?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        bottomAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.bottom_animation)
        topAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.top_animation)
        leftAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.left_anim)
        rightAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.right_anim)

        binding.apply {
            imageViewLogo.animation = bottomAnimation
            textViewAppName.animation = leftAnimation
            textViewSlogan.animation = rightAnimation
        }


        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_recipeListFragment)

        }, 3500)

    }

}


