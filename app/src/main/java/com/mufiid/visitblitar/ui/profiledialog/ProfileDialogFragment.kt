package com.mufiid.visitblitar.ui.profiledialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.mufiid.visitblitar.R
import com.mufiid.visitblitar.databinding.FragmentProfileDialogBinding
import com.mufiid.visitblitar.ui.home.HomeFragment
import com.mufiid.visitblitar.utils.pref.AuthPref

class ProfileDialogFragment : DialogFragment(), View.OnClickListener {
    private lateinit var binding: FragmentProfileDialogBinding
    private var titleProfileDialog: String? = ""
    private var buttonListener: ButtonListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogout.setOnClickListener(this)
        binding.buttonLogin.setOnClickListener(this)
        binding.titleDialog.text = getString(R.string.hay_username, "Kawan")

        if (context?.let { AuthPref.isLoggedIn(it) } == false) {
            binding.buttonLogout.visibility = View.GONE
            binding.buttonLogin.visibility = View.VISIBLE
        } else {
            binding.buttonLogout.visibility = View.VISIBLE
            binding.buttonLogin.visibility = View.GONE
        }

    }

    override fun onResume() {
        super.onResume()
        val params = dialog?.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            titleProfileDialog = savedInstanceState.getString(TITLE_DIALOG)
        }

        if (arguments != null) {
            titleProfileDialog = arguments?.getString(TITLE_DIALOG)

            binding.titleDialog.text = getString(R.string.hay_username, titleProfileDialog)
        }
    }

    companion object {
        var TITLE_DIALOG = "title_dialog"
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_logout -> {
                if (buttonListener != null) {
                    buttonListener?.logout()
                }
                dialog?.dismiss()
            }
            R.id.button_login -> {
                if (buttonListener != null) {
                    buttonListener?.login()
                }
                dialog?.dismiss()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment

        if (fragment is HomeFragment) {
            this.buttonListener = fragment.buttonListener
        }
    }

    interface ButtonListener {
        fun logout()
        fun login()
    }
}