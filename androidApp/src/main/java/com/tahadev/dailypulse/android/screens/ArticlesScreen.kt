package com.tahadev.dailypulse.android.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.tahadev.dailypulse.articles.domain.model.Article
import com.tahadev.dailypulse.articles.presentation.ArticleState
import com.tahadev.dailypulse.articles.presentation.ArticleViewModel
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesScreen(
    articleViewModel: ArticleViewModel = getViewModel(),
    onAboutBtnClicked: () -> Unit
) {

    val state = articleViewModel.articlesState.collectAsState().value

    Scaffold(Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Articles") },
                actions = {
                    IconButton(onClick = { onAboutBtnClicked() }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "About Device Button"
                        )
                    }
                }
            )
        }) {
        val swipeRefreshState = SwipeRefreshState(state is ArticleState.Loading)
        SwipeRefresh(state = swipeRefreshState, onRefresh = { articleViewModel.loadData(true) }) {
        Box(modifier = Modifier.fillMaxSize()) {
                when (state) {
                    is ArticleState.Success -> {
                        ArticlesList(
                            Modifier
                                .padding(16.dp)
                                .fillMaxSize()
                                .padding(it),
                            state.articles,
//                            articleViewModel
                        )
                    }

                    is ArticleState.Error -> {
                        ErrorView(state.errMsg ?: "")
                    }

                    is ArticleState.Loading -> {
                        if (state.isLoading)
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(40.dp)
                                    .align(Alignment.Center)
                            )
                    }
                }
            }
        }
    }
}

@Composable
fun ArticlesList(modifier: Modifier = Modifier, articles: List<Article>) {
//    val swipeRefreshState = SwipeRefreshState(articleState.isLoading)
//    SwipeRefresh(state = viewModel.articlesState is , onRefresh = { viewModel.loadData(forceLoad = true) }) {
        LazyColumn(modifier) {
            itemsIndexed(articles) { _, article ->
                ArticleItem(article)
            }
        }
//    }
}

@Composable
fun ArticleItem(article: Article) {
    Column(Modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(), model = article.imageUrl, contentDescription = "Article image"
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = article.description,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier.padding(bottom = 16.dp).align(Alignment.End),
            text = article.date,
            style = MaterialTheme.typography.bodySmall,
            color = Color.LightGray
        )
    }
}

@Composable
fun ErrorView(errorMsg: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = errorMsg, style = MaterialTheme.typography.titleMedium)
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleItemPrev() {
    ArticleItem(
        Article(
            "Stock market today: Live updates - CNBC",
            "Futures were higher in premarket trading as Wall Street tried to regain its footing.",
            "2024-02-07",
            "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080"
        )
    )
}