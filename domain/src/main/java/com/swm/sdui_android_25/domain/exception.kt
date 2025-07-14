package com.swm.sdui_android_25.domain

sealed class SDUIException : Exception() {
    object NetworkError : SDUIException()
    object ParseError : SDUIException()
    object ComponentNotFound : SDUIException()
    data class ActionError(val actionType: String) : SDUIException()
}