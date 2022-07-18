package com.livk.consumer;


import com.livk.spring.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * RetrofitWebfluxConsumerApp
 * </p>
 *
 * @author livk
 * @date 2022/4/13
 */
@SpringBootApplication
public class RetrofitWebfluxConsumerApp {

    public static void main(String[] args) {
        LivkSpring.run(RetrofitWebfluxConsumerApp.class, args);
    }

}
