package misc.serializers

import java.math.BigDecimal
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = BigDecimal::class)
object BigDecimalSerializer : KSerializer<BigDecimal> {
    @InternalSerializationApi
    override val descriptor: SerialDescriptor =
        buildSerialDescriptor("BigDecimal", PrimitiveKind.DOUBLE)

    override fun serialize(encoder: Encoder, value: BigDecimal) {
        encoder.encodeString(value.toPlainString())
    }

    override fun deserialize(decoder: Decoder): BigDecimal {
        val stringValue = decoder.decodeString()
        return if (stringValue.isNotEmpty()) {
            BigDecimal(stringValue)
        } else {
            BigDecimal.ZERO
        }
    }
}
