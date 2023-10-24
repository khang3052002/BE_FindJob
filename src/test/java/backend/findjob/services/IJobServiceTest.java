//package backend.findjob.services;
//
//import backend.findjob.dto.respone.ResponeObject;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//
//import java.lang.reflect.Field;
//
//import static org.junit.jupiter.api.Assertions.*;
////@SpringBootTest
//class IJobServiceTest {
//    @Autowired
//    private IJobService jobService;
//
//
//    @BeforeEach
//    void setUp() {
//    }
////    @Test
////    public void whenIdJobValidate_thenJobShouldFound() throws NoSuchFieldException {
////        Long id_user = 1L;
////        ResponseEntity<ResponeObject> respone = jobService.getSaveListFromUser(id_user);
//////        Field id =  respone.getBody().getData().getClass().getDeclaredField("id");
//////        System.out.println(id);
////        assertEquals(respone.getStatusCode(),200);
////
////    }
//}