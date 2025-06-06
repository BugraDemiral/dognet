package com.monomobile.example.dognet.di;

import com.monomobile.example.dognet.data.remote.DogApi;
import com.monomobile.example.dognet.domain.repository.DogRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class NetworkModule_ProvideDogRepositoryFactory implements Factory<DogRepository> {
  private final Provider<DogApi> apiProvider;

  public NetworkModule_ProvideDogRepositoryFactory(Provider<DogApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public DogRepository get() {
    return provideDogRepository(apiProvider.get());
  }

  public static NetworkModule_ProvideDogRepositoryFactory create(Provider<DogApi> apiProvider) {
    return new NetworkModule_ProvideDogRepositoryFactory(apiProvider);
  }

  public static DogRepository provideDogRepository(DogApi api) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideDogRepository(api));
  }
}
