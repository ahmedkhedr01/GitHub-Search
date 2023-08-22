package com.example.project.ui.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.project.ui.GitHubApp
import com.example.project.ui.theme.ProjectTheme
import com.example.project.ui.viewmodels.GitHubViewModel
import androidx.compose.material3.TextField
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.project.R
import com.example.project.data.model.GitHubUser
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(viewModel: GitHubViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    val users: List<GitHubUser> by viewModel.users.collectAsState(emptyList())
    val coroutineScope = rememberCoroutineScope()
    val onClick: () -> Unit = {
        coroutineScope.launch {
            println("I am in the on Click")
           viewModel.searchUsers(searchQuery)
        }
    }
Column(modifier = Modifier
    .fillMaxSize()
    .background(Color.Black)
    ,horizontalAlignment = Alignment.CenterHorizontally) {
    Image(
        painter = painterResource(id = R.drawable.github_icon),
        contentDescription = "GitHub Icon",
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(40.dp))
    )
    SearchField(searchQuery = searchQuery, onSearchQueryChange = { newQuery ->
        searchQuery = newQuery
    }, onClick = onClick)
    SearchResults(users)
}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(searchQuery: String, onClick:()-> Unit, onSearchQueryChange: (String) -> Unit) {
    Row(modifier = Modifier
        .padding(top = 16.dp, bottom = 16.dp)
        .fillMaxWidth(0.9f)
        .border(
            BorderStroke(1.dp, Color.Cyan),
            RoundedCornerShape(8.dp)
        )) {
        TextField(value = searchQuery, onValueChange = onSearchQueryChange,
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon" )},
            singleLine = true,
            placeholder = { Text(text = "Search") },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onSearchQueryChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear Search Icon"
                        )
                    }
                }
            },
             modifier = Modifier
                 .width(240.dp)
                 .padding(end = 12.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White,
                textColor = Color.White
            ),)
        Button(onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
            enabled = searchQuery.isNotEmpty(),
            modifier = Modifier.padding(top = 4.dp)) {
            Text(text = "Submit"
                , color = Color.Black)
        }
    }

}


@Composable
fun SearchResults(users: List<GitHubUser>) {
    LazyColumn {
        items(users) { user ->
            UserListItem(user)
        }
    }
}

@Composable
fun UserListItem(user: GitHubUser) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Display the profile picture
        Image(
            painter = rememberAsyncImagePainter(user.avatar_url),
            contentDescription = "Profile Picture",
            modifier = Modifier.size(50.dp).clip(RoundedCornerShape(25.dp))
        )

        // Display the login name
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = user.login, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjectTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GitHubApp()
        }
    }
}