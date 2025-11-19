package org.example;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.example.bankAccounts.BankAccountsRegistry;
import org.example.bankAccounts.facades.InterestManagementFacade;
import org.example.bankAccounts.facades.TransactionExportFacade;
import org.example.bankAccounts.factories.BankAccountFactory;
import org.example.bankAccounts.factories.TransactionFactory;
import org.example.bankAccounts.services.BankAccountFundsService;
import org.example.bankAccounts.services.BankAccountNumberGenerator;
import org.example.bankAccounts.services.InterestCalculatorService;
import org.example.bankAccounts.services.TransactionManager;
import org.example.bankAccounts.transactions.cron.TransactionExportCronService;
import org.example.cards.*;
import org.example.cards.services.PaymentCardService;
import org.example.cron.InterestCalculationCronService;
import org.example.logger.TransactionLogger;
import org.example.people.factories.CustomersFactory;
import org.example.people.serialization.CustomerSerializationFactory;
import org.example.serialization.BankAccountOwnerJsonSerializationServiceXML;
import org.example.serialization.Serialization;

public class BankInjector extends AbstractModule {
    @Override
    protected void configure() {
        bind(Serialization.class).to(BankAccountOwnerJsonSerializationServiceXML.class);

        bind(CustomersFactory.class);
        bind(BankAccountFactory.class);
        bind(PaymentCardFactory.class);
        bind(CustomerSerializationFactory.class);

        bind(BankAccountFundsService.class);
        bind(PaymentCardService.class);

        bind(TransactionLogger.class).in(Singleton.class);

        bind(BankAccountNumberGenerator.class);

        bind(PaymentCardNumberGenerator.class);
        bind(PaymentCardCvvGenerator.class);
        bind(PaymentCardPinGenerator.class);
        bind(PaymentCardExpirationCalculator.class);

        bind(PaymentCardRegistry.class).in(Singleton.class);
        bind(BankAccountsRegistry.class).in(Singleton.class);

        bind(InterestCalculatorService.class);

        bind(InterestManagementFacade.class);

        bind(InterestCalculationCronService.class).in(Singleton.class);

        bind(Gson.class).toInstance(new Gson());
        bind(XmlMapper.class).toInstance(new XmlMapper());

        bind(TransactionFactory.class);
        bind(TransactionManager.class).in(Singleton.class);
        bind(TransactionExportFacade.class);
        bind(TransactionExportCronService.class).in(Singleton.class);
    }
}