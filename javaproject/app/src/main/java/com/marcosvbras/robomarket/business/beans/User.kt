package com.marcosvbras.robomarket.business.beans

data class User(
        var objectId: String? = null,
        var name: String? = null,
        var email: String? = null,
        var username: String? = null,
        var password: String? = null,
        var emailVerified: Boolean? = null,
        var sessionToken: String? = null,
        var avatarUrl: String? = null,
        var phone: String? = null,
        var genre: String? = null,
        var address: String? = null
)