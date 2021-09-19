package dev.androidbroadcast.devto.api.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

internal class LocalDateSerializer : KSerializer<Date> {
    private fun newDateFormat(): DateFormat {
        return SimpleDateFormat("MMM d, yyyy", Locale.US).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
    }

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Date {
        try {
            return newDateFormat().parse(decoder.decodeString())
        } catch (e: ParseException) {
            throw SerializationException(e)
        }
    }

    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeString(newDateFormat().format(value))
    }
}
