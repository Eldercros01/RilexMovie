package com.example.rilexmovie.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rilexmovie.BottomNavigationBar
import com.example.rilexmovie.CustomSearchBar
import com.example.rilexmovie.Domain.FilmItemModel
import com.example.rilexmovie.FilmItem
import com.example.rilexmovie.R
import com.example.rilexmovie.ViewModel.MainViewModel
import com.example.rilexmovie.ui.theme.RilexMovieTheme

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RilexMovieTheme {
                MainScreen(onItemClick = { item ->
                    val intent = Intent(this, DetailMovieActivity::class.java)
                    intent.putExtra("object", item)
                    startActivity(intent)
                })
            }

        }
    }
}

@Composable
fun MainScreen(onItemClick: (FilmItemModel) -> Unit = {}) {
    Scaffold(
        bottomBar = {
            Box(modifier = Modifier.background(colorResource(R.color.black))) {
                BottomNavigationBar()
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                backgroundColor = colorResource(id = R.color.black3),
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(R.drawable.float_icon),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = colorResource(R.color.blackBackground)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = colorResource(R.color.blackBackground))
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            MainContent(onItemClick)
        }

    }
}

@Composable
fun MainContent(onItemClick: (FilmItemModel) -> Unit) {
    val viewModel: MainViewModel = viewModel()
    val upcoming = remember { mutableStateListOf<FilmItemModel>() }
    val newMoview = remember { mutableStateListOf<FilmItemModel>() }

    var showUpcomingLoad by remember { mutableStateOf(true) }
    var showNewMoviesLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.loadUpcoming().observeForever {
            upcoming.clear()
            upcoming.addAll(it)
            showUpcomingLoad = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadItems().observeForever {
            newMoview.clear()
            newMoview.addAll(it)
            showNewMoviesLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 60.dp, bottom = 100.dp)
    ) {
        Text(
            text = "What would you like to watch?",
            style = TextStyle(color = Color.White, fontSize = 25.sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )

        CustomSearchBar(hint = "Search Movie")

        SectionTitle(Title = "New Movies")

        if (showNewMoviesLoading){
              Box(
                  modifier = Modifier
                      .fillMaxWidth()
                      .height(50.dp), contentAlignment = Alignment.Center
              ){
                  CircularProgressIndicator()
              }
         }else{
             LazyRow (horizontalArrangement = Arrangement.spacedBy(8.dp)
             , contentPadding =  PaddingValues(horizontal = 16.dp)
             ){
                 items (newMoview) { item ->
                     FilmItem(item, onItemClick)

                 }
             }

        }

        SectionTitle("Upcoming Movies")

        if (showUpcomingLoad){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }else{
            LazyRow (horizontalArrangement = Arrangement.spacedBy(8.dp)
                , contentPadding =  PaddingValues(horizontal = 16.dp)
            ){
                items (upcoming) { item ->
                    FilmItem(item, onItemClick)

                }
            }

        }
    }
}

@Composable
fun SectionTitle(Title: String) {
    Text(
        text = Title,
        style = TextStyle(color = Color(0xFFFFA000), fontSize = 18.sp),
        modifier = Modifier.padding(start = 16.dp, top = 32.dp, bottom = 8.dp),
        fontWeight = FontWeight.Bold
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {

}