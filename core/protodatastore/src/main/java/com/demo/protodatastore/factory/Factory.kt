package com.demo.protodatastore.factory

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.demo.proto.Preferences
import com.demo.proto.Session
import com.demo.protodatastore.serializer.PrefsSerializer
import com.demo.protodatastore.serializer.SessionSerializer

val Context.sessionDataStore: DataStore<Session> by dataStore(
  fileName = "session.pb",
  serializer = SessionSerializer,
)

val Context.prefsDataStore: DataStore<Preferences> by dataStore(
  fileName = "preferences.pb",
  serializer = PrefsSerializer,
)
