package com.androidclass.stockproactivities.service;

import java.util.HashMap;

/**
 * Created by jsingh on 8/10/16.
 */
public class StockService {

    HashMap<String,Stock> stockList = new HashMap<String,Stock>();


    public StockService() {


        Stock msft = new Stock("MSFT", "Microsoft Corporation", 58.02, -0.18, 58.50, 39.72, 1576000 );
        Stock google = new Stock("GOOGL", "Alphabet, Inc", 808.49, 1.01, 813.33, 593.09, 918514 );
        Stock yahoo = new Stock("YHOO", "Yahoo! Inc", 39.93, .69, 39.98,26.15, 1298000);
        Stock twitter = new Stock("TWTR", "Twitter", 19.04, .36, 31.87,13.73, 2865000);
        Stock fb = new Stock("FB", "Facebook", 124.88, -0.18, 128.33,72.00, 1067000);
        Stock ebay = new Stock("EBAY", "eBay Inc", 31.12, .01, 31.79,21.51, 608000);


        msft.setInfo("Microsoft Corporation is engaged in developing, licensing and supporting a range of software products and services. The Company also designs and sells hardware, and delivers online advertising to the customers. The Company operates in five segments: Devices and Consumer (D&C) Licensing, D&C Hardware, D&C Other, Commercial Licensing, and Commercial Other. The Company’s products include operating systems for computing devices, servers, phones, and other intelligent devices; server applications for distributed computing environments; productivity applications; business solution applications; desktop and server management tools; software development tools; video games; and online advertising. It also offers cloud-based solutions that provide customers with software, services and content over the Internet by way of shared computing resources located in centralized data centers. It provides consulting and product and solution support services.");
        google.setInfo("Alphabet Inc. is a holding company. The Company holds interests in Google Inc. (Google). The Company's segments include Google and Other Bets. Google segment includes Internet products, such as Search, Ads, Commerce, Maps, YouTube, Apps, Cloud, Android, Chrome, Google Play, and hardware products, including Chromecast, Chromebooks and Nexus, which are sold by the Company. Its technical infrastructure and Virtual Reality are also included in Google segment. Google segment is engaged in advertising, sales of digital content, applications and cloud services, as well as sale of Google branded hardware. The Other Bets segment consists of various operating segments and includes businesses, such as Access/Google Fiber, Calico, Nest, Verily, GV, Google Capital, X and other initiatives. Other Bets segment is engaged in the sale of Nest hardware products, Internet and television services through Google Fiber, and licensing and research and development (R&D) services through Verily.");
        twitter.setInfo("Twitter, Inc. (Twitter) offers products and services for users, advertisers, developers and platform and data partners. The Company's service is live-live commentary, live connections, live conversations. Its products and services for users include Twitter, and Periscope and Vine. Its Twitter is a platform for public self-expression and conversation in real time. Its promoted products enable its advertisers to promote their brands, products and services, and extend the conversation around their advertising campaigns. Its promoted products consist of promoted tweets, promoted accounts and promoted trends. Its Fabric platform offers modular software development kits that help developers build applications, gives them mobile analytics, the ability to generate revenue through Twitter's mobile-focused advertising exchange, MoPub. It offers subscription access to its public data feed for partners wishing to access data beyond its public application program interface (API).");
        yahoo.setInfo("Yahoo! Inc. (Yahoo), along with its subsidiaries, is engaged in digital information discovery. The Company's segments include the Americas; Europe, Middle East and Africa (EMEA), and Asia Pacific. The Company focuses on informing, connecting and entertaining its users with its search (Yahoo search), communications, including Yahoo Mail and Yahoo Messenger, and digital content products, including Tumblr, and its four verticals, such as Yahoo News, Yahoo Sports, Yahoo Finance and Yahoo Lifestyle. Yahoo Search is a search engine that serves as a guide for users to discover the information on the Internet. Yahoo Mail connects users to the people and things across mobile and desktop. Yahoo Messenger is an instant messaging service that provides an interactive and personalized way for users to connect and communicate in real-time. The Company's Digital Content offerings include Tumblr, its social platform, and its four verticals, including News, Sports, Finance and Lifestyle.");
        fb.setInfo("Facebook, Inc. builds products that enable people to connect and share through mobile devices and personal computers. The Company enables people to share their opinions, ideas, photos and videos, and other activities. Its products include Facebook, Instagram, Messenger, WhatsApp and Oculus. Facebook is a mobile application and Website that enables people to connect, share, discover and communicate with each other on mobile devices and personal computers. Instagram is a mobile application that enables people to take photos or videos, customize them with filter effects, and share them with friends and followers in a photo feed or send them to friends. Messenger is a messaging application available for mobile and Web on various platforms and devices. WhatsApp Messenger is a mobile messaging application that is used by people around the world. Oculus virtual reality technology and content platform allows people to play games, consume content and connect with others.");
        ebay.setInfo("eBay Inc. (eBay) is a commerce company, which operates through its Marketplace, StubHub and Classifieds platforms. The Company helps in enabling commerce on its platforms for buyers and sellers online. The Company has an open source platform that provides software developers and merchants an access to its application programming interfaces for developing software and solutions for commerce. Its Marketplace platforms include its online marketplace located at www.ebay.com, localized counterparts and the eBay mobile applications. Its StubHub platforms include its online ticket platform located at www.stubhub.com and the StubHub mobile applications. Its StubHub platforms provide customers with a place to purchase tickets to the games, concerts and theater shows and also enable owners to sell the tickets. Its Classifieds platforms offer online classifieds with a collection of brands, such as Mobile.de, Kijiji, Gumtree, Marktplaats, eBay Classifieds and others.");


        stockList.put(msft.getSymbol(), msft);
        stockList.put(google.getSymbol(), google);
        stockList.put(yahoo.getSymbol(), yahoo);
        stockList.put(twitter.getSymbol(), twitter);
        stockList.put(fb.getSymbol(), fb);
        stockList.put(ebay.getSymbol(), ebay);

    }


    public Stock getStock(String symbol) {
        return stockList.get(symbol);

    }
}
