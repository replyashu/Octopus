package com.ashu.ocotopus.ui.home.view;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0012H\u0002J\u001a\u0010\u0017\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u0014H\u0016J\b\u0010\u001b\u001a\u00020\u0012H\u0016J\u001a\u0010\u001c\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u0014H\u0016J\u001a\u0010\u001d\u001a\u00020\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u0012H\u0016J\u0012\u0010#\u001a\u00020\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J$\u0010$\u001a\u00020\u00192\u0006\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u0010+\u001a\u00020\u0012H\u0016J\b\u0010,\u001a\u00020\u0012H\u0002J\b\u0010-\u001a\u00020\u0012H\u0002J\u0010\u0010.\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010/\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u00100\u001a\u00020\u0012H\u0002J\b\u00101\u001a\u00020\u0012H\u0002J\b\u00102\u001a\u00020\u0012H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e\u00a8\u00063"}, d2 = {"Lcom/ashu/ocotopus/ui/home/view/HomeFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/yuyakaido/android/cardstackview/CardStackListener;", "()V", "_binding", "Lcom/ashu/ocotopus/databinding/FragmentHomeBinding;", "binding", "getBinding", "()Lcom/ashu/ocotopus/databinding/FragmentHomeBinding;", "dishAdapter", "Lcom/ashu/ocotopus/ui/home/adapter/DishAdapter;", "viewModel", "Lcom/ashu/ocotopus/ui/home/HomeViewModel;", "getViewModel", "()Lcom/ashu/ocotopus/ui/home/HomeViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "addFirst", "", "size", "", "addLast", "initialize", "onCardAppeared", "view", "Landroid/view/View;", "position", "onCardCanceled", "onCardDisappeared", "onCardDragging", "direction", "Lcom/yuyakaido/android/cardstackview/Direction;", "ratio", "", "onCardRewound", "onCardSwiped", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "paginate", "reload", "removeFirst", "removeLast", "replace", "setUpUIElements", "swap", "app_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class HomeFragment extends androidx.fragment.app.Fragment implements com.yuyakaido.android.cardstackview.CardStackListener {
    private com.ashu.ocotopus.databinding.FragmentHomeBinding _binding;
    private final kotlin.Lazy viewModel$delegate = null;
    private com.ashu.ocotopus.ui.home.adapter.DishAdapter dishAdapter;
    
    public HomeFragment() {
        super();
    }
    
    private final com.ashu.ocotopus.databinding.FragmentHomeBinding getBinding() {
        return null;
    }
    
    private final com.ashu.ocotopus.ui.home.HomeViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void setUpUIElements() {
    }
    
    private final void initialize() {
    }
    
    private final void paginate() {
    }
    
    private final void reload() {
    }
    
    private final void addFirst(int size) {
    }
    
    private final void addLast(int size) {
    }
    
    private final void removeFirst(int size) {
    }
    
    private final void removeLast(int size) {
    }
    
    private final void replace() {
    }
    
    private final void swap() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @java.lang.Override()
    public void onCardDragging(@org.jetbrains.annotations.Nullable()
    com.yuyakaido.android.cardstackview.Direction direction, float ratio) {
    }
    
    @java.lang.Override()
    public void onCardSwiped(@org.jetbrains.annotations.Nullable()
    com.yuyakaido.android.cardstackview.Direction direction) {
    }
    
    @java.lang.Override()
    public void onCardRewound() {
    }
    
    @java.lang.Override()
    public void onCardCanceled() {
    }
    
    @java.lang.Override()
    public void onCardAppeared(@org.jetbrains.annotations.Nullable()
    android.view.View view, int position) {
    }
    
    @java.lang.Override()
    public void onCardDisappeared(@org.jetbrains.annotations.Nullable()
    android.view.View view, int position) {
    }
}