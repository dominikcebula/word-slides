package com.dominikcebula.word.slides

import com.dominikcebula.word.slides.store.StaticStoreForTesting
import com.dominikcebula.word.slides.store.Store
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class TestContextConfiguration {

    @Bean
    @Primary
    Store getStaticStore() {
        return new StaticStoreForTesting()
    }
}
