package com.ashu.ocotopus.ui.home;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0012H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\f8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e\u00a8\u0006\u0013"}, d2 = {"Lcom/ashu/ocotopus/ui/home/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "mainRepository", "Lcom/ashu/ocotopus/repository/MainRepository;", "(Lcom/ashu/ocotopus/repository/MainRepository;)V", "_resp", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ashu/ocotopus/util/Resource;", "Lcom/ashu/ocotopus/data/Dish;", "_text", "", "res", "Landroidx/lifecycle/LiveData;", "getRes", "()Landroidx/lifecycle/LiveData;", "text", "getText", "getDishes", "Lkotlinx/coroutines/Job;", "app_release"})
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    private final com.ashu.ocotopus.repository.MainRepository mainRepository = null;
    private final androidx.lifecycle.MutableLiveData<com.ashu.ocotopus.util.Resource<com.ashu.ocotopus.data.Dish>> _resp = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _text = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> text = null;
    
    @javax.inject.Inject()
    public HomeViewModel(@org.jetbrains.annotations.NotNull()
    com.ashu.ocotopus.repository.MainRepository mainRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ashu.ocotopus.util.Resource<com.ashu.ocotopus.data.Dish>> getRes() {
        return null;
    }
    
    private final kotlinx.coroutines.Job getDishes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getText() {
        return null;
    }
}