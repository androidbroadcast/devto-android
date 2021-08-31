package dev.androidbroadcast.devto.api.result.retrofit

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import dev.androidbroadcast.devto.api.result.Result

class ResultAdapterFactory : CallAdapter.Factory() {

    @Suppress("ReturnCount")
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val rawReturnType: Class<*> = getRawType(returnType)
        if (rawReturnType == Call::class.java && returnType is ParameterizedType) {
            val callInnerType: Type = getParameterUpperBound(0, returnType)
            if (getRawType(callInnerType) == Result::class.java) {
                // resultType is Call<Result<*>> | callInnerType is Result<*>
                if (callInnerType is ParameterizedType) {
                    val resultInnerType = getParameterUpperBound(0, callInnerType)
                    return ResultCallAdapter<Any?>(resultInnerType)
                }
                return ResultCallAdapter<Nothing>(Nothing::class.java)
            }
        }

        return null
    }
}

private class ResultCallAdapter<R>(private val type: Type) : CallAdapter<R, Call<Result<R>>> {

    override fun responseType() = type

    override fun adapt(call: Call<R>): Call<Result<R>> = ResultCall(call)
}
