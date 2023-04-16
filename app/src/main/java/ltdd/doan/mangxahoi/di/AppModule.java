package ltdd.doan.mangxahoi.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import ltdd.doan.mangxahoi.data.repository.UserRepository;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public UserRepository userRepositoryProvide(@ApplicationContext Context context){
        return new UserRepository(context);
    }
}
