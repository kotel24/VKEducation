package ru.sumin.vkeducation.presentation.applist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import ru.sumin.vkeducation.R
import ru.sumin.vkeducation.domain.applist.AppsList
import ru.sumin.vkeducation.presentation.appdetails.AppDetailsError
import ru.sumin.vkeducation.presentation.appdetails.AppDetailsLoading


@Composable
fun AppsListScreen(
    modifier: Modifier = Modifier,
    onAppClick: (AppsList) -> Unit = {},
    viewModel: AppListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(
        Unit
    ) {
        viewModel.events.collect { event ->
            when(event){
                is AppsListEvent.ShowSnackbar -> snackbarHostState.showSnackbar(event.message)
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->
        when (val currentState = uiState) {
            is AppListUiState.Loading -> {
                AppDetailsLoading(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                )
            }

            is AppListUiState.Error -> {
                AppDetailsError(
                    onRefreshClick = { viewModel.getAppsList() },
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                )
            }

            is AppListUiState.Content -> {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color(0xFF0079FF))
                ) {
                    Spacer(
                        modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars)
                    )

                    AppsListTopBar(onLogoClick = { viewModel.onLogoClick() })

                    AppsListContent(
                        content = currentState,
                        onAppClick = onAppClick,
                        contentPadding = PaddingValues(10.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun AppsListContent(
    content: AppListUiState.Content,
    onAppClick: (AppsList) -> Unit,
    contentPadding: PaddingValues,
) {
    val apps = content.appsList
    Surface(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        color = Color.White,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = contentPadding,
        ) {
            items(
                items = apps,
                key = { it.name }
            ) { app ->
                AppRow(
                    apps = app,
                    onClick = { onAppClick(app) }
                )
            }
        }
    }
}

@Composable
private fun AppsListTopBar(
    onLogoClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.clickable{onLogoClick()},
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.rustore_logo),
                contentDescription = "RuStore logo",
                modifier = Modifier.size(44.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "RuStore",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Surface(
            modifier = Modifier.size(34.dp),
            shape = RoundedCornerShape(8.dp),
            color = Color.White.copy(alpha = 0.16f)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Outlined.Apps,
                    contentDescription = "Apps menu",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
private fun AppRow(
    apps: AppsList,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = apps.iconUrl,
            contentDescription = apps.name,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = apps.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF151515),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = apps.category,
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF2D2D2D),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = apps.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF8E8E93),
                maxLines = 1,
            )
        }
    }
}