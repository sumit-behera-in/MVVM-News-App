package apps.sumit.apitest.common

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val flag: Boolean? = null,
) {
    class Success<T>(data: T, flag: Boolean) : Resource<T>(data, flag = flag)
    class Error<T>(message: String) : Resource<T>(message = message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}