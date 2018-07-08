package com.marcosvbras.robomarket.utils

import android.text.Editable
import android.text.TextWatcher
import io.reactivex.annotations.NonNull
import android.widget.EditText

class MaskWatcher(@NonNull val mask: String, val editText: EditText) : TextWatcher {

    var isUpdating: Boolean = false
    var old = ""
    val maskLength = mask.length

    override fun afterTextChanged(editable: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val str = unmask(s.toString())
        var mascara = ""
        if (isUpdating) {
            old = str
            isUpdating = false
            return
        }

        var i = 0

        for (m in mask.toCharArray()) {
            if (m != '#' && str.length > old.length) {
                mascara += m
                continue
            }
            try {
                mascara += str[i]
            } catch (e: Exception) {
                break
            }

            i++
        }

        isUpdating = true
        editText.setText(mascara)
        editText.setSelection(mascara.length)
    }

    companion object {
        const val cpf = "###.###.###-##"
        const val cep = "##.###-###"
        const val nineDigitPhone = "(##) # ####-####"
        const val eightDigitPhone = "(##) ####-####"
        const val cnpj = "##.###.###/####-##"
        const val rg = "########"

        fun buildCpf(editText: EditText) = MaskWatcher(cpf, editText)
        fun buildCep(editText: EditText) = MaskWatcher(cep, editText)
        fun build9DigitPhone(editText: EditText) = MaskWatcher(nineDigitPhone, editText)
        fun build8DigitPhone(editText: EditText) = MaskWatcher(eightDigitPhone, editText)
        fun buildCnpj(editText: EditText) = MaskWatcher(cnpj, editText)
        fun buildRg(editText: EditText) = MaskWatcher(rg, editText)

        fun unmask(s: String): String {
            return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "").replace("[/]".toRegex(), "").replace("[(]".toRegex(), "").replace("[ ]".toRegex(), "").replace("[:]".toRegex(), "").replace("[)]".toRegex(), "")
        }
    }
}