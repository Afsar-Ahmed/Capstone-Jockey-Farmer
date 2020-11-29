package sheridan.capstone.findmyfarmer.ImageHandler;

import android.graphics.Bitmap;

import com.google.firebase.storage.StorageReference;

import java.util.List;

public interface StorageResponse {
    void processFinish(List<StorageReference> response,Bitmap bitmap);
    void OnErrorListener(String error);
}
