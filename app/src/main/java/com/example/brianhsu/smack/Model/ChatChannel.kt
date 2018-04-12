package com.example.brianhsu.smack.Model

/**
 * Created by brian on 2018/4/12.
 */
class ChatChannel(val name: String, val description: String, val id: String) {
    override fun toString(): String {
        return "#$name"
    }
}