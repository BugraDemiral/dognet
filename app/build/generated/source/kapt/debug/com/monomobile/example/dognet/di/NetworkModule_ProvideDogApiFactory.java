package com.monomobile.example.dognet.di;

import com.monomobile.example.dognet.data.remote.DogApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideDogApiFactory implements Factory<DogApi> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideDogApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public DogApi get() {
    return provideDogApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideDogApiFactory create(Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideDogApiFactory(retrofitProvider);
  }

  public static DogApi provideDogApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideDogApi(retrofit));
  }
}
