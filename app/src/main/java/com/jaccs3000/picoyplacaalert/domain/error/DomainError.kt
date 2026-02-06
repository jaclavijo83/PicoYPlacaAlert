package com.picoyplacaalert.domain.error

sealed class DomainError : Throwable() {

    data object Unknown : DomainError()

    data class Validation(val reason: String) : DomainError()

    data class NotFound(val reason: String) : DomainError()
}
