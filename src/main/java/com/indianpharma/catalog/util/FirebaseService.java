package com.indianpharma.catalog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;

@Component
public class FirebaseService {

    @Autowired
    private FirebaseAuth firebaseAuth;

    public UserRecord registerUserFirebase(String email, String password) throws FirebaseAuthException {
        CreateRequest req = new CreateRequest();
        req.setEmail(email);
        req.setPassword(password);
        req.setEmailVerified(Boolean.TRUE);

        return this.firebaseAuth.createUser(req);
    }
}
