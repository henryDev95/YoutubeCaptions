package com.henrymoya.youtubecaptions.search.ui.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.henrymoya.youtubecaptions.core.flow.rememberFlowWithLifecycle
import com.henrymoya.youtubecaptions.search.uistate.CaptionUiState
import com.henrymoya.youtubecaptions.search.viewmodel.CaptionViewModel
import com.henrymoya.youtubecaptions.uikit.progressbar.LoadingProgressWidget
import com.henrymoya.youtubecaptions.uikit.row.ItemCaptionVideo
import com.henrymoya.youtubecaptions.uikit.textfield.FOutlineTextFieldSearch


@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun SearchScreen(
    viewModel: CaptionViewModel = hiltViewModel(),
) {

    val captionState by rememberFlowWithLifecycle(viewModel.captionState)
        .collectAsState(initial = CaptionUiState.Empty)

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Listado de subtitulo")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                )
            )
        },
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {

            FOutlineTextFieldSearch(
                label = "Search",
                paddingValues = PaddingValues(horizontal = 2.dp),
                onSearchAction = {
                    viewModel.search(it)
                }
            )
            LazyColumn(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            ) {

                items(items = captionState.captionsResult) { item ->
                    ItemCaptionVideo(item.text, item.start)
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                }
            }
        }

        LoadingProgressWidget(captionState.isLoading)
    }
}

