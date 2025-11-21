package com.jmperezra.bluey.core.domain

sealed class ErrorApp : Throwable() {
    object UnknownError : ErrorApp() {
        private fun readResolve(): Any = UnknownError
    }

    object CacheError : ErrorApp() {
        private fun readResolve(): Any = CacheError
    }

    object InternetError : ErrorApp() {
        private fun readResolve(): Any = InternetError
    }

    object ServerError : ErrorApp() {
        private fun readResolve(): Any = ServerError
    }
}

