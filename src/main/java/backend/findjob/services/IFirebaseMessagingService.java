package backend.findjob.services;

import backend.findjob.dto.request.PushNotificationRequest;
import backend.findjob.dto.respone.ResponeObject;
import org.springframework.http.ResponseEntity;

public interface IFirebaseMessagingService {


    ResponseEntity<ResponeObject> pushNotificationByToken(PushNotificationRequest notificationRequest);
}
