package com.mufiid.visitblitar.data.source.remote

class ApiResponse<T>(val status: StatusResponse, val body: T?,val message: String?) {
    companion object {
        fun <T> success(body: T?): ApiResponse<T> = ApiResponse(StatusResponse.SUCCESS, body, null)
        fun <T> empty(message: String?): ApiResponse<T> = ApiResponse(StatusResponse.EMPTY, null, message)
        fun <T> error(message: String?): ApiResponse<T> = ApiResponse(StatusResponse.ERROR, null, message)
        fun <T> failed(message: String?): ApiResponse<T> = ApiResponse(StatusResponse.FAILED, null, message)
    }
}