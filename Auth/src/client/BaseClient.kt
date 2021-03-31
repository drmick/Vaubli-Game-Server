package client

import models.AuthUser

class BaseClient(val executor: String) {
    fun login(address: String): AuthUser {
        return AuthUser(nickname = "Reptiloid $address")
    }
}