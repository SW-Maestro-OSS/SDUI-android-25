package com.swm.sdui_android_25

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.swm.sdui_android_25.domain.model.ButtonComponentDto
import com.swm.sdui_android_25.domain.model.CardComponentDto
import com.swm.sdui_android_25.domain.model.ColumnComponentDto
import com.swm.sdui_android_25.domain.model.ComponentSpec
import com.swm.sdui_android_25.domain.model.ImageComponentDto
import com.swm.sdui_android_25.domain.model.ModifierSpec
import com.swm.sdui_android_25.domain.model.RowComponentDto
import com.swm.sdui_android_25.domain.model.ScreenDto
import com.swm.sdui_android_25.domain.model.SpacerComponentDto
import com.swm.sdui_android_25.domain.model.TextComponentDto
import com.swm.sdui_android_25.domain.model.ComponentType
import com.swm.sdui_android_25.ui.components.SDUIButton
import com.swm.sdui_android_25.ui.components.SDUICard
import com.swm.sdui_android_25.ui.components.SDUIColumn
import com.swm.sdui_android_25.ui.components.SDUIImage
import com.swm.sdui_android_25.ui.components.SDUIRow
import com.swm.sdui_android_25.ui.components.SDUISpacer
import com.swm.sdui_android_25.ui.components.SDUIText

@Composable
fun SDUIRenderer(
    screen: ScreenDto,
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        if (screen.title.isNotEmpty()) {
            Text(
                text = screen.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        screen.components.forEach { componentDto ->
            RenderComponent(
                component = componentDto,
                modifier = Modifier.padding(bottom = 16.dp),
            )
        }
    }
}

@Composable
fun RenderComponent(
    component: ComponentSpec,
    modifier: Modifier = Modifier
) {
    val finalModifier = modifier.applyModifierSpec(component.modifierSpec)
    
    when (component) {
        is SpacerComponentDto -> SDUISpacer(component, finalModifier)
        is TextComponentDto -> SDUIText(component, finalModifier)
        is ButtonComponentDto -> SDUIButton(component, finalModifier)
        is ImageComponentDto -> SDUIImage(component, finalModifier)
        is CardComponentDto -> SDUICard(component, finalModifier)
        is ColumnComponentDto -> SDUIColumn(component, finalModifier)
        is RowComponentDto -> SDUIRow(component, finalModifier)
    }
}

fun Modifier.applyModifierSpec(modifierSpec: ModifierSpec?): Modifier {
    var modifier = this
    modifierSpec?.let { spec ->
        spec.height?.let { modifier = modifier.height(it.dp) }
        spec.width?.let { modifier = modifier.width(it.dp) }
        // elevation은 나중에 Card나 다른 컴포넌트에서 별도로 처리
    }
    return modifier
}