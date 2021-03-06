package com.example.tradelog.api.core.model

enum class ActionType {
    UNKNOWN,
    SELL,
    BUY;

    companion object {
        fun lookup(id: String) : ActionType {
            return try {
                valueOf(id)
            } catch (ex: IllegalArgumentException) {
                UNKNOWN
            }
        }
    }
}
