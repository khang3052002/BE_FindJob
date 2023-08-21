package backend.findjob.services.impls;

import backend.findjob.dto.request.PushNotificationRequest;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.services.IFirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingService implements IFirebaseMessagingService {
    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @Override
    public ResponseEntity<ResponeObject> pushNotificationByToken(PushNotificationRequest notificationRequest) {
        Notification notification = Notification.builder()
                .setTitle(notificationRequest.getTitle())
                .setBody(notificationRequest.getDescription())

                .build();

        Message message = Message
                .builder()
                .setNotification(notification)
//                .setToken(notificationRequest.getDevice_token())
                .setTopic(notificationRequest.getTopic())
                .build();

        try
        {
            firebaseMessaging.send(message);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponeObject("Successfull","Send notification successful",""));

        }catch (FirebaseMessagingException e)
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponeObject("Fail", e.getMessage(), ""));
        }
    }
}
