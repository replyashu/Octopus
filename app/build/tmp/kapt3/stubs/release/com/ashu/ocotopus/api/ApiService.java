package com.ashu.ocotopus.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0006"}, d2 = {"Lcom/ashu/ocotopus/api/ApiService;", "", "fetchDishes", "Lretrofit2/Response;", "Lcom/ashu/ocotopus/data/Dish;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface ApiService {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "http://10.0.2.2:8081/dish")
    @retrofit2.http.Headers(value = {"Content-Type: application/json;charset=utf-8", "Accept: application/json"})
    public abstract java.lang.Object fetchDishes(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ashu.ocotopus.data.Dish>> continuation);
}