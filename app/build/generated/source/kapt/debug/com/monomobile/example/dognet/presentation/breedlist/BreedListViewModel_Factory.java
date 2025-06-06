package com.monomobile.example.dognet.presentation.breedlist;

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
public final class BreedListViewModel_Factory implements Factory<BreedListViewModel> {
  private final Provider<DogRepository> repositoryProvider;

  public BreedListViewModel_Factory(Provider<DogRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public BreedListViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static BreedListViewModel_Factory create(Provider<DogRepository> repositoryProvider) {
    return new BreedListViewModel_Factory(repositoryProvider);
  }

  public static BreedListViewModel newInstance(DogRepository repository) {
    return new BreedListViewModel(repository);
  }
}
