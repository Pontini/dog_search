package pontinisystems.network

sealed class CustomException : Exception() {

    class ResponseBodyError(private val body: ErrorResponse?) : CustomException() {
        override val message: String?
            get() = body!!.status_message
    }


    class SessionExpired : CustomException() {
        override val message: String?
            get() = "Session expired"
    }

    class TimeOutException() : CustomException() {
        override val message: String?
            get() = "Time Out"
    }

    class Network() : CustomException() {
        override val message: String?
            get() = "Problems with the connection"
    }

    class Unknown(private val e: Exception) : CustomException() {

        override val message: String?
            get() = "Unknown error"
    }
}