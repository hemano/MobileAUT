Appium Environment Setup instructions:
https://docs.google.com/document/d/1cynI1UVCrCcyzKdjxPGgG1gYPaI6ENblzmrp2fNWJQw/edit?usp=sharing

Edit "config.properties" file with appropriate AppiumJS Path

For Mac:
   
    APPIUM_JS_PATH=/usr/local/lib/node_modules/appium/build/lib/main.js

For Windows:
  
    APPIUM_JS_PATH=C:/Users/<username>/AppData/Roaming/npm/node_modules/appium/build/lib/main.js

Instructions to execute the tests:

Connect the devices or open emulators
open terminal and execute below maven command

    mvn clean test -Dtest=Runner
    
User can keep the APK file in the "build" directory.

The DesiredCapabilitie can be configured in "caps/capabilities.json" file as follows:

    [
      {
        "android": {
          "platformName": "android",
          "deviceName": "android",
          "appPackage":"com.thecarousell.Carousell",
          "appActivity": "com.thecarousell.Carousell.activities.EntryActivity",
          "noSign":true,
          "autoGrantPermissions": true,
          "newCommandTimeout": 10000
          }
      }
    ]

Note: Currently, test cases can be only written in "com.carousell.test.CarousellTest" class. 
Improvements:
1. Execute test from classes under the test package.
