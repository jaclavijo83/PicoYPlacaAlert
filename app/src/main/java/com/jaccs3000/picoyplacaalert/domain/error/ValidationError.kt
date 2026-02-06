package com.picoyplacaalert.domain.error

sealed class ValidationError(message: String) : IllegalArgumentException(message) {

    data object InvalidPlateFormat :
        ValidationError("Formato de placa inválido")

    data object InvalidTimeWindow :
        ValidationError("La franja horaria es inválida")

    data object EmptyRules :
        ValidationError("Debe existir al menos una regla de pico y placa")
}
