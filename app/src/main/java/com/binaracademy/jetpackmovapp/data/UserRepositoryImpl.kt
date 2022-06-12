package com.binaracademy.jetpackmovapp.data

import androidx.lifecycle.LiveData
import com.binaracademy.jetpackmovapp.data.source.local.UserLocalDataSource
import com.binaracademy.jetpackmovapp.data.source.local.entity.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

interface UserRepository {
    fun getUser(email: String): LiveData<UserEntity?>
    fun login(email: String, password: String): LiveData<UserEntity?>
    suspend fun insertUser(userEntity: UserEntity)
    suspend fun updateUser(userEntity: UserEntity)
    suspend fun deleteAllUser()
    suspend fun setIsAuthorized(userEntity: UserEntity, isAuthorizedState: Boolean)
}

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {

    override fun getUser(email: String): LiveData<UserEntity?> =
        userLocalDataSource.getUser(email)

    override fun login(email: String, password: String): LiveData<UserEntity?> =
        userLocalDataSource.login(email, password)

    override suspend fun insertUser(userEntity: UserEntity) =
        userLocalDataSource.insertUser(userEntity)

    override suspend fun updateUser(userEntity: UserEntity) =
        userLocalDataSource.updateUser(userEntity)

    override suspend fun deleteAllUser() =
        userLocalDataSource.deleteAllUser()

    override suspend fun setIsAuthorized(userEntity: UserEntity, isAuthorizedState: Boolean) {
        userEntity.isAuthorized = isAuthorizedState
        userLocalDataSource.updateUser(userEntity)
    }
}