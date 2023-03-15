package chapter04

class Constructor {
}

class User constructor(_nickname: String) {
    val nickname: String

    init {
        nickname = _nickname
    }

}

class UserDefault(val nickname: String, val isSubscribed: Boolean = true)

open class UserOpen(val nickname: String) {

}

class TwitterUser(nickname: String) : UserOpen(nickname) {

}