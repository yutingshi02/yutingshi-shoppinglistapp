package com.example.yutingshi_shoppinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class ShoppingItem(val name: String, val quantity: String, var isBought: Boolean = false)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListApp()
        }
    }
}

@Composable
fun ShoppingListApp() {
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }
    val shoppingList = remember { mutableStateListOf<ShoppingItem>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFE4E1)) // Pink background
            .padding(16.dp)
    ) {
        Text("Shopping List", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = itemName,
                onValueChange = { itemName = it },
                label = { Text("Item name") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = itemQuantity,
                onValueChange = { itemQuantity = it },
                label = { Text("Quantity") },
                modifier = Modifier.width(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (itemName.isNotBlank() && itemQuantity.isNotBlank()) {
                    shoppingList.add(ShoppingItem(itemName, itemQuantity))
                    itemName = ""
                    itemQuantity = ""
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFC1E3), // Pink color for the button background
                contentColor = Color.White         // White text color
            ),
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(shoppingList.size) { index ->
                val item = shoppingList[index]
                ShoppingItemRow(item = item)
            }
        }
    }
}

@Composable
fun ShoppingItemRow(item: ShoppingItem) {
    var isChecked by remember { mutableStateOf(item.isBought) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("${item.name} (${item.quantity})", modifier = Modifier.weight(1f))
        Checkbox(
            checked = isChecked,
            onCheckedChange = { checked ->
                isChecked = checked
                item.isBought = checked
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShoppingListApp() {
    ShoppingListApp()
}




