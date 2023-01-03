package com.ashu.ocotopus.ui.home.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0010B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u001c\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0007H\u0016J\u001c\u0010\f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/ashu/ocotopus/ui/home/adapter/DishAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ashu/ocotopus/ui/home/adapter/DishAdapter$DishViewHolder;", "dishData", "Lcom/ashu/ocotopus/data/Dish;", "(Lcom/ashu/ocotopus/data/Dish;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DishViewHolder", "app_release"})
public final class DishAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.ashu.ocotopus.ui.home.adapter.DishAdapter.DishViewHolder> {
    private final com.ashu.ocotopus.data.Dish dishData = null;
    
    public DishAdapter(@org.jetbrains.annotations.Nullable()
    com.ashu.ocotopus.data.Dish dishData) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.ashu.ocotopus.ui.home.adapter.DishAdapter.DishViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.ashu.ocotopus.ui.home.adapter.DishAdapter.DishViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2 = {"Lcom/ashu/ocotopus/ui/home/adapter/DishAdapter$DishViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/ashu/ocotopus/ui/home/adapter/DishAdapter;Landroid/view/View;)V", "dishDescription", "Landroidx/appcompat/widget/AppCompatTextView;", "getDishDescription", "()Landroidx/appcompat/widget/AppCompatTextView;", "dishImage", "Landroidx/appcompat/widget/AppCompatImageView;", "getDishImage", "()Landroidx/appcompat/widget/AppCompatImageView;", "app_release"})
    public final class DishViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final androidx.appcompat.widget.AppCompatImageView dishImage = null;
        @org.jetbrains.annotations.NotNull()
        private final androidx.appcompat.widget.AppCompatTextView dishDescription = null;
        
        public DishViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.appcompat.widget.AppCompatImageView getDishImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.appcompat.widget.AppCompatTextView getDishDescription() {
            return null;
        }
    }
}