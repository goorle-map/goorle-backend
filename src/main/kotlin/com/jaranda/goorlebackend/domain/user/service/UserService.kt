package com.jaranda.goorlebackend.domain.user.service

import com.jaranda.goorlebackend.domain.user.dto.UserReadDTO
import com.jaranda.goorlebackend.domain.user.entity.User
import com.jaranda.goorlebackend.domain.user.error.UserAlreadyException
import com.jaranda.goorlebackend.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {
    @Transactional
    fun createUser(userId: String): UserReadDTO {
        if (userRepository.existsById(userId)) throw UserAlreadyException()
        val user = User(id = userId)
        userRepository.save(user)
        return UserReadDTO.of(user)
    }

    @Transactional
    fun findUserByIdOrNull(userId: String): UserReadDTO? = userRepository.findByIdOrNull(userId)?.let {
        return UserReadDTO.of(it)
    }
}