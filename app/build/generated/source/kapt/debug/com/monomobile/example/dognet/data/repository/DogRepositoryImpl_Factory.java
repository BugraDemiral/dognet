package com.monomobile.example.dognet.data.repository;

import com.monomobile.example.dognet.data.remote.DogApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DogRepositoryImpl_Factory implements Factory<DogRepositoryImpl> {
  private final Provider<DogApi> apiProvider;

  public DogRepositoryImpl_Factory(Provider<DogApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public DogRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static DogRepositoryImpl_Factory create(Provider<DogApi> apiProvider) {
    return new DogRepositoryImpl_Factory(apiProvider);
  }

  public static DogRepositoryImpl newInstance(DogApi api) {
    return new DogRepositoryImpl(api);
  }
}
