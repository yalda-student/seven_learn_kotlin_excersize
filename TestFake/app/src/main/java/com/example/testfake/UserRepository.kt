package com.example.testfake

data class User(val id: Int, val name: String)

interface UserRepository {
    suspend fun addUser(user: User): Boolean
    suspend fun getUserById(id: Int): User?
    suspend fun getAllUsers(): List<User>
    suspend fun deleteUser(id: Int): Boolean
}

class FakeUserRepository : UserRepository {
    private val users = mutableListOf<User>()

    override suspend fun addUser(user: User): Boolean {
        return if (users.any { it.id == user.id }) {
            false // اگر کاربری با همان شناسه وجود داشته باشد، اضافه نمی‌شود
        } else {
            users.add(user)
            true
        }
    }

    override suspend fun getUserById(id: Int): User? {
        return users.find { it.id == id }
    }

    override suspend fun getAllUsers(): List<User> {
        return users
    }

    override suspend fun deleteUser(id: Int): Boolean {
        return users.removeIf { it.id == id }
    }
}
