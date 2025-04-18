package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {

    private val shopItemListLD = MutableLiveData<List<ShopItem>>()

    private val shopItemList = sortedSetOf<ShopItem>( { o1, o2 -> o1.id.compareTo(o2.id)} )

    private var autoIncrementId = 0

    init {
        for (i in 0 until 10000) {
            val item = ShopItem("Name $i", i, true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopItemList.add(shopItem)
        updateShopItemListLD()
    }

    override fun deleteShopItem(shopItemId: Int) {
        shopItemList.removeIf { it.id == shopItemId }
        updateShopItemListLD()
    }

    override fun editShopItem(shopItem: ShopItem) {
        deleteShopItem(shopItem.id)
        addShopItem(shopItem)
    }

    override fun getShopItemById(shopItemId: Int): ShopItem {
        return shopItemList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopItemListLD
    }

    private fun updateShopItemListLD() {
        shopItemListLD.value = shopItemList.toList()
    }
}