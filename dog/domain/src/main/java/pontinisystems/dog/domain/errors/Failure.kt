package pontinisystems.dog.domain.errors

sealed class Failure:Exception(){
    open class NetworkFailure(val code:Int?=0, override val message:String?=""): Failure()
    open class InputInvalid(override val message:String?=""): Failure()
    open class Unknown(override val message:String?=""): Failure()
}