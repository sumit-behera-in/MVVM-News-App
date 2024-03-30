package apps.sumit.apitest.features.presentation.mainScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import apps.sumit.apitest.features.presentation.ui.theme.AquaBlue
import apps.sumit.apitest.features.presentation.ui.theme.ButtonBlue
import apps.sumit.apitest.features.presentation.ui.theme.DeepBlue
import apps.sumit.apitest.features.presentation.util.BottomMenuContent


@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighLightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inActiveTextColor: Color = AquaBlue,
    initializedSelectedIndex: Int = 0,
    selectedItem:(Int) -> Unit
) {
    var selectedItemIndex by remember {
        mutableIntStateOf(initializedSelectedIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(4.dp)
    ) {
        items.forEachIndexed { index, bottomMenuContent ->
            BottomMenuItem(
                item = bottomMenuContent,
                isSelected = index == selectedItemIndex,
                activeTextColor = activeTextColor,
                activeHighLightColor = activeHighLightColor,
                inActiveTextColor = inActiveTextColor
            ) {
                selectedItemIndex = index
                selectedItem(selectedItemIndex)
            }
        }
    }
}