package com.example.testfake

import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UserRepositoryTest {

    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userRepository = FakeUserRepository()
    }

    @Test
    fun `addUser should add a user successfully`() = runTest {
        val user = User(1, "John Doe")
        val result = userRepository.addUser(user)
        assertTrue(result)
    }

    @Test
    fun `addUser should not add a user with duplicate id`() = runTest {
        val user1 = User(1, "John Doe")
        val user2 = User(1, "Jane Doe")
        userRepository.addUser(user1)
        val result = userRepository.addUser(user2)
        assertFalse(result)
    }

    @Test
    fun `getUserById should return correct user`() = runTest {
        val user = User(1, "John Doe")
        userRepository.addUser(user)
        val result = userRepository.getUserById(1)
        assertNotNull(result)
        assertEquals("John Doe", result?.name)
    }

    @Test
    fun `getUserById should return null for non-existent user`() = runTest {
        val result = userRepository.getUserById(999)
        assertNull(result)
    }

    @Test
    fun `getAllUsers should return all added users`() = runTest {
        val user1 = User(1, "John Doe")
        val user2 = User(2, "Jane Doe")
        userRepository.addUser(user1)
        userRepository.addUser(user2)
        val users = userRepository.getAllUsers()
        assertEquals(2, users.size)
        assertTrue(users.contains(user1))
        assertTrue(users.contains(user2))
    }

    @Test
    fun `deleteUser should remove the user`() = runTest {
        val user = User(1, "John Doe")
        userRepository.addUser(user)
        val result = userRepository.deleteUser(1)
        assertTrue(result)
        assertNull(userRepository.getUserById(1))
    }

    @Test
    fun `deleteUser should return false for non-existent user`() = runTest {
        val result = userRepository.deleteUser(999)
        assertFalse(result)
    }
}
