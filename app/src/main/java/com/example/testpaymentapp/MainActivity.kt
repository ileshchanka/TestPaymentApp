package com.example.testpaymentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testpaymentapp.databinding.ActivityMainBinding
import com.example.testpaymentapp.di.AppComponent
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    @Inject
    lateinit var viewModel: MainViewModel

    init {
        AppComponent.get().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {

            etEmail.apply {
                addTextChangedListener {
                    viewModel.emailTextChanged(this.text.toString())
                }

                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus.not()) {
                        viewModel.emailFocusChanged(this.text.toString())
                    }
                }
            }

            etNumber.apply {
                addTextChangedListener {
                    viewModel.numberTextChanged(this.text.toString())
                }
                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus.not()) {
                        viewModel.numberFocusChanged(this.text.toString())
                    }
                }
            }

            etDate.apply {
                addTextChangedListener {
                    viewModel.dateTextChanged(this.text.toString())
                }
                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus.not()) {
                        viewModel.dateFocusChanged(this.text.toString())
                    }
                }
            }

            etCvc.apply {
                addTextChangedListener {
                    viewModel.cvcTextChanged(this.text.toString())
                }
                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus.not()) {
                        viewModel.cvcFocusChanged(this.text.toString())
                    }
                }
            }

            buttonPay.setOnClickListener {
                MaterialAlertDialogBuilder(this@MainActivity).apply {
                    setTitle(resources.getString(R.string.alert_title))
                    setMessage(resources.getString(R.string.alert_message))
                    setPositiveButton(resources.getString(R.string.alert_positive_button)) { dialog, _ ->
                        dialog.dismiss()
                    }
                }.show()
            }
        }

        viewModel.viewState.observe(this) { viewState ->
            with(binding) {
                etEmail.setTextColor(viewState.emailColor)
                etNumber.setTextColor(viewState.numberColor)
                etDate.setTextColor(viewState.dateColor)
                etCvc.setTextColor(viewState.cvcColor)
                buttonPay.isEnabled = viewState.isPayButtonEnabled
            }
        }
    }
}
