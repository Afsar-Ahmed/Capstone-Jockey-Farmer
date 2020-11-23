package sheridan.capstone.findmyfarmer.ImageHandler;

import com.google.firebase.storage.StorageReference;

import java.util.List;

public interface StorageResponse {
    void processFinish(List<StorageReference> response);
}
