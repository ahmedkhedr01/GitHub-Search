package com.example.project.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project.ui.screens.SearchScreen
import com.example.project.ui.theme.ProjectTheme
import com.example.project.ui.viewmodels.GitHubViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GitHubApp()
                }
            }
        }
    }
}
@Composable
fun GitHubApp() {
val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "search"){
        composable("search"){
            SearchScreen( viewModel = GitHubViewModel())
        }
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