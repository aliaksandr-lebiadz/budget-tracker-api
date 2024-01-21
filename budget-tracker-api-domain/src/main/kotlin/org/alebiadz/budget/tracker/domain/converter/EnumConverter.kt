package org.alebiadz.budget.tracker.domain.converter

import org.alebiadz.budget.tracker.commons.HasText
import org.alebiadz.budget.tracker.domain.entity.AccountType
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class EnumConverter : AttributeConverter<HasText, String> {

    override fun convertToDatabaseColumn(hasText: HasText): String {

        return hasText.text
    }

    override fun convertToEntityAttribute(text: String): AccountType {

        return HasText.byText(text)
    }
}