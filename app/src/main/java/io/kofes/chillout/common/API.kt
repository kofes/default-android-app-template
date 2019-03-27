package io.kofes.chillout.common

import com.google.gson.*
import com.google.gson.annotations.SerializedName
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.lang.reflect.Type


object Router {
    private val customDeserializers = GsonBuilder()
        .registerTypeAdapter(API.CustomResponse::class.java, CustomDeserializer())
        .create()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(API.HOST + API.BASE_PATH)
        .client(OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build())
        .addConverterFactory(GsonConverterFactory.create(customDeserializers))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

interface API {
    companion object {
        const val HOST = "http://custom_host.domain"
        const val BASE_PATH = "/base/path/"
        const val BASE_METHOD = "/base_method.php"
    }

    @POST(BASE_METHOD)
    @FormUrlEncoded
    fun baseRequest(
        @Field("action") action: String
    ): Single<BaseResponse>

    @POST(BASE_METHOD)
    @FormUrlEncoded
    fun customRequest(
        @Field("action") action: String = "get_custom"
    ): Single<CustomResponse>

    open class ErrorResponse {
        @SerializedName("status") var status: String? = null
        @SerializedName("error") var error: BaseError? = null
    }

    open class BaseError {
        @SerializedName("code") var code: Int? = null
        @SerializedName("message") var message: String? = null
    }

    open class BaseResponse: ErrorResponse() {
        @SerializedName("data") var data: BaseData? = null
    }

    open class BaseData {
        @SerializedName("description") var description: String? = null
    }

    open class CustomResponse: ErrorResponse() {
        @SerializedName("custom_field") var customField: String? = null
    }
}

class CustomDeserializer: JsonDeserializer<API.CustomResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): API.CustomResponse {
        val jsonObject = json?.asJsonObject
        val response = API.CustomResponse()

        // status
        response.status = jsonObject?.get("status")?.asString

        // error
        response.error = Gson().fromJson(jsonObject?.getAsJsonObject("error")?.asJsonObject, API.BaseError::class.java)

        // custom_field
        response.customField = jsonObject?.getAsJsonObject("custom_field")?.asString

        return response
    }
}