package com.example.toof.egetdatarepogithub

import android.graphics.Bitmap

data class Owner(val login: String?, val avatar: String?)

data class User(val full_name: String?, val owner: Owner?)