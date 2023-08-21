package backend.findjob.api.NotificationAPI;

import backend.findjob.dto.request.PushNotificationRequest;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.services.IFirebaseMessagingService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
@Hidden
public class NotificationAPI {
    @Autowired
    private IFirebaseMessagingService firebaseMessagingService;
    @PostMapping("/pushbytoken")
    public ResponseEntity<ResponeObject> pushNotificationByToken(@RequestBody PushNotificationRequest notificationRequest)
    {
        return firebaseMessagingService.pushNotificationByToken(notificationRequest);
    }
}
