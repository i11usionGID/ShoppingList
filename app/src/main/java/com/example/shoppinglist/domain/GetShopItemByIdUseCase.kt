package com.example.shoppinglist.domain

class GetShopItemByIdUseCase(private val repository: ShopListRepository) {

    fun getShopItemById(shopItemId: Int): ShopItem {
        return repository.getShopItemById(shopItemId)
    }
}