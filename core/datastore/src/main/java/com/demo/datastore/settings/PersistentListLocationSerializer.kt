package com.demo.datastore.settings

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.descriptors.listSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class PersistentListLocationSerializer : KSerializer<PersistentList<Location>> {
  private val delegateSerializer = ListSerializer(Location.serializer())

  @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
  override val descriptor: SerialDescriptor = buildSerialDescriptor(
    serialName = "PersistentList",
    kind = SerialKind.CONTEXTUAL, // indicates that this serializer is contextual serializer
    listSerialDescriptor(Location.serializer().descriptor),
  )

  override fun deserialize(decoder: Decoder): PersistentList<Location> {
    return delegateSerializer.deserialize(decoder = decoder).toPersistentList()
  }

  override fun serialize(encoder: Encoder, value: PersistentList<Location>) {
    delegateSerializer.serialize(encoder = encoder, value = value.toList())
  }
}
