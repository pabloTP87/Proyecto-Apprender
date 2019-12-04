package com.example.apprender.logica

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.apprender.R
import kotlinx.android.synthetic.main.registry_confirm_dialog.*

class CustomDialog private constructor(
    private val positiveButtonText: String?,
    private val titulo: String?,
    private val descripcion: String?,
    private val img: Int?,
    private var dialogClickListener: DialogButtonClickListener? = null)
    : DialogFragment() {

    interface DialogButtonClickListener{
        fun onPositiveButtonClick()
    }

    data class Builder(private var positiveButtonText: String? = null,
                       private var titulo: String? = null,
                       private var descripcion: String? = null,
                       private var img: Int? = null) {

        fun setPositiveButtonText(positiveButtonText: String) = apply { this.positiveButtonText = positiveButtonText }
        fun setTitulo(titulo: String) = apply { this.titulo = titulo }
        fun setDescripcion(descripcion: String) = apply { this.descripcion = descripcion }
        fun setImagen(img: Int) = apply { this.img = img }

        fun build() = CustomDialog(positiveButtonText, titulo, descripcion, img)
    }

    fun setDialogButtonClickListener(listener: DialogButtonClickListener) = apply { dialogClickListener = listener }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.registry_confirm_dialog,container)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnStartMain.text = positiveButtonText
        btnStartMain.setOnClickListener { dialogClickListener!!.onPositiveButtonClick()}
        titulo_dialog.text = titulo
        descripcion_dialog.text = descripcion
        img_dialog.setImageResource(img!!)
    }
}