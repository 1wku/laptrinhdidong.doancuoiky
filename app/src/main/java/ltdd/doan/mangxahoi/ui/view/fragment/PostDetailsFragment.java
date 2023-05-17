package ltdd.doan.mangxahoi.ui.view.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Iterator;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.data.dto.response.LikePostResponse;
import ltdd.doan.mangxahoi.data.model.Comment;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.databinding.FragmentPostDetailsBinding;
import ltdd.doan.mangxahoi.interfaces.OnCreateCommentResult;
import ltdd.doan.mangxahoi.interfaces.OnDeletePostResult;
import ltdd.doan.mangxahoi.interfaces.OnGetPostByIdResult;
import ltdd.doan.mangxahoi.interfaces.OnGetUserDetailResult;
import ltdd.doan.mangxahoi.interfaces.OnLikePostResult;
import ltdd.doan.mangxahoi.session.Session;
import ltdd.doan.mangxahoi.ui.view.activity.MainActivity;
import ltdd.doan.mangxahoi.ui.view.adapter.CommentAdapter;
import ltdd.doan.mangxahoi.ui.view.adapter.PostAdapterProfile;
import ltdd.doan.mangxahoi.ui.viewmodel.PostDetailsViewModel;
@AndroidEntryPoint
public class PostDetailsFragment extends Fragment {

    private FragmentPostDetailsBinding binding;

    private PostDetailsViewModel mViewModel;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PostDetailsViewModel.class);
    }
    public Boolean isLike(LikePostResponse res){
        for (String u : res.likes) {
            if (u != null) {
                if (u.equals(Session.getSharedPreference(getContext(), "user_id", ""))) {
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_details, container, false);
        binding.setPostDetailsFragment(this);


        String post_id = getArguments().getString("post_id");


        binding.frgPostDetailsSwipeRefresh.setOnRefreshListener(() -> {
            System.out.println("Before get post by id ");
            mViewModel.getPostDetailsById(post_id);
            mViewModel.getCommentsByPost(post_id);

            binding.frgPostDetailsSwipeRefresh.setRefreshing(false);
        });

        mViewModel.getPost().observe(getViewLifecycleOwner(), post -> {
            if (!Objects.equals(post.getPhoto() , "none image")){
                Glide.with(requireContext())
                        .load(post.getPhoto())
                        .into(binding.frgPostDetailsImgPostImage);
            }
            else {
                binding.frgPostDetailsImgPostImage.setVisibility(View.GONE);
            }

            if (!Objects.equals(post.getOwnerData().getAvatar() , "none image") && !Objects.equals(post.getOwnerData().getAvatar() , "")){
                Glide.with(requireContext())
                        .load(post.getOwnerData().getAvatar())
                        .into(binding.frgPostDetailsUserAVT);
            }

            // TODO: 4/18/2023 ảnh

            if (isPostLiked(post)) binding.frgPostDetailsImgLike.setImageDrawable(requireContext().getDrawable(R.drawable.ic_heart_red));
            else binding.frgPostDetailsImgLike.setImageDrawable(requireContext().getDrawable(R.drawable.ic_heart));



            binding.setPost(post);

        });
        // TODO: 4/18/2023 comment cart + adapter

        mViewModel.getComments().observe(getViewLifecycleOwner(), comments -> {
            CommentAdapter commentAdapter = new CommentAdapter(requireContext(),mViewModel.getComments().getValue() );
            binding.frgCommentRecyclerView.setLayoutManager( new LinearLayoutManager(requireContext()));
            binding.setCommentAdapter(commentAdapter);
        });

        mViewModel.getPostDetailsById(post_id);
        mViewModel.getCommentsByPost(post_id);

        binding.frgPostDetailsImgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.like(post_id, new OnLikePostResult() {
                    @Override
                    public void onSuccess(LikePostResponse result) {
                        if ( isLike(result)){
                            binding.frgPostDetailsImgLike.setImageResource(R.drawable.ic_heart_red);
                        }else{
                            binding.frgPostDetailsImgLike.setImageResource(R.drawable.ic_heart);
                        }
                        binding.frgPostDetailsLblPostLikes.setText(result.likes.size()+ " lượt thích");
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
            }
        });

        binding.frgPostDetailsImgPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(requireContext(), binding.frgPostDetailsImgPopupMenu);

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup_post, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        // TODO : delete + edit post

                        CharSequence title = menuItem.getTitle();
                        if ("Edit Post".equals(title)) {
                            Toast.makeText(requireContext(), "You Clicked edit " + menuItem.getItemId(), Toast.LENGTH_SHORT).show();

                        } else if ("Delete Post".equals(title)) {
                            if (mViewModel.getPost().getValue().getOwner().equals(Session.getSharedPreference(getContext(),"user_id",""))){
                                mViewModel.deletePost(post_id, new OnDeletePostResult() {
                                    @Override
                                    public void onSuccess(String result) {
                                        Toast.makeText(requireContext(), "Xoá bài đăng thành công  ", Toast.LENGTH_SHORT).show();
                                        navToMyProfile(binding.frgPostDetailsImgPostImage);
                                    }

                                    @Override
                                    public void onError(String error) {
                                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }
                            else Toast.makeText(requireContext(), "Bạn không có quyền xoá bài đăng này", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });


        return binding.getRoot();
    }

    public void navToMyProfile(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("user_id", Session.getSharedPreference(getContext(),"user_id",""));
        Navigation.findNavController(view).navigate(ltdd.doan.mangxahoi.ui.view.fragment.PostDetailsFragmentDirections.followToProfile().getActionId(), bundle);
    }

    public boolean isPostLiked(Post post) {

        if (post.getLikers() == null) return false;

        for (String u : post.getLikers()) {
            if (u != null){
                if (u.equals(Session.getSharedPreference(requireContext(),"user_id",""))) {
                    return true;
                }
            }

        }
        return false;
    }

    public void commentPost(String post_id){
        String comment = binding.frgPostDetailsTxtComment.getText().toString();
        mViewModel.createComment(post_id, comment, new OnCreateCommentResult() {
            @Override
            public void onSuccess(Comment data) {
                binding.frgPostDetailsTxtComment.setText("");
                mViewModel.getCommentsByPost(post_id);

            }

            @Override
            public void onError(String error) {

            }
        });

    }

}