package com.monomobile.example.dognet.data.remote;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\n"}, d2 = {"Lcom/monomobile/example/dognet/data/remote/DogApi;", "", "getAllBreeds", "Lcom/monomobile/example/dognet/data/remote/BreedListResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBreedImages", "Lcom/monomobile/example/dognet/data/remote/BreedImagesResponse;", "breed", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface DogApi {
    
    @retrofit2.http.GET(value = "breeds/list/all")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAllBreeds(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.monomobile.example.dognet.data.remote.BreedListResponse> $completion);
    
    @retrofit2.http.GET(value = "breed/{breed}/images/random/10")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getBreedImages(@retrofit2.http.Path(value = "breed")
    @org.jetbrains.annotations.NotNull
    java.lang.String breed, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.monomobile.example.dognet.data.remote.BreedImagesResponse> $completion);
}