package com.tanitama.green.presentation.customviews

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import com.google.android.material.button.MaterialButton
import com.tanitama.green.R


class CustomRegisterEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfPassword: EditText
    private lateinit var btnRegister: MaterialButton

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confPassword = etConfPassword.text.toString().trim()

            if (s.hashCode() == etEmail.text.hashCode() && !TextUtils.isEmpty(email) && !email.isValidEmail()) {
                etEmail.error = "Please enter a valid email address"
            } else {
                etEmail.error = null
            }

            if (s.hashCode() == etPassword.text.hashCode() && password.isNotEmpty() && password.length < 8) {
                etPassword.error = "Password must be at least 8 characters"
            } else {
                etPassword.error = null
            }

            if (s.hashCode() == etConfPassword.text.hashCode() && confPassword != password) {
                etConfPassword.error = "Confirmation password does not match"
            } else {
                etConfPassword.error = null
            }

            btnRegister.isEnabled = validateForm(username, email, password, confPassword)
        }


        override fun afterTextChanged(s: Editable?) {
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_register_edit_text, this, true)
        etUsername = findViewById(R.id.etUsername)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfPassword = findViewById(R.id.etConfPassword)
        btnRegister = findViewById(R.id.btnRegister)

        etUsername.addTextChangedListener(textWatcher)
        etEmail.addTextChangedListener(textWatcher)
        etPassword.addTextChangedListener(textWatcher)
        etConfPassword.addTextChangedListener(textWatcher)

        btnRegister.isEnabled = true
        btnRegister.setOnClickListener {
            clearFocus()
            val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(windowToken, 0)
            rootView.requestFocus()
        }
    }

    fun validateForm(name: String, email: String, password: String, confPassword: String): Boolean {
        return name.isNotEmpty() && email.isValidEmail() && password.length >= 8 && password == confPassword
    }

    private fun String.isValidEmail(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
            .matches()
    }

    fun getUsername(): String {
        return etUsername.text.toString().trim()
    }

    fun getEmail(): String {
        return etEmail.text.toString().trim()
    }

    fun getPassword(): String {
        return etPassword.text.toString().trim()
    }

    fun getConfPassword(): String {
        return etConfPassword.text.toString().trim()
    }

    fun setOnRegisterClickListener(listener: OnClickListener) {
        btnRegister.setOnClickListener(listener)
    }
}