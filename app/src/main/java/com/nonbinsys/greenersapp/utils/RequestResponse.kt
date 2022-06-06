package com.nonbinsys.greenersapp.utils

data class RequestResponse<T>(
    val _embedded: T,
    val _links: Self
)
