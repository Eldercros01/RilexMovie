package com.example.rilexmovie

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rilexmovie.Domain.FilmItemModel

@Composable
fun FilmItem(itemModel: FilmItemModel, onItemClick: (FilmItemModel) -> Unit) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .width(120.dp)
            .clickable { onItemClick(itemModel) }
            .background(color = Color(0xFF303036))
            .padding(8.dp)
    ) {
        AsyncImage(
            model = itemModel.Poster,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = itemModel.Title,
            modifier = Modifier.padding(start = 4.dp),
            style = TextStyle(color = Color.White, fontSize = 11.sp),
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color(0xFFFFC107)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = itemModel.Imdb.toString(),
                style = TextStyle(color = Color.White, fontSize = 11.sp)
            )
        }
    }
}
