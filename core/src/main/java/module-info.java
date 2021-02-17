//module core {
//    requires x.snowroller.spi;
//    requires x.snowroller.fileutils;
//    requires com.google.gson;
//
//    requires java.sql;
//    requires net.bytebuddy;
//    requires com.fasterxml.classmate;
//    requires java.persistence;
//    requires java.xml.bind;
//    exports x.snowroller.models;
//    opens x.snowroller to com.google.gson, org.hibernate.orm.core;
//
////    requires org.mongodb.driver.sync.client;
////    requires org.mongodb.driver.core;
////    requires org.mongodb.bson;
//    //   requires jdk.net;
////    requires org.slf4j;
//    requires static lombok;
//    //requires javaee.api;  VAD ÄR DETA? FUNKAR INTE MED BÅDE DENNA OCH JAVA.PRESISTENCE
//    uses x.snowroller.spi.Page;
//    uses x.snowroller.spi.CurrencyConverter;
//    opens x.snowroller.models to com.google.gson;
//}

module core {
        requires x.snowroller.spi;
        requires x.snowroller.fileutils;
        requires com.google.gson;
        requires org.hibernate.orm.core;

        requires java.sql;
        requires net.bytebuddy;
        requires com.fasterxml.classmate;
        requires java.persistence;
        requires java.xml.bind;
        opens x.snowroller to com.google.gson, org.hibernate.orm.core;


        requires static lombok;

        uses x.snowroller.spi.Page;
        uses x.snowroller.spi.CurrencyConverter;
        }