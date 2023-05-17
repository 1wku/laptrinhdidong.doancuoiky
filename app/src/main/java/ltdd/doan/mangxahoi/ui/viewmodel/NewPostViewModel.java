package ltdd.doan.mangxahoi.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.repository.PostRepository;
import ltdd.doan.mangxahoi.interfaces.OnCreatePostResult;

@HiltViewModel
public class NewPostViewModel extends ViewModel {

    private PostRepository pRepo;

    @Inject
    public NewPostViewModel (PostRepository pRepo){
        this.pRepo = pRepo;
    }
    public void createPost(String content, String photo){
        pRepo.createPost(content, photo, new OnCreatePostResult() {
            @Override
            public void onSuccess(Post data) {

            }

            @Override
            public void onError(String error) {

            }
        });
    }
}