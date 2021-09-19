package dev.androidbroadcast.devto.user

import dev.androidbroadcast.devto.api.DevtoApi
import dev.androidbroadcast.devto.api.UserService
import dev.androidbroadcast.devto.api.result.Result
import dev.androidbroadcast.devto.api.result.map
import dev.androidbroadcast.devto.user.model.User
import javax.inject.Inject

internal class CurrentUserUseCase(private val usersService: UserService) {

    @Inject
    constructor(devtoApi: DevtoApi) : this(devtoApi.users)

    suspend operator fun invoke(): Result<User> {
        return usersService.currentUser().map { user ->
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
