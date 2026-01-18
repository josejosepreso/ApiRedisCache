package com.indianpharma.catalog;

import java.time.Duration;

public final class Configuration {
	public static final Duration JWT_EXPIRATION_DURATION = Duration.ofMinutes(15);
	public static final String FIREBASE_AUTH_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword";
}
