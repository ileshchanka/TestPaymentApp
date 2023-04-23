package com.example.testpaymentapp.validation

sealed interface ValidationResult {
    object Success : ValidationResult
    object Fail : ValidationResult
}
