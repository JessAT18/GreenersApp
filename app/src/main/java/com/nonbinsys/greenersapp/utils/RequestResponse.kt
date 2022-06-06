package com.nonbinsys.greenersapp.utils

import com.nonbinsys.greenersapp.data.pojo.ComercioList
import com.nonbinsys.greenersapp.data.pojo.Self

data class RequestResponse<T>(
    val _embedded: T,
    val _links: Self
)
