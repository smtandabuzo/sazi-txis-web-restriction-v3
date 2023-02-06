package com.example.authenticatingldap.core;

import java.security.Principal;


public interface Authenticator {

    Principal authenticate (String aUserId, String aToken);

}
