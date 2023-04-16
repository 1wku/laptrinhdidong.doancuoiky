package ltdd.doan.mangxahoi.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import java.io.Closeable;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;
public class PostDetailsViewModel extends ViewModel {

    public PostDetailsViewModel() {
    }

    public PostDetailsViewModel(@NonNull Closeable... closeables) {
        super(closeables);
    }

    public Post getPostDetailsById(int post_id) {
        User user = new User();
        Post post = new Post().getEx(user.getEx());
        return post;
    }
}