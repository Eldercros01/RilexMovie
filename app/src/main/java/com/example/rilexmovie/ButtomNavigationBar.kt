package com.example.rilexmovie

import android.widget.Toast
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar() {
    val bottomMenuItemsList = prepareBottomMenu()
    val contextForToast = LocalContext.current
    var selectedItem by remember { mutableStateOf("Home") }

    BottomAppBar(
        cutoutShape = CircleShape,
        contentColor = colorResource(id = R.color.white),
        backgroundColor = colorResource(id = R.color.black), // Se corrigió a negro
        elevation = 3.dp
    ) {
        BottomNavigation(
            backgroundColor = colorResource(id = R.color.black) // Se asegura el fondo negro
        ) {
            bottomMenuItemsList.forEach { bottomMenuItem ->
                BottomNavigationItem(
                    selected = (selectedItem == bottomMenuItem.label),
                    onClick = {
                        selectedItem = bottomMenuItem.label
                        Toast.makeText(contextForToast, bottomMenuItem.label, Toast.LENGTH_SHORT).show()
                    },
                    icon = {
                        Icon(
                            painter = bottomMenuItem.icon,
                            contentDescription = bottomMenuItem.label,
                            modifier = Modifier
                                .height(20.dp)
                                .width(20.dp)
                        )
                    },
                    label = {
                        Text(
                            text = bottomMenuItem.label,
                            modifier = Modifier.padding(top = 14.dp)
                        )
                    },
                    alwaysShowLabel = true
                )
            }
        }
    }
}

data class BottomMenuItem(
    val label: String,
    val icon: Painter
)

@Composable
fun prepareBottomMenu(): List<BottomMenuItem> {
    return listOf(
        BottomMenuItem("Home", painterResource(id = R.drawable.btn_1)),
        BottomMenuItem("Profile", painterResource(id = R.drawable.btn_2)),
        BottomMenuItem("Support", painterResource(id = R.drawable.btn_3)),
        BottomMenuItem("Settings", painterResource(id = R.drawable.btn_4))
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BottomNavigationBar()
}
