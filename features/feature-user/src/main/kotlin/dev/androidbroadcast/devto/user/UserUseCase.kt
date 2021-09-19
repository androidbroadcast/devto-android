package dev.androidbroadcast.devto.user

import dev.androidbroadcast.devto.api.UserService
import dev.androidbroadcast.devto.api.result.Result
import dev.androidbroadcast.devto.api.result.map
import dev.androidbroadcast.devto.user.model.User
import javax.inject.Inject

internal class UserUseCase @Inject constructor(private val userService: UserService) {

    suspend operator fun invoke(userId: Int): Result<User> {
        return userService.userById(userId).map { user ->
            with(user) {
                User(
                    id,
                    username,
                    name,
                    summary,
                    twitterUsername,
                    githubUsername,
                    websiteUrl,
                    location,
                    joinedAt,
                    profileImageUrl
                )
            }
        }
    }
}
