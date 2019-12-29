package com.apprenderchile.apprender.logica

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.apprenderchile.apprender.R
import kotlinx.android.synthetic.main.custom_dialog.*

class CustomDialog

private constructor(
    private val positiveButtonText: String?,
    private val cancelButtonText: String?,
    private val continueButtonText: String?,
    private val titulo: String?,
    private val descripcion: String?,
    private val img: Int?,
    private val continueButtonVisible: Boolean?,
    private var dialogClickListener: DialogButtonClickListener? = null) : DialogFragment() {

    interface DialogButtonClickListener{
        fun onPositiveButtonClick()
        fun onCancelButtonClick()
        fun onContinueButtonClick()
    }

    data class Builder(private var positiveButtonText: String? = null,
                       private var cancelButtonText: String? = null,
                       private var continueButtonText: String? = null,
                       private var continueButtonVisible: Boolean? = false,
                       private var titulo: String? = null,
                       private var descripcion: String? = null,
                       private var img: Int? = null) {

        fun setPositiveButtonText(positiveButtonText: String) = apply { this.positiveButtonText = positiveButtonText }
        fun setCancelButtonText(cancelButtonText: String) = apply { this.cancelButtonText = cancelButtonText }
        fun setContinueButtonText(continueButtonText: String) = apply { this.continueButtonText = continueButtonText }
        fun setContinueButtonVisible(continueButtonVisible: Boolean) = apply { this.continueButtonVisible = continueButtonVisible }
        fun setTitulo(titulo: String) = apply { this.titulo = titulo }
        fun setDescripcion(descripcion: String) = apply { this.descripcion = descripcion }
        fun setImagen(img: Int) = apply { this.img = img }

        fun build() = CustomDialog(positiveButtonText,cancelButtonText,continueButtonText, titulo, descripcion, img,continueButtonVisible)
    }

    fun setDialogButtonClickListener(listener: DialogButtonClickListener) = apply { dialogClickListener = listener }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.custom_dialog,container)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnStartMain.text = continueButtonText
        btn_no.text = cancelButtonText
        btn_si.text = positiveButtonText
        btnStartMain.setOnClickListener { dialogClickListener!!.onContinueButtonClick()}
        btn_no.setOnClickListener { dialogClickListener!!.onCancelButtonClick() }
        btn_si.setOnClickListener { dialogClickListener!!.onPositiveButtonClick() }
        titulo_dialog.text = titulo
        descripcion_dialog.text = descripcion
        img_dialog.setImageResource(img!!)

        if (continueButtonVisible!!){
            btn_si.visibility = View.GONE
            btn_no.visibility = View.GONE
        }else{
            btnStartMain.visibility = View.GONE
        }
    }
}