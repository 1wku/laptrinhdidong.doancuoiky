package ltdd.doan.mangxahoi.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.repository.PostRepository;

public class FeedViewModel extends ViewModel {
    private PostRepository pRepo;
    private MutableLiveData<List<Post>> list_posts;


    public FeedViewModel() {
    }

    public MutableLiveData<List<Post>> getPosts(){
        return list_posts;
    }

    public void getFeed(){
        pRepo.getFeed();
    }
}