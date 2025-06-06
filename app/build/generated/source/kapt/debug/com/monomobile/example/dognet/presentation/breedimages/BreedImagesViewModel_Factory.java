package com.monomobile.example.dognet.presentation.breedimages;

import com.monomobile.example.dognet.domain.repository.DogRepository;
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
public final class BreedImagesViewModel_Factory implements Factory<BreedImagesViewModel> {
  private final Provider<DogRepository> repositoryProvider;

  public BreedImagesViewModel_Factory(Provider<DogRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public BreedImagesViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static BreedImagesViewModel_Factory create(Provider<DogRepository> repositoryProvider) {
    return new BreedImagesViewModel_Factory(repositoryProvider);
  }

  public static BreedImagesViewModel newInstance(DogRepository repository) {
    return new BreedImagesViewModel(repository);
  }
}
