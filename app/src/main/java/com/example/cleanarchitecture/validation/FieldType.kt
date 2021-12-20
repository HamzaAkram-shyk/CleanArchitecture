package com.example.cleanarchitecture.validation

import android.widget.EditText

sealed class FieldType {
    class BasicField(val field: EditText) : FieldType()
    class PasswordField(val field: EditText) : FieldType()
    class EmailField(val field: EditText) : FieldType()
}