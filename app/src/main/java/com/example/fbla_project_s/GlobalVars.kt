package com.example.fbla_project_s

import android.app.Application

class GlobalVars: Application() {
    companion object {
        var username: String = ""
        var email: String = ""
        var password: String = ""
    }
}