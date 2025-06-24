package com.example.shoppinglist.domain

class DeleteShopItemUseCase(private val repository: ShopListRepository) {

    suspend fun deleteShopItem(shopItemId: Int) {
        repository.deleteShopItem(shopItemId)
    }
}