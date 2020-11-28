package com.eticon.rosseti.livedata

import androidx.lifecycle.MutableLiveData
import com.eticon.rosseti.dataClasses.UserApplication
import com.eticon.rosseti.dataClasses.UserData

var userProfile = MutableLiveData<UserData>()

var applicationUserLiveData = MutableLiveData<MutableList<UserApplication>>()