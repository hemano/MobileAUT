Appium Environment Setup instructions:
https://docs.google.com/document/d/1cynI1UVCrCcyzKdjxPGgG1gYPaI6ENblzmrp2fNWJQw/edit?usp=sharing

Edit "config.properties" file with appropriate AppiumJS Path

For Mac:
   
    APPIUM_JS_PATH=/usr/local/lib/node_modules/appium/build/lib/main.js

For Windows:
  
    APPIUM_JS_PATH=C:/Users/<username>/AppData/Roaming/npm/node_modules/appium/build/lib/main.js

Instructions to execute the tests:

Connecte the devices or open emulators
open terminal and execute below maven command

    mvn clean test -Dtest=Runner
