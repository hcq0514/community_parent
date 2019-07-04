package com.comm.sms.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云通讯配置
 * @author : hcq
 * @date : 2019/6/17
 */
@Configuration
public class FirebasePushConfig {


	@Bean
	public void initializeApp() throws Exception {
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.getApplicationDefault())
//                .setDatabaseUrl("https://user.firebaseio.com/")
//                .build();

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.getApplicationDefault())
				.setDatabaseUrl("https://fir-messing-53de2.firebaseio.com")
				.build();

        FirebaseApp.initializeApp(options);

//		FirebaseOptions options2 = new FirebaseOptions.Builder()
//				.setCredentials(GoogleCredentials.getApplicationDefault())
//				.setDatabaseUrl("https://fir-messing-53de2.firebaseio.com")
//				.build();
//
//        FirebaseApp.initializeApp(options2,"se");
	}

}
