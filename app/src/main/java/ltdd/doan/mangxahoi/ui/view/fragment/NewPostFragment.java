package ltdd.doan.mangxahoi.ui.view.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.Console;
import java.io.File;
import java.util.UUID;
import java.util.concurrent.Executor;

import dagger.hilt.android.AndroidEntryPoint;
import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.databinding.FragmentNewPostBinding;
import ltdd.doan.mangxahoi.ui.viewmodel.NewPostViewModel;
@AndroidEntryPoint
public class NewPostFragment extends Fragment {

    private FragmentNewPostBinding binding;
    private NewPostViewModel viewModel;

    public Uri imageUri;
    private ActivityResultLauncher<Intent> selectImageResultLauncher;
    private ActivityResultLauncher<String[]> permissionResultLauncher;

    private FirebaseAuth mAuth;

    FirebaseStorage storage;

    StorageReference storageReference;

    @Override
    public void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();

        mAuth.signInAnonymously();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user == null){
            System.out.println("signIn Fail");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(NewPostViewModel.class);


        // select image
        selectImageResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                if (result.getData() != null) {
                    imageUri = result.getData().getData();
                    binding.frgNewPostImgSelectPhoto.setImageURI(imageUri);

                }
            }
        });

        // permission
        permissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
            if (Boolean.TRUE.equals(result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE)) && Boolean.TRUE.equals(result.get(Manifest.permission.READ_EXTERNAL_STORAGE))) {
                Toast.makeText(requireContext(), "Cấp quyền thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Cấp quyền không thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_post, container, false);
        binding.setNewPostFragment(this);

        return binding.getRoot();
    }

    public void createPost(String postDescription) {

        String postPhotoId = UUID.randomUUID().toString();

        if (imageUri != null) {
            if (!postDescription.isEmpty()) {

                storageReference = storage.getReference("images/"+ postPhotoId);
                UploadTask uploadTask = storageReference.putFile(imageUri);

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return storageReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            viewModel.createPost(postDescription, downloadUri.toString());
                            Toast.makeText(requireContext(),"Success " ,Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireContext(),"Fail " ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } else Toast.makeText(requireContext(), "Nội dung bài post không được để trống", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(requireContext(), "Ảnh không được để trống", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("IntentReset")
    public void selectImage() {
        if (checkPerm()) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");

            selectImageResultLauncher.launch(intent);
        } else {
            permissionResultLauncher.launch(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE});
        }
    }

    private boolean checkPerm() {
        int permWrite = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permRead = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        return permWrite == PackageManager.PERMISSION_GRANTED && permRead == PackageManager.PERMISSION_GRANTED;
    }
}