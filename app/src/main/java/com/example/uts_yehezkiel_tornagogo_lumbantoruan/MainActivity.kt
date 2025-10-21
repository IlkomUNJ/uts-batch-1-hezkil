package com.example.uts_yehezkiel_tornagogo_lumbantoruan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uts_yehezkiel_tornagogo_lumbantoruan.ui.theme.UTS_Yehezkiel_Tornagogo_LumbantoruanTheme

data class Contact(
    val id: Int,
    val name: String,
    val address: String
)
object Routes {
    const val LIST_CONTACT = "list_contact"
    const val ADD_CONTACT = "add_contact"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UTS_Yehezkiel_Tornagogo_LumbantoruanTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UTS_Yehezkiel_Tornagogo_LumbantoruanTheme {

    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LIST_CONTACT
    ) {
        composable(Routes.LIST_CONTACT) {
            ListContactScreen(navController = navController)
        }

        composable(Routes.ADD_CONTACT) {
            AddContactScreen(navController = navController)
        }
    }
}

@Composable
fun AddContactScreen(navController: NavHostController) {

    Scaffold(

        topBar = {
            Text(
                text = "Add Contact",
                modifier = Modifier.padding(20.dp,top = 30.dp)
            )


        },
        bottomBar = {
            BottomAppBar (
                actions = { /* TODO */ },
                floatingActionButton = {
                    FloatingActionButton (
                        onClick = {
                            navController.navigate(Routes.LIST_CONTACT)
                        },
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Add Contact")
                    }
                }
            )
        }

    ) { padding ->


    }

}



@Composable
fun ContactItem(contact: Contact) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = contact.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = contact.address,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
    Divider(color = Color.LightGray, thickness = 0.5.dp)
}

@Composable
fun ListContactScreen(navController: NavHostController) {
    val contacts = remember { mutableStateOf(emptyList<Contact>()) }


    Scaffold(
        topBar = {
            Text(
                text = "DashBoard",
                modifier = Modifier.padding(16.dp, top=20.dp)
            )
        },
        bottomBar = {
            BottomAppBar (
                actions = { /* TODO */ },
                floatingActionButton = {
                    FloatingActionButton (
                        onClick = {
                            navController.navigate(Routes.ADD_CONTACT)
                        },
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Add Contact")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (contacts.value.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("No Contact", style = MaterialTheme.typography.headlineSmall)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

            }
        }
    }
}

