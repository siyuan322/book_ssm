package org.example.yuan.book.pojo;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    // key是商品编号
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();

    /**
     * 添加商品
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        // 先查看购物车中是否已添加过此商品
        CartItem item = items.get(cartItem.getId());

        if (item == null) {
            // 之前没添加过
            items.put(cartItem.getId(), cartItem);
        } else {
            // 已经添加过
            item.setCount(item.getCount() + 1);   // 数量增加
            item.setTotalPrice(item.getPrice() * (item.getCount()));  // 金额更新
        }
    }

    /**
     * 减少一件
     *
     * @param cartItem
     */
    public void removeOneItem(CartItem cartItem) {
        // 先查看购物车中是否已添加过此商品
        CartItem item = items.get(cartItem.getId());

        // 已经添加过
        item.setCount(item.getCount() - 1);   // 数量增加
        item.setTotalPrice(item.getPrice() * (item.getCount()));  // 金额更新

    }

    /**
     * 删除商品项
     *
     * @param id
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }

    /**
     * 更新商品数量
     *
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count) {
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            cartItem.setCount(cartItem.getCount() + 1);   // 数量增加
            cartItem.setTotalPrice(cartItem.getPrice() * cartItem.getCount());  // 金额更新
        }
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }


    public Integer getTotalPrice() {
        Integer totalPrice = new Integer(0);
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice += entry.getValue().getTotalPrice();
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
