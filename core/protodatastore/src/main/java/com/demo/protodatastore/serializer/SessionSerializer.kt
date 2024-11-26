package com.demo.protodatastore.serializer

import androidx.datastore.core.Serializer
import com.demo.proto.Session
import com.google.protobuf.InvalidProtocolBufferException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream

object SessionSerializer : Serializer<Session> {
  override val defaultValue: Session
    get() = Session.getDefaultInstance()

  override suspend fun readFrom(input: InputStream): Session {
    return withContext(Dispatchers.IO) {
      return@withContext try {
        Session.parseFrom(input)
      } catch (e: InvalidProtocolBufferException) {
        e.printStackTrace()
        defaultValue
      }
    }
  }

  override suspend fun writeTo(t: Session, output: OutputStream) {
    withContext(Dispatchers.IO) {
      t.writeTo(output)
    }
  }
}