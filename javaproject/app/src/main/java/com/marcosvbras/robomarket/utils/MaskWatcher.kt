package com.marcosvbras.robomarket.utils

import android.text.Editable
import android.text.TextWatcher
import io.reactivex.annotations.NonNull

class MaskWatcher(@NonNull val mask: String) : TextWatcher {

    private var isRunning = false
    private var isDeleting = false
    val maskLength = mask.length

    override fun afterTextChanged(editable: Editable?) {
        if(isRunning || isDeleting)
            return

        isRunning = true

        val editableLength = editable?.length

        if(editableLength!! < maskLength) {
            if (mask[editableLength] != '#')
                editable.append(mask[editableLength])
            else if(mask[editableLength - 1] != '#')
                editable.insert(editableLength - 1, mask, editableLength - 1, editableLength)
        }

        isRunning = false
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        isDeleting = count > after
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    companion object {
        fun buildCpf() = MaskWatcher("###.###.###-##")
        fun buildCep() = MaskWatcher("##.###-###")
        fun build9DigitPhone() = MaskWatcher("(##) # ####-####")
        fun build8DigitPhone() = MaskWatcher("(##) ####-####")
        fun buildCnpj() = MaskWatcher("##.###.###/####-##")
        fun buildRg() = MaskWatcher("########")
    }
}