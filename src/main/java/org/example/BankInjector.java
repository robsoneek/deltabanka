package org.example;

import com.google.inject.AbstractModule;
import org.example.serialization.BankAccountOwnerJsonSerializationService;
import org.example.serialization.Serialization;

public class BankInjector extends AbstractModule {
    @Override
    protected void configure() {
        bind(Serialization.class).to(BankAccountOwnerJsonSerializationService.class);
    }

}
